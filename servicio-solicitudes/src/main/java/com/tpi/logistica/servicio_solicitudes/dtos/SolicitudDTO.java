package com.tpi.logistica.servicio_solicitudes.dtos;


import com.tpi.logistica.servicio_solicitudes.entities.Solicitud;

import lombok.Data;

@Data
public class SolicitudDTO {

    private int id;
    private int nroSolicitud;
    private int contenedorId;
    private int clienteId;
    private double costoEstimado;
    private String tiempoEstimado;
    private double costoFinal;
    private String tiempoReal;
    private int estadoId;

    public SolicitudDTO(int id, int nroSolicitud, int contenedorId, int clienteId,
                        double costoEstimado, String tiempoEstimado, double costoFinal,
                        String tiempoReal, int estadoId) {
        this.id = id;
        this.nroSolicitud = nroSolicitud;
        this.contenedorId = contenedorId;
        this.clienteId = clienteId;
        this.costoEstimado = costoEstimado;
        this.tiempoEstimado = tiempoEstimado;
        this.costoFinal = costoFinal;
        this.tiempoReal = tiempoReal;
        this.estadoId = estadoId;
    }

    public static SolicitudDTO toDto(Solicitud s) {
        return new SolicitudDTO(
                s.getId(),
                s.getNroSolicitud(),
                s.getContenedorId(),
                s.getClienteId(),
                s.getCostoEstimado(),
                s.getTiempoEstimado() != null ? s.getTiempoEstimado().toString() : null,
                s.getCostoFinal(),
                s.getTiempoReal() != null ? s.getTiempoReal().toString() : null,
                s.getEstadoId()
        );
    }
}

