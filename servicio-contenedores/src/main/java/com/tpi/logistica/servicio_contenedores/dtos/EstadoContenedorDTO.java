package com.tpi.logistica.servicio_contenedores.dtos;

import lombok.Data;
import com.tpi.logistica.servicio_contenedores.entities.EstadoContenedor;

@Data
public class EstadoContenedorDTO {
    private int id;
    private String nombre;

    public EstadoContenedorDTO(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public static EstadoContenedorDTO toDto(EstadoContenedor e) {
        return new EstadoContenedorDTO(
                e.getId(),
                e.getNombre()
        );
    }
}
