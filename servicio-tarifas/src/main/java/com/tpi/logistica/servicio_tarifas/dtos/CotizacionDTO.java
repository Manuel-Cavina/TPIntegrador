package com.tpi.logistica.servicio_tarifas.dtos;

import com.tpi.logistica.servicio_tarifas.entities.Cotizacion;

import lombok.Data;

@Data
public class CotizacionDTO {

    private int id;
    private int solicitudId;
    private String tipoCalculo;
    private double distanciaKm;
    private double pesoKg;
    private double volumenM3;
    private double costoTotal;
    private String fechaCalculo;

    public CotizacionDTO(int id, int solicitudId, String tipoCalculo,
                         double distanciaKm, double pesoKg, double volumenM3,
                         double costoTotal, String fechaCalculo) {
        this.id = id;
        this.solicitudId = solicitudId;
        this.tipoCalculo = tipoCalculo;
        this.distanciaKm = distanciaKm;
        this.pesoKg = pesoKg;
        this.volumenM3 = volumenM3;
        this.costoTotal = costoTotal;
        this.fechaCalculo = fechaCalculo;
    }

    public static CotizacionDTO toDto(Cotizacion c) {
        return new CotizacionDTO(
                c.getId(),
                c.getSolicitudId(),
                c.getTipoCalculo(),
                c.getDistanciaKm(),
                c.getPesoKg(),
                c.getVolumenM3(),
                c.getCostoTotal(),
                c.getFechaCalculo() != null ? c.getFechaCalculo().toString() : null
        );
    }
}