package com.tpi.logistica.servicio_tramos_rutas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tpi.logistica.servicio_tramos_rutas.entities.TramosDeRuta;
import com.tpi.logistica.servicio_tramos_rutas.repositories.TramosDeRutaRepository;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/rutas/tramos")
@RequiredArgsConstructor
public class TramosDeRutaController {

    private final TramosDeRutaRepository repo;

    @PostMapping
    public ResponseEntity<?> asignar(@RequestBody TramosDeRuta tdr) {
        return ResponseEntity.ok(repo.save(tdr));
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(repo.findAll());
    }
}
    

