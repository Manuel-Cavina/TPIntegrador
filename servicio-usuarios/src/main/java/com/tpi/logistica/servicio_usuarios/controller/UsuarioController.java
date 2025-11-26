package com.tpi.logistica.servicio_usuarios.controller;

import com.tpi.logistica.servicio_usuarios.dtos.UsuarioDTO;
import com.tpi.logistica.servicio_usuarios.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Ping
    @GetMapping("/ping")
    public Map<String, String> ping() {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("status", "OK");
        respuesta.put("mensaje", "Servicio usuarios funcionando correctamente");
        return respuesta;
    }

    @GetMapping
    public List<UsuarioDTO> getAll() {
        return usuarioService.getAll();
    }

    @GetMapping("/{id}")
    public UsuarioDTO getById(@PathVariable int id) {
        return usuarioService.getById(id);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO dto) {
        UsuarioDTO creada = usuarioService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @PutMapping("/{id}")
    public UsuarioDTO update(@PathVariable int id, @RequestBody UsuarioDTO dto) {
        return usuarioService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        usuarioService.delete(id);
    }
}
