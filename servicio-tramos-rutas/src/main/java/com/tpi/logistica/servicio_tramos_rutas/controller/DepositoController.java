package com.tpi.logistica.servicio_tramos_rutas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpi.logistica.servicio_tramos_rutas.entities.Deposito;
import com.tpi.logistica.servicio_tramos_rutas.repositories.DepositoRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/depositos")
@RequiredArgsConstructor
public class DepositoController {

    private final DepositoRepository depositoRepo;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Deposito deposito) {
        return ResponseEntity.ok(depositoRepo.save(deposito));
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(depositoRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(
                depositoRepo.findById(id).orElse(null)
        );
    }
}
