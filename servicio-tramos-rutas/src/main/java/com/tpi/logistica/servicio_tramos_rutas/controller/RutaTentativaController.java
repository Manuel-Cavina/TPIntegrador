package com.tpi.logistica.servicio_tramos_rutas.controller;

import com.tpi.logistica.servicio_tramos_rutas.dtos.TramoDTO;
import com.tpi.logistica.servicio_tramos_rutas.entities.Direccion;
import com.tpi.logistica.servicio_tramos_rutas.entities.Deposito;
import com.tpi.logistica.servicio_tramos_rutas.repositories.DepositoRepository;
import com.tpi.logistica.servicio_tramos_rutas.repositories.DireccionRepository;
import com.tpi.logistica.servicio_tramos_rutas.services.OSRMService;
import com.tpi.logistica.servicio_tramos_rutas.dtos.RutaOSRMResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/rutas")
public class RutaTentativaController {

    private final DireccionRepository direccionRepo;
    private final DepositoRepository depositoRepo;
    private final OSRMService osrmService;

    public RutaTentativaController(
            DireccionRepository direccionRepo,
            DepositoRepository depositoRepo,
            OSRMService osrmService
    ) {
        this.direccionRepo = direccionRepo;
        this.depositoRepo = depositoRepo;
        this.osrmService = osrmService;
    }

    @GetMapping("/tentativa")
    public ResponseEntity<?> generarRutaTentativa(
            @RequestParam Integer origenId,
            @RequestParam Integer destinoId
    ) {

        // Buscar direcciones
        Direccion origen = direccionRepo.findById(origenId).orElse(null);
        Direccion destino = direccionRepo.findById(destinoId).orElse(null);

        if (origen == null || destino == null) {
            return ResponseEntity.badRequest().body("Direcciones no encontradas");
        }

        List<Deposito> depositos = depositoRepo.findAll();

        List<TramoDTO> tramos = new ArrayList<>();
        double distanciaTotal = 0;
        double tiempoTotal = 0;

        // Caso sin depósitos
        if (depositos.isEmpty()) {

            RutaOSRMResponse r = osrmService.calcularTramo(
                    origen.getLongitud(), origen.getLatitud(),
                    destino.getLongitud(), destino.getLatitud()
            );

            distanciaTotal = r.getDistancia();
            tiempoTotal = r.getDuracion();

            tramos.add(new TramoDTO(
                    null,                    // id
                    origen.getId(),          // direccionOrigenId
                    destino.getId(),         // direccionDestinoId
                    null,                    // camionId
                    null,                    // estadoId
                    null,                    // tipoId
                    null,                    // fechaHoraInicio
                    null,                    // fechaHoraFin
                    r.getDistancia(),        // costoAprox
                    0,                       // costoReal
                    null                     // solicitudId
            ));

            Map<String,Object> respuesta = new HashMap<>();
            respuesta.put("distanciaTotal", distanciaTotal);
            respuesta.put("tiempoTotal", tiempoTotal);
            respuesta.put("costoEstimado", 0);
            respuesta.put("tramos", tramos);

            return ResponseEntity.ok(respuesta);
        }

        // Elegir depósito más cercano
        Deposito masCercano = null;
        Direccion dirMasCercana = null;
        double menorDist = Double.MAX_VALUE;

        for (Deposito d : depositos) {
            Direccion dir = direccionRepo.findById(d.getDireccionId()).orElse(null);
            if (dir == null) continue;

            RutaOSRMResponse calc = osrmService.calcularTramo(
                    origen.getLongitud(), origen.getLatitud(),
                    dir.getLongitud(), dir.getLatitud()
            );

            if (calc.getDistancia() < menorDist) {
                menorDist = calc.getDistancia();
                masCercano = d;
                dirMasCercana = dir;
            }
        }

        if (dirMasCercana == null) {
            return ResponseEntity.badRequest().body("Depósito inválido");
        }

        // Origen → Depósito
        RutaOSRMResponse r1 = osrmService.calcularTramo(
                origen.getLongitud(), origen.getLatitud(),
                dirMasCercana.getLongitud(), dirMasCercana.getLatitud()
        );

        tramos.add(new TramoDTO(
                null,                    // id
                origen.getId(),          // direccionOrigenId
                dirMasCercana.getId(),   // direccionDestinoId
                null,                    // camionId
                null,                    // estadoId
                1,                       // tipoId
                null,                    // fechaHoraInicio
                null,                    // fechaHoraFin
                r1.getDistancia(),       // costoAprox
                0,                       // costoReal
                null                     // solicitudId
        ));

        distanciaTotal += r1.getDistancia();
        tiempoTotal += r1.getDuracion();

        // Depósito → Destino
        RutaOSRMResponse r2 = osrmService.calcularTramo(
                dirMasCercana.getLongitud(), dirMasCercana.getLatitud(),
                destino.getLongitud(), destino.getLatitud()
        );

        tramos.add(new TramoDTO(
                null,                    // id
                dirMasCercana.getId(),   // direccionOrigenId
                destino.getId(),         // direccionDestinoId
                null,                    // camionId
                null,                    // estadoId
                2,                       // tipoId
                null,                    // fechaHoraInicio
                null,                    // fechaHoraFin
                r2.getDistancia(),       // costoAprox
                0,                       // costoReal
                null                     // solicitudId
        ));

        distanciaTotal += r2.getDistancia();
        tiempoTotal += r2.getDuracion();

        Map<String,Object> respuesta = new HashMap<>();
        respuesta.put("distanciaTotal", distanciaTotal);
        respuesta.put("tiempoTotal", tiempoTotal);
        respuesta.put("costoEstimado", 0);
        respuesta.put("tramos", tramos);

        return ResponseEntity.ok(respuesta);
    }
}