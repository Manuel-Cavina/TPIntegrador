package com.tpi.logistica.servicio_solicitudes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SolicitudController {

    @GetMapping("/ping")
    public Map<String, String> ping() {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("status", "OK");
        respuesta.put("mensaje", "Servicio solicitudes funcionando correctamente");
        return respuesta;
    }
}
