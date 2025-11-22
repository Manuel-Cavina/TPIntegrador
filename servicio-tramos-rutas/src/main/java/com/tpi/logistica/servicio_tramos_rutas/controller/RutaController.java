package com.tpi.logistica.servicio_tramos_rutas.controller;

import com.tpi.logistica.servicio_tramos_rutas.entities.Tramo;
import com.tpi.logistica.servicio_tramos_rutas.repositories.TramoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rutas")
@RequiredArgsConstructor
public class RutaController {

    private final TramoRepository tramoRepo;

    // Guarda los tramos reales asignados a una solicitud
    @PostMapping("/guardar")
    public ResponseEntity<?> guardarRuta(@RequestBody List<Tramo> tramos) {

        for (Tramo t : tramos) {
            t.setEstadoId(1);          // pendiente
            tramoRepo.save(t);
        }

        return ResponseEntity.ok("Ruta guardada correctamente.");
    }

    // obtener todos los tramos de una solicitud
    @GetMapping("/{solicitudId}")
    public ResponseEntity<?> obtenerPorSolicitud(@PathVariable Integer solicitudId) {
        return ResponseEntity.ok(tramoRepo.findBySolicitudId(solicitudId));
    }
}
