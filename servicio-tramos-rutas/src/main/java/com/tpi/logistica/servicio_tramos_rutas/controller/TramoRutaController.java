package com.tpi.logistica.servicio_tramos_rutas.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import com.tpi.logistica.servicio_tramos_rutas.dtos.TramoDTO;
import com.tpi.logistica.servicio_tramos_rutas.entities.Tramo;
import com.tpi.logistica.servicio_tramos_rutas.repositories.TramoRepository;
import com.tpi.logistica.servicio_tramos_rutas.services.OSRMService;
import com.tpi.logistica.servicio_tramos_rutas.dtos.RutaOSRMResponse;

@RestController
@RequestMapping("/tramos")
public class TramoRutaController {

    private final TramoRepository tramoRepo;
    private final OSRMService osrmService;

    public TramoRutaController(TramoRepository tramoRepo, OSRMService osrmService) {
    this.tramoRepo = tramoRepo;
    this.osrmService = osrmService;
    }
    

    @GetMapping("/ping")
    public Map<String, String> ping() {
        Map<String, String> r = new HashMap<>();
        r.put("status", "OK");
        r.put("mensaje", "Servicio tramos y rutas funcionando correctamente");
        return r;
    }

    @PostMapping
    public ResponseEntity<TramoDTO> crear(@RequestBody Tramo tramo) {

        if (tramo.getDireccionOrigenId() == null || tramo.getDireccionDestinoId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Tramo nuevo = tramoRepo.save(tramo);
        return ResponseEntity.ok(TramoDTO.toDto(nuevo));
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(tramoRepo.findAll());
    }

    @GetMapping("/estado/{idEstado}")
    public ResponseEntity<?> listarPorEstado(@PathVariable Integer idEstado) {
    return ResponseEntity.ok(tramoRepo.findByEstadoId(idEstado));
    }

    @PutMapping("/{id}/iniciar")
    public ResponseEntity<?> iniciar(@PathVariable Integer id) {

        Tramo t = tramoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tramo no encontrado"));

        t.setFechaHoraInicio(LocalDateTime.now());
        t.setEstadoId(2); // en curso

        return ResponseEntity.ok(TramoDTO.toDto(tramoRepo.save(t)));
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<?> finalizar(@PathVariable Integer id) {

    Tramo t = tramoRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Tramo no encontrado"));

    t.setFechaHoraFin(LocalDateTime.now());
    t.setEstadoId(3); // finalizado

    return ResponseEntity.ok(TramoDTO.toDto(tramoRepo.save(t)));
    }

    @GetMapping("/distancia")
    public ResponseEntity<?> distancia(
        @RequestParam Double lon1,
        @RequestParam Double lat1,
        @RequestParam Double lon2,
        @RequestParam Double lat2) {

    RutaOSRMResponse respuesta = osrmService.calcularTramo(lon1, lat1, lon2, lat2);
    return ResponseEntity.ok(respuesta);
    }
}
