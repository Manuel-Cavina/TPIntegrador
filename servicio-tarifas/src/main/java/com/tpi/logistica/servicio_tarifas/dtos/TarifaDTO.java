package com.tpi.logistica.servicio_tarifas.dtos;

import com.tpi.logistica.servicio_tarifas.entities.Tarifa;
import lombok.Data;

@Data
public class TarifaDTO {

    private Integer id;
    private String tipo;
    private String descripcion;
    private double costoBase;
    private double costoPorKm;
    private double costoPorKg;
    private double costoPorVolumen;
    private boolean activa;
    private String fechaInicio;
    private String fechaFin;

    public static TarifaDTO toDto(Tarifa t) {
        TarifaDTO dto = new TarifaDTO();
        dto.setId(t.getId());
        dto.setTipo(t.getTipo());
        dto.setDescripcion(t.getDescripcion());
        dto.setCostoBase(t.getCostoBase());
        dto.setCostoPorKm(t.getCostoPorKm());
        dto.setCostoPorKg(t.getCostoPorKg());
        dto.setCostoPorVolumen(t.getCostoPorVolumen());
        dto.setActiva(t.isActiva());
        dto.setFechaInicio(t.getFechaInicio() == null ? null : t.getFechaInicio().toString());
        dto.setFechaFin(t.getFechaFin() == null ? null : t.getFechaFin().toString());
        return dto;
    }
}
