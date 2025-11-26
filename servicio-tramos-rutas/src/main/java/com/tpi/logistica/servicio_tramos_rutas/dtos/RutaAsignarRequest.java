package com.tpi.logistica.servicio_tramos_rutas.dtos;

import java.util.List;

import lombok.Data;

@Data
public class RutaAsignarRequest {

    private Integer solicitudId;
    private List<TramoRequest> tramos;

    @Data
    public static class TramoRequest {
        private Integer direccionOrigenId;
        private Integer direccionDestinoId;
        private Integer tipoId;
        private Integer depositoId; // puede venir null si no aplica
    }
}
