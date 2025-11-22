package com.tpi.logistica.servicio_tramos_rutas.controller;

import java.time.LocalDateTime;

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
@RequestMapping("/tramos")
@RequiredArgsConstructor
public class TramoController {

    private final TramoRepository tramoRepo;

    // TRANSPORTISTA INICIA TRAMO
    @PutMapping("/{id}/iniciar")
    public ResponseEntity<?> iniciar(@PathVariable Integer id) {
        Tramo t = tramoRepo.findById(id).orElse(null);

        if (t == null)
            return ResponseEntity.badRequest().body("Tramo no encontrado");

        t.setEstadoId(2); // EN CURSO
        t.setFechaHoraInicio(LocalDateTime.now());
        tramoRepo.save(t);

        return ResponseEntity.ok("Tramo iniciado correctamente");
    }

    // TRANSPORTISTA FINALIZA TRAMO
    @PutMapping("/{id}/finalizar")
    public ResponseEntity<?> finalizar(@PathVariable Integer id) {
        Tramo t = tramoRepo.findById(id).orElse(null);

        if (t == null)
            return ResponseEntity.badRequest().body("Tramo no encontrado");

        t.setEstadoId(3); // FINALIZADO
        t.setFechaHoraFin(LocalDateTime.now());
        tramoRepo.save(t);

        return ResponseEntity.ok("Tramo finalizado correctamente");
    }

    // TRAMOS PENDIENTES
@GetMapping("/pendientes")
public ResponseEntity<?> pendientes() {
    return ResponseEntity.ok(tramoRepo.findByEstadoId(1));
}

// TRAMOS EN CURSO
@GetMapping("/en-curso")
public ResponseEntity<?> enCurso() {
    return ResponseEntity.ok(tramoRepo.findByEstadoId(2));
}

// TRAMOS FINALIZADOS
@GetMapping("/finalizados")
public ResponseEntity<?> finalizados() {
    return ResponseEntity.ok(tramoRepo.findByEstadoId(3));
}
    
}
