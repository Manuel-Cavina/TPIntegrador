package com.tpi.logistica.servicio_solicitudes.controller;

import com.tpi.logistica.servicio_solicitudes.dtos.SolicitudDTO;
import com.tpi.logistica.servicio_solicitudes.services.SolicitudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @GetMapping("/ping")
    public String ping() {
        return "Servicio Solicitudes OK";
    }

    @GetMapping
    public List<SolicitudDTO> getAll() {
        return solicitudService.getAll();
    }

    @GetMapping("/{id}")
    public SolicitudDTO getById(@PathVariable int id) {
        return solicitudService.getById(id);
    }

    @PostMapping
    public ResponseEntity<SolicitudDTO> create(@RequestBody SolicitudDTO dto) {
        SolicitudDTO creada = solicitudService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    public SolicitudDTO update(@PathVariable int id, @RequestBody SolicitudDTO dto) {
        return solicitudService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        solicitudService.delete(id);
    }
}
