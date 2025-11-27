package com.tpi.logistica.servicio_camiones.controllers;

import com.tpi.logistica.servicio_camiones.dtos.CamionDTO;
import com.tpi.logistica.servicio_camiones.services.CamionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/camiones")
public class CamionController {

    private final CamionService camionService;

    public CamionController(CamionService camionService) {
        this.camionService = camionService;
    }

    @GetMapping("/ping")
    public Map<String, String> ping() {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("status", "OK");
        respuesta.put("mensaje", "Servicio Camiones operativo");
        return respuesta;
    }

    @GetMapping
    public List<CamionDTO> getAll() {
        return camionService.getAll();
    }

    @GetMapping("/{id}")
    public CamionDTO getById(@PathVariable int id) {
        return camionService.getById(id);
    }

    @PostMapping
    public ResponseEntity<CamionDTO> create(@RequestBody CamionDTO dto) {
        CamionDTO creado = camionService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public CamionDTO update(@PathVariable int id, @RequestBody CamionDTO dto) {
        return camionService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        camionService.delete(id);
    }
}
