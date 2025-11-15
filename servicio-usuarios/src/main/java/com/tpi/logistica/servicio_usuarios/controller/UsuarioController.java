package com.tpi.logistica.servicio_usuarios.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @GetMapping("/ping")
    public Map<String, String> ping() {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("status", "OK");
        respuesta.put("mensaje", "Servicio usuarios funcionando correctamente");
        return respuesta;
    }
}
