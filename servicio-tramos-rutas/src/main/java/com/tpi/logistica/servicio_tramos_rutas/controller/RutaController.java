package com.tpi.logistica.servicio_tramos_rutas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tpi.logistica.servicio_tramos_rutas.dtos.RutaAsignarRequest;
import com.tpi.logistica.servicio_tramos_rutas.dtos.RutaResponse;
import com.tpi.logistica.servicio_tramos_rutas.services.RutaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/rutas")
@RequiredArgsConstructor
public class RutaController {

    private final RutaService rutaService;

    @PostMapping("/asignar")
    public ResponseEntity<RutaResponse> asignarRuta(@RequestBody RutaAsignarRequest request) {
        return ResponseEntity.ok(rutaService.asignarRuta(request));
    }

    @GetMapping("/{solicitudId}")
public ResponseEntity<?> obtenerRuta(@PathVariable Integer solicitudId) {
    return ResponseEntity.ok(rutaService.obtenerRutaCompleta(solicitudId));
}


    @GetMapping
    public ResponseEntity<?> listarRutas() {
        return ResponseEntity.ok(rutaService.listarRutas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRuta(@PathVariable Integer id) {
        rutaService.eliminarRuta(id);
        return ResponseEntity.ok("Ruta eliminada correctamente");
    }

    @PutMapping("/tramos/{id}/iniciar")
    public ResponseEntity<?> iniciarTramo(@PathVariable Integer id) {
        return ResponseEntity.ok(rutaService.iniciarTramo(id));
    }

    @PutMapping("/tramos/{id}/finalizar")
    public ResponseEntity<?> finalizarTramo(@PathVariable Integer id) {
        return ResponseEntity.ok(rutaService.finalizarTramo(id));
    }

    @GetMapping("/tramos/estado/{estadoId}")
    public ResponseEntity<?> obtenerPorEstado(@PathVariable Integer estadoId) {
        return ResponseEntity.ok(rutaService.obtenerTramosPorEstado(estadoId));
    }
}
