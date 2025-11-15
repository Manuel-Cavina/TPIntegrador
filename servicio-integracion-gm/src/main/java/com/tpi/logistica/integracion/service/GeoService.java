package com.tpi.logistica.integracion.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeoService {

    private final RestTemplate restTemplate = new RestTemplate();

    public double calcularDistancia(double lon1, double lat1, double lon2, double lat2) {

        try {
            // FORZAMOS PUNTO DECIMAL SIEMPRE (CULTURA US/INGLÉS)
            String url = String.format(java.util.Locale.US,
                    "http://localhost:5000/route/v1/driving/%f,%f;%f,%f?overview=false",
                    lon1, lat1, lon2, lat2
            );

            JsonNode json = restTemplate.getForObject(url, JsonNode.class);

            return json
                    .get("routes")
                    .get(0)
                    .get("distance")
                    .asDouble();

        } catch (Exception e) {
            throw new RuntimeException("Error consultando OSRM: " + e.getMessage(), e);
        }
    }
}
