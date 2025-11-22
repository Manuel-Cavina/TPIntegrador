package com.tpi.logistica.servicio_tramos_rutas.dtos;

import com.tpi.logistica.servicio_tramos_rutas.entities.TramosDeRuta;

import lombok.Data;

@Data
public class TramosDeRutaDTO {

    private Integer id;
    private Integer rutaId;
    private Integer tramoId;
    private Integer orden;

    public TramosDeRutaDTO(Integer id, Integer rutaId, Integer tramoId, Integer orden) {
        this.id = id;
        this.rutaId = rutaId;
        this.tramoId = tramoId;
        this.orden = orden;
    }

    public static TramosDeRutaDTO toDto(TramosDeRuta t) {
        return new TramosDeRutaDTO(
                t.getId(),
                t.getRutaId(),
                t.getTramoId(),
                t.getOrden()
        );
    }
}
