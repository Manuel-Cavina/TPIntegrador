package com.tpi.logistica.servicio_contenedores.controller;

import com.tpi.logistica.servicio_contenedores.dto.*;
import com.tpi.logistica.servicio_contenedores.entity.EstadoContenedor;
import com.tpi.logistica.servicio_contenedores.service.ContenedorService;
import jakarta.validation.Valid;
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

    // Health simple (opcional)
    @GetMapping("/ping")
    public String ping() { return "Servicio Contenedores operativo"; }

    // Crear
    @PostMapping
    public ContenedorResponse crear(@RequestBody @Valid ContenedorRequest req) {
        return service.crear(req);
    }

    // Listar con paginación y filtros
    @GetMapping
    public Page<ContenedorResponse> listar(
            @RequestParam(required = false) EstadoContenedor estado,
            @RequestParam(required = false) Long clienteId,
            Pageable pageable) {
        return service.listar(estado, clienteId, pageable);
    }

    // Obtener por id
    @GetMapping("/{id}")
    public ContenedorResponse obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    // Actualizar datos
    @PutMapping("/{id}")
    public ContenedorResponse actualizar(@PathVariable Long id,
                                         @RequestBody @Valid ContenedorUpdateRequest req) {
        return service.actualizar(id, req);
    }

    // Cambiar solo el estado
    @PatchMapping("/{id}/estado")
    public ContenedorResponse cambiarEstado(@PathVariable Long id,
                                            @RequestBody @Valid CambioEstadoRequest req) {
        return service.cambiarEstado(id, req);
    }

    // (Opcional) eliminar
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
