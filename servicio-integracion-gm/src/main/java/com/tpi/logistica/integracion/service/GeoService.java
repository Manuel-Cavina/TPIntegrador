package com.tpi.logistica.integracion.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpi.logistica.integracion.dto.DistanciaDTO;

@Service
public class GeoService {

    @Value("${google.maps.apikey}")
    private String apiKey;

    private final RestClient.Builder builder;

    public GeoService(RestClient.Builder builder) {
        this.builder = builder;
    }

    public DistanciaDTO calcularDistancia(String origen, String destino) throws Exception {

        RestClient client = builder.baseUrl("https://maps.googleapis.com/maps/api").build();

        String url = "/distancematrix/json?origins=" + origen +
                     "&destinations=" + destino +
                     "&units=metric&key=" + apiKey;

        String response = client.get().uri(url).retrieve().body(String.class);
        System.out.println("====== RESPUESTA GOOGLE ======");
        System.out.println(response);
        System.out.println("================================");


        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);
        JsonNode leg = root.path("rows").get(0).path("elements").get(0);
        
        DistanciaDTO dto = new DistanciaDTO();
        dto.setOrigen(origen);
        dto.setDestino(destino);
        dto.setKilometros(leg.path("distance").path("value").asDouble() / 1000);
        dto.setDuracionTexto(leg.path("duration").path("text").asText());

        return dto;
    }
}
