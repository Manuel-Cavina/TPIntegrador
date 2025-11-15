package com.tpi.logistica.servicio_tramos_rutas.dtos;

import com.tpi.logistica.servicio_tramos_rutas.entities.TramosDeRuta;

import lombok.Data;

@Data
public class TramosDeRutaDTO {

    private int id;
    private int rutaId;
    private int tramoId;
    private int orden;

    public TramosDeRutaDTO(int id, int rutaId, int tramoId, int orden) {
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
