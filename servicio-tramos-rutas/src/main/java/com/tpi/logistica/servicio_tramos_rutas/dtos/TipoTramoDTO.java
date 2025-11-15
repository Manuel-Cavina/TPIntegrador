package com.tpi.logistica.servicio_tramos_rutas.dtos;

import com.tpi.logistica.servicio_tramos_rutas.entities.TipoTramo;
import lombok.Data;

@Data
public class TipoTramoDTO {

    private int id;
    private String nombre;

    public TipoTramoDTO(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public static TipoTramoDTO toDto(TipoTramo t) {
        return new TipoTramoDTO(t.getId(), t.getNombre());
    }
}
