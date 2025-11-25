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

    public static CotizacionDTO toDto(Cotizacion c) {
        CotizacionDTO dto = new CotizacionDTO();
        dto.setId(c.getId());
        dto.setSolicitudId(c.getSolicitudId());
        dto.setTipoCalculo(c.getTipoCalculo());
        dto.setDistanciaKm(c.getDistanciaKm());
        dto.setPesoKg(c.getPesoKg());
        dto.setVolumenM3(c.getVolumenM3());
        dto.setCostoTotal(c.getCostoTotal());
        dto.setFechaCalculo(c.getFechaCalculo().toString());
        return dto;
    }
}
