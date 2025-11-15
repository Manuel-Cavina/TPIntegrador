package com.tpi.logistica.servicio_contenedores.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContenedorController {

    @GetMapping("/ping")
    public Map<String, String> ping() {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("status", "OK");
        respuesta.put("mensaje", "Servicio Camiones operativo");
        return respuesta;
    }
}
