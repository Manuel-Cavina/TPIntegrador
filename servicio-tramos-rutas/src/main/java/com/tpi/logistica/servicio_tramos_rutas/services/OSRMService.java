package com.tpi.logistica.servicio_tramos_rutas.services;

import com.tpi.logistica.servicio_tramos_rutas.dtos.RutaOSRMResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class OSRMService {

    private final RestTemplate restTemplate = new RestTemplate();

    public RutaOSRMResponse calcularTramo(double lon1, double lat1, double lon2, double lat2) {

        try {

            String url = String.format(
                    "http://localhost:5000/route/v1/driving/%f,%f;%f,%f?overview=false",
                    lon1, lat1, lon2, lat2
            );

            Map<String, Object> json = restTemplate.getForObject(url, Map.class);

            List<Map<String, Object>> routes = (List<Map<String, Object>>) json.get("routes");
            Map<String, Object> route = routes.get(0);

            double distancia = ((Number) route.get("distance")).doubleValue();
            double duracion = ((Number) route.get("duration")).doubleValue();

            return new RutaOSRMResponse(distancia, duracion);

        } catch (Exception e) {
            throw new RuntimeException("Error consultando OSRM: " + e.getMessage(), e);
        }
    }
}
