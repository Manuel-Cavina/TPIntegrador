package com.tpi.logistica.servicio_tramos_rutas.dtos;

import com.tpi.logistica.servicio_tramos_rutas.entities.Ruta;
import lombok.Data;

@Data
public class RutaDTO {

    private Integer id;
    private Integer solicitudId;
    private Integer cantidadTramos;
    private Integer cantidadDepositos;

    public RutaDTO(Integer id, Integer solicitudId, Integer cantidadTramos, Integer cantidadDepositos) {
        this.id = id;
        this.solicitudId = solicitudId;
        this.cantidadTramos = cantidadTramos;
        this.cantidadDepositos = cantidadDepositos;
    }

    public static RutaDTO toDto(Ruta r) {
        return new RutaDTO(
                r.getId(),
                r.getSolicitudId(),
                r.getCantidadTramos(),
                r.getCantidadDepositos()
        );
    }
}
