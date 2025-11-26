package com.tpi.logistica.servicio_tramos_rutas.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tpi.logistica.servicio_tramos_rutas.dtos.RutaAsignarRequest;
import com.tpi.logistica.servicio_tramos_rutas.dtos.RutaResponse;
import com.tpi.logistica.servicio_tramos_rutas.entities.Ruta;
import com.tpi.logistica.servicio_tramos_rutas.entities.Tramo;
import com.tpi.logistica.servicio_tramos_rutas.entities.TramosDeRuta;
import com.tpi.logistica.servicio_tramos_rutas.repositories.*;
import com.tpi.logistica.servicio_tramos_rutas.services.external.OSRMService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RutaService {

    private final RutaRepository rutaRepo;
    private final TramoRepository tramoRepo;
    private final TramosDeRutaRepository tdrRepo;
    private final DireccionRepository direccionRepo;
    private final OSRMService osrmService;

    // ===============================================================
    // POST /rutas/asignar
    // ===============================================================
    @Transactional
    public RutaResponse asignarRuta(RutaAsignarRequest request) {

        if (request.getTramos() == null || request.getTramos().isEmpty()) {
            throw new RuntimeException("La lista de tramos está vacía");
        }

        // Crear ruta principal
        Ruta ruta = new Ruta();
        ruta.setSolicitudId(request.getSolicitudId());
        ruta = rutaRepo.save(ruta);

        double distanciaTotal = 0;
        double duracionTotal = 0;
        int orden = 1;

        for (RutaAsignarRequest.TramoRequest tReq : request.getTramos()) {

            // Validar origen/destino
            var origen = direccionRepo.findById(tReq.getDireccionOrigenId())
                .orElseThrow(() -> new RuntimeException("Dirección origen no encontrada"));
            var destino = direccionRepo.findById(tReq.getDireccionDestinoId())
                .orElseThrow(() -> new RuntimeException("Dirección destino no encontrada"));

            // Llamamos a OSRM
            var osrm = osrmService.calcularTramo(
                origen.getLongitud(), origen.getLatitud(),
                destino.getLongitud(), destino.getLatitud()
            );

            double dist = (osrm != null) ? osrm.getDistancia() : 0.0;
            double dur  = (osrm != null) ? osrm.getDuracion() : 0.0;

            // Crear tramo
            Tramo tramo = new Tramo();
            tramo.setSolicitudId(request.getSolicitudId());
            tramo.setDireccionOrigenId(tReq.getDireccionOrigenId());
            tramo.setDireccionDestinoId(tReq.getDireccionDestinoId());
            tramo.setTipoId(tReq.getTipoId());
            tramo.setDepositoId(tReq.getDepositoId());
            tramo.setEstadoId(1);  
            tramo.setDistanciaAprox(dist);
            tramo.setDuracionAprox(dur);

            // ⚠ obligatorio por tu BD
            tramo.setCamionId(1);

            tramo = tramoRepo.save(tramo);

            // Relación ruta ↔ tramo
            TramosDeRuta tdr = new TramosDeRuta();
            tdr.setRutaId(ruta.getId());
            tdr.setTramoId(tramo.getId());
            tdr.setOrden(orden++);
            tdrRepo.save(tdr);

            distanciaTotal += dist;
            duracionTotal += dur;
        }

        // Actualizar ruta
        ruta.setCantidadTramos(request.getTramos().size());
        ruta.setCantidadDepositos(0);
        ruta.setDistanciaTotal(distanciaTotal);
        ruta.setDuracionTotal(duracionTotal);
        rutaRepo.save(ruta);

        return new RutaResponse(ruta.getId(), "Ruta registrada correctamente");
    }

    

    // ===============================================================
    // GET /rutas/{solicitudId}
    // ===============================================================
    public Object obtenerRutaCompleta(Integer solicitudId) {

    // 1. Buscar ruta por solicitudId
    Ruta ruta = rutaRepo.findBySolicitudId(solicitudId);
    if (ruta == null) {
        throw new RuntimeException("No existe ruta para la solicitud " + solicitudId);
    }

    // 2. Traer tramos ordenados
    var relaciones = tdrRepo.findByRutaIdOrderByOrden(ruta.getId());

    // 3. Construir lista de tramos
    var listaTramos = relaciones.stream().map(rel -> {

        Tramo tramo = tramoRepo.findById(rel.getTramoId())
            .orElseThrow(() -> new RuntimeException(
                "No existe el tramo con id " + rel.getTramoId()
            ));

        return new java.util.LinkedHashMap<String, Object>() {{
            put("orden", rel.getOrden());
            put("tramoId", tramo.getId());
            put("direccionOrigenId", tramo.getDireccionOrigenId());
            put("direccionDestinoId", tramo.getDireccionDestinoId());
            put("tipoId", tramo.getTipoId());
            put("depositoId", tramo.getDepositoId());
            put("distanciaAprox", tramo.getDistanciaAprox());
            put("duracionAprox", tramo.getDuracionAprox());
            put("estadoId", tramo.getEstadoId());
        }};
    }).toList();

    // 4. Armar la respuesta final
    return new java.util.LinkedHashMap<String, Object>() {{
        put("rutaId", ruta.getId());
        put("solicitudId", ruta.getSolicitudId());
        put("cantidadTramos", ruta.getCantidadTramos());
        put("distanciaTotal", ruta.getDistanciaTotal());
        put("duracionTotal", ruta.getDuracionTotal());
        put("tramos", listaTramos);
    }};
}


    // ===============================================================
    // GET /rutas
    // ===============================================================
    public List<Ruta> listarRutas() {
        return rutaRepo.findAll();
    }

    // ===============================================================
    // DELETE /rutas/{id}
    // ===============================================================
    public void eliminarRuta(Integer id) {
        tdrRepo.deleteByRutaId(id);
        rutaRepo.deleteById(id);
    }

    // ===============================================================
    // PUT /rutas/tramos/{id}/iniciar
    // ===============================================================
    public Tramo iniciarTramo(Integer id) {
        Tramo t = tramoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tramo no encontrado"));
        t.setFechaHoraInicio(LocalDateTime.now());
        t.setEstadoId(2);
        return tramoRepo.save(t);
    }

    // ===============================================================
    // PUT /rutas/tramos/{id}/finalizar
    // ===============================================================
    public Tramo finalizarTramo(Integer id) {
        Tramo t = tramoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tramo no encontrado"));
        t.setFechaHoraFin(LocalDateTime.now());
        t.setEstadoId(3);
        return tramoRepo.save(t);
    }

    // ===============================================================
    // GET /rutas/tramos/estado/{estadoId}
    // ===============================================================
    public List<Tramo> obtenerTramosPorEstado(Integer estadoId) {
        return tramoRepo.findByEstadoId(estadoId);
    }
}
