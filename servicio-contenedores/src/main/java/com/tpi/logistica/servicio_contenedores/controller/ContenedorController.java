package com.tpi.logistica.servicio_contenedores.controller;

import com.tpi.logistica.servicio_contenedores.dtos.ContenedorDTO;
import com.tpi.logistica.servicio_contenedores.dtos.EstadoContenedorDTO;
import com.tpi.logistica.servicio_contenedores.service.ContenedorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contenedores")
public class ContenedorController {

    private final ContenedorService service;

    public ContenedorController(ContenedorService service) {
        this.service = service;
    }

    @GetMapping("/ping")
    public String ping() { return "Servicio Contenedores operativo"; }

    @PostMapping
    public ContenedorDTO crear(@RequestBody ContenedorDTO req) {
        return service.crear(req);
    }

    @GetMapping
    public Page<ContenedorDTO> listar(@RequestParam(required = false) Integer estadoId,
                                      @RequestParam(required = false) Integer clienteId,
                                      Pageable pageable) {
        return service.listar(estadoId, clienteId, pageable);
    }

    @GetMapping("/{id}")
    public ContenedorDTO obtener(@PathVariable int id) { return service.obtener(id); }

    @PutMapping("/{id}")
    public ContenedorDTO actualizar(@PathVariable int id, @RequestBody ContenedorDTO req) {
        return service.actualizar(id, req);
    }

    @PatchMapping("/{id}/estado")
    public ContenedorDTO cambiarEstado(@PathVariable int id, @RequestBody EstadoContenedorDTO req) {
        return service.cambiarEstado(id, req);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) { service.eliminar(id); }
}
