package com.tpi.logistica.servicio_tramos_rutas.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpi.logistica.servicio_tramos_rutas.entities.Tramo;
import com.tpi.logistica.servicio_tramos_rutas.repositories.TramoRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/tramos")
@RequiredArgsConstructor
public class TramoController {

    private final TramoRepository tramoRepo;

    // ============================================================
    // 1) LISTAR TODOS LOS TRAMOS
    // ============================================================
    @GetMapping
    public ResponseEntity<List<Tramo>> listarTodos() {
        return ResponseEntity.ok(tramoRepo.findAll());
    }

    // ============================================================
    // 2) OBTENER TRAMO POR ID
    // ============================================================
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        return tramoRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ============================================================
    // 3) LISTAR TRAMOS POR SOLICITUD
    // ============================================================
    @GetMapping("/solicitud/{solicitudId}")
    public ResponseEntity<List<Tramo>> obtenerPorSolicitud(
            @PathVariable Integer solicitudId) {

        return ResponseEntity.ok(tramoRepo.findBySolicitudId(solicitudId));
    }

    // ============================================================
    // 4) INICIAR TRAMO (SET FECHA REAL DE INICIO)
    // ============================================================
    @PutMapping("/{id}/iniciar")
    public ResponseEntity<?> iniciarTramo(@PathVariable Integer id) {

        Tramo tramo = tramoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tramo no encontrado"));

        tramo.setFechaHoraInicio(LocalDateTime.now());
        tramo.setEstadoId(2); // EN CURSO

        tramoRepo.save(tramo);

        return ResponseEntity.ok("Tramo iniciado correctamente");
    }

    // ============================================================
    // 5) FINALIZAR TRAMO (SET FECHA REAL DE FIN)
    // ============================================================
    @PutMapping("/{id}/finalizar")
    public ResponseEntity<?> finalizarTramo(@PathVariable Integer id) {

        Tramo tramo = tramoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tramo no encontrado"));

        tramo.setFechaHoraFin(LocalDateTime.now());
        tramo.setEstadoId(3); // FINALIZADO

        tramoRepo.save(tramo);

        return ResponseEntity.ok("Tramo finalizado correctamente");
    }

    
}
