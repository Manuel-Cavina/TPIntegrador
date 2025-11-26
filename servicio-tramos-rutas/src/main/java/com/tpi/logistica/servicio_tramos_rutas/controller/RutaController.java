package com.tpi.logistica.servicio_tramos_rutas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpi.logistica.servicio_tramos_rutas.dtos.RutaAsignarRequest;
import com.tpi.logistica.servicio_tramos_rutas.dtos.RutaResponse;
import com.tpi.logistica.servicio_tramos_rutas.services.RutaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rutas")
@RequiredArgsConstructor
public class RutaController {

    private final RutaService rutaService;

    @PostMapping("/asignar")
    public ResponseEntity<RutaResponse> asignarRuta(@RequestBody RutaAsignarRequest request) {
        return ResponseEntity.ok(rutaService.asignarRuta(request));
    }
}
