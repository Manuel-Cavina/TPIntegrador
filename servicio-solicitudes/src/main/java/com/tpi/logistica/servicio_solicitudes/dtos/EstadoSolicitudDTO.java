package com.tpi.logistica.servicio_solicitudes.dtos;

import com.tpi.logistica.servicio_solicitudes.entities.EstadoSolicitud;

import lombok.Data;

@Data
public class EstadoSolicitudDTO {
    private int id;
    private String nombre;

    public EstadoSolicitudDTO(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public static EstadoSolicitudDTO toDto(EstadoSolicitud e) {
        return new EstadoSolicitudDTO(
                e.getId(),
                e.getNombre()
        );
    }
}

