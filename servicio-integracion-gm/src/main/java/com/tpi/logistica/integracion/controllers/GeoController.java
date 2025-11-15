package com.tpi.logistica.integracion.controllers;

import org.springframework.web.bind.annotation.*;
import com.tpi.logistica.integracion.dto.DistanciaDTO;
import com.tpi.logistica.integracion.service.GeoService;

@RestController
@RequestMapping("/api/distancia")
public class GeoController {

    private final GeoService geoService;

    public GeoController(GeoService geoService) {
        this.geoService = geoService;
    }

    @GetMapping
    public DistanciaDTO obtenerDistancia(
            @RequestParam String origen,
            @RequestParam String destino) throws Exception {

        return geoService.calcularDistancia(origen, destino);
    }
}
