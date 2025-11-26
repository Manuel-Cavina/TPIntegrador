package com.tpi.logistica.servicio_tramos_rutas.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tpi.logistica.servicio_tramos_rutas.dtos.RutaAsignarRequest;
import com.tpi.logistica.servicio_tramos_rutas.dtos.RutaResponse;
import com.tpi.logistica.servicio_tramos_rutas.entities.Ruta;
import com.tpi.logistica.servicio_tramos_rutas.entities.Tramo;
import com.tpi.logistica.servicio_tramos_rutas.entities.TramosDeRuta;
import com.tpi.logistica.servicio_tramos_rutas.repositories.RutaRepository;
import com.tpi.logistica.servicio_tramos_rutas.repositories.TramoRepository;
import com.tpi.logistica.servicio_tramos_rutas.repositories.TramosDeRutaRepository;
import com.tpi.logistica.servicio_tramos_rutas.repositories.DireccionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RutaService {

    private final RutaRepository rutaRepo;
    private final TramoRepository tramoRepo;
    private final TramosDeRutaRepository tramosDeRutaRepo;
    private final DireccionRepository direccionRepo;
    private final OSRMService osrmService;

    @Transactional
    public RutaResponse asignarRuta(RutaAsignarRequest request) {

        Ruta ruta = new Ruta();
        ruta.setSolicitudId(request.getSolicitudId());
        ruta = rutaRepo.save(ruta);

        double distanciaTotal = 0;
        double duracionTotal = 0;
        int orden = 1;

        for (RutaAsignarRequest.TramoRequest tReq : request.getTramos()) {

            var origen = direccionRepo.findById(tReq.getDireccionOrigenId())
                .orElseThrow(() -> new RuntimeException("Dirección origen no encontrada: " + tReq.getDireccionOrigenId()));

            var destino = direccionRepo.findById(tReq.getDireccionDestinoId())
                .orElseThrow(() -> new RuntimeException("Dirección destino no encontrada: " + tReq.getDireccionDestinoId()));

            
            var osrm = osrmService.calcularTramo(
    origen.getLongitud(), origen.getLatitud(),
    destino.getLongitud(), destino.getLatitud()
);


            Tramo tramo = new Tramo();
            tramo.setSolicitudId(request.getSolicitudId());
            tramo.setDireccionOrigenId(tReq.getDireccionOrigenId());
            tramo.setDireccionDestinoId(tReq.getDireccionDestinoId());
            tramo.setTipoId(tReq.getTipoId());
            tramo.setDepositoId(tReq.getDepositoId());
            tramo.setEstadoId(1); // pendiente
            tramo.setDistanciaAprox(osrm.getDistancia());
            tramo.setDuracionAprox(osrm.getDuracion());

            tramo = tramoRepo.save(tramo);

            TramosDeRuta tdr = new TramosDeRuta();
            tdr.setRutaId(ruta.getId());
            tdr.setTramoId(tramo.getId());
            tdr.setOrden(orden++);
            tramosDeRutaRepo.save(tdr);

            distanciaTotal += osrm.getDistancia();
            duracionTotal += osrm.getDuracion();
        }

        ruta.setCantidadTramos(request.getTramos().size());
        ruta.setDistanciaTotal(distanciaTotal);
        ruta.setDuracionTotal(duracionTotal);

        rutaRepo.save(ruta);

        return new RutaResponse(ruta.getId(), "Ruta registrada correctamente");
    }
}
