package com.tpi.logistica.servicio_contenedores.dtos;
import com.tpi.logistica.servicio_contenedores.entities.Ubicacion;

import lombok.Data;

@Data
public class UbicacionDTO {
    private int id;
    private String calle;
    private int numeracion;
    private double latitud;
    private double longitud;

    public UbicacionDTO(int id, String calle, int numeracion, double latitud, double longitud) {
        this.id = id;
        this.calle = calle;
        this.numeracion = numeracion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public static UbicacionDTO toDto(Ubicacion u) {
        return new UbicacionDTO(
                u.getId(),
                u.getCalle(),
                u.getNumeracion(),
                u.getLatitud(),
                u.getLongitud()
        );
    }
}


