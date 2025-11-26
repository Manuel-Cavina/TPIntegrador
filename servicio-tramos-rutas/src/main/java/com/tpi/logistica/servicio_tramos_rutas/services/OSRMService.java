package com.tpi.logistica.servicio_tramos_rutas.services;

import com.tpi.logistica.servicio_tramos_rutas.dtos.RutaOSRMResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class OSRMService {

    private final RestTemplate restTemplate = new RestTemplate();

    public RutaOSRMResponse calcularTramo(double lon1, double lat1, double lon2, double lat2) {

        // 🔥 FORZAR FORMATO US (con punto como separador decimal)
        String url = String.format(
                Locale.US,
                "http://localhost:5000/route/v1/driving/%f,%f;%f,%f?overview=false",
                lon1, lat1, lon2, lat2
        );

        Map<String, Object> json;

        try {
            json = restTemplate.getForObject(url, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo conectar con OSRM: " + e.getMessage());
        }

        if (json == null) {
            throw new RuntimeException("OSRM devolvió una respuesta vacía");
        }

        // 🔥 Validar código devuelto por OSRM (debe ser "Ok")
        Object code = json.get("code");
        if (code == null || !"Ok".equals(code.toString())) {
            throw new RuntimeException("OSRM devolvió código inválido: " + code);
        }

        // 🔥 Validar rutas
        List<Map<String, Object>> routes = (List<Map<String, Object>>) json.get("routes");

        if (routes == null || routes.isEmpty()) {
            throw new RuntimeException("OSRM no encontró rutas para las coordenadas enviadas");
        }

        Map<String, Object> route = routes.get(0);

        // 🔥 Obtener distancia y duración
        double distancia = ((Number) route.get("distance")).doubleValue();
        double duracion = ((Number) route.get("duration")).doubleValue();

        return new RutaOSRMResponse(distancia, duracion);
    }
}
