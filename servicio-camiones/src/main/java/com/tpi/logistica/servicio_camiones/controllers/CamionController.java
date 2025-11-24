package com.tpi.logistica.servicio_camiones.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CamionController {

    @GetMapping("/ping")
    public Map<String, String> ping() {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("status", "OK");
        respuesta.put("mensaje", "Servicio Camiones operativo");
        return respuesta;
    }

    @GetMapping("/api/v1/camiones/")
    public Map<String, String> listCamiones() {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("status", "success");
        respuesta.put("mensaje", "Listado de camiones");
        return respuesta;
    }
}
