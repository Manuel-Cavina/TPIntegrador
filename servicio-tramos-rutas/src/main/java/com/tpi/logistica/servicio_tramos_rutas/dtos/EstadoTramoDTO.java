package com.tpi.logistica.servicio_tramos_rutas.dtos;

import com.tpi.logistica.servicio_tramos_rutas.entities.EstadoTramo;
import lombok.Data;

@Data
public class EstadoTramoDTO {

    private int id;
    private String nombre;

    public EstadoTramoDTO(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public static EstadoTramoDTO toDto(EstadoTramo e) {
        return new EstadoTramoDTO(e.getId(), e.getNombre());
    }
}
