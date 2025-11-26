package com.tpi.logistica.servicio_tramos_rutas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpi.logistica.servicio_tramos_rutas.entities.Direccion;
import com.tpi.logistica.servicio_tramos_rutas.repositories.DireccionRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/direcciones")
@RequiredArgsConstructor
public class DireccionController {

    private final DireccionRepository direccionRepo;

    @PostMapping
    public ResponseEntity<Direccion> crearDireccion(@RequestBody Direccion direccion) {
        return ResponseEntity.ok(direccionRepo.save(direccion));
    }

    @GetMapping
    public ResponseEntity<List<Direccion>> listarDirecciones() {
        return ResponseEntity.ok(direccionRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(direccionRepo.findById(id).orElse(null));
    }   
}
