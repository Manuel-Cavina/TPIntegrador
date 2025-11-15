package com.tpi.logistica.integracion.controllers;

import com.tpi.logistica.integracion.dto.DistanciaDTO;
import com.tpi.logistica.integracion.service.GeoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/geo")
public class GeoController {

    private final GeoService geoService;

    public GeoController(GeoService geoService) {
        this.geoService = geoService;
    }

    // GET - recibe parámetros en la URL
    @GetMapping("/distancia")
    public ResponseEntity<Double> distancia(
            @RequestParam double lon1,
            @RequestParam double lat1,
            @RequestParam double lon2,
            @RequestParam double lat2
    ) {
        return ResponseEntity.ok(
                geoService.calcularDistancia(lon1, lat1, lon2, lat2)
        );
    }

    // POST - recibe un JSON con coordenadas
    @PostMapping("/distancia")
    public ResponseEntity<Double> distanciaPost(@RequestBody DistanciaDTO dto) {
        double distancia = geoService.calcularDistancia(
                dto.getLon1(),
                dto.getLat1(),
                dto.getLon2(),
                dto.getLat2()
        );
        return ResponseEntity.ok(distancia);
    }
}
