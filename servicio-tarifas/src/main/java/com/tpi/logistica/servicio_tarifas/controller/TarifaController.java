package com.tpi.logistica.servicio_tarifas.controllers;

import com.tpi.logistica.servicio_tarifas.dtos.TarifaDTO;
import com.tpi.logistica.servicio_tarifas.services.TarifaService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/tarifas")
public class TarifaController {

    private final TarifaService service;

    public TarifaController(TarifaService service) {
        this.service = service;
    }

    @GetMapping("/ping")
    public Map<String, String> ping() {
        return Map.of("status", "OK", "mensaje", "Servicio Tarifas operativo");
    }

    @GetMapping
    public List<TarifaDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public TarifaDTO getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<TarifaDTO> create(@RequestBody TarifaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public TarifaDTO update(@PathVariable int id, @RequestBody TarifaDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
