package com.tpi.logistica.servicio_contenedores.dtos;
import com.tpi.logistica.servicio_contenedores.entities.Ubicacion;

import lombok.Data;

@Data
public class UbicacionDTO {
    private Integer id;
    private String calle;
    private Integer numeracion;
    private Double latitud;
    private Double longitud;

    public UbicacionDTO(Integer id, String calle, Integer numeracion, Double latitud, Double longitud) {
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


