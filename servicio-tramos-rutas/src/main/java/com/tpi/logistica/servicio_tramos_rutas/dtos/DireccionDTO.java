package com.tpi.logistica.servicio_tramos_rutas.dtos;
import com.tpi.logistica.servicio_tramos_rutas.entities.Direccion;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Data
@Getter
public class DireccionDTO {
    private Integer id;
    private String calle;
    private Integer numeracion;
    private Double latitud;
    private Double longitud;

    public DireccionDTO(Integer id, String calle, Integer numeracion, double latitud, double longitud) {
        this.id = id;
        this.calle = calle;
        this.numeracion = numeracion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public static DireccionDTO toDto(Direccion d) {
        return new DireccionDTO(
                d.getId(),
                d.getCalle(),
                d.getNumeracion(),
                d.getLatitud(),
                d.getLongitud()
        );
    }
}
