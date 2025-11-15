package com.tpi.logistica.servicio_camiones.dtos;

import com.tpi.logistica.servicio_camiones.entities.EstadoCamion;

import lombok.Data;
@Data
public class EstadoCamionDTO {
    private int id;
    private String nombre;

    public EstadoCamionDTO(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public static EstadoCamionDTO toDto(EstadoCamion estado) {
        return new EstadoCamionDTO(
                estado.getId(),
                estado.getNombre()
        );
    }
}
