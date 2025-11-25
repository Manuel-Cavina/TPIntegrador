package com.tpi.logistica.servicio_tarifas.dtos;

import com.tpi.logistica.servicio_tarifas.entities.Tarifa;
import lombok.Data;

@Data
public class TarifaDTO {

    private int id;
    private String tipo;
    private String descripcion;
    private double costoBase;
    private double costoPorKm;
    private double costoPorKg;
    private double costoPorVolumen;
    private boolean activa;
    private String fechaInicio;
    private String fechaFin;

    public TarifaDTO(int id, String tipo, String descripcion, double costoBase, double costoPorKm, double costoPorKg, double costoPorVolumen, boolean activa, String s, String s1) {
    }

    // TarifaDTO.toDto — reemplazá por esta versión
    public static TarifaDTO toDto(Tarifa t) {
        return new TarifaDTO(
                t.getId(),
                t.getTipo(),
                t.getDescripcion(),
                t.getCostoBase(),
                t.getCostoPorKm(),
                t.getCostoPorKg(),
                t.getCostoPorVolumen(),
                t.isActiva(),
                t.getFechaInicio() != null ? t.getFechaInicio().toString() : null,
                t.getFechaFin()    != null ? t.getFechaFin().toString()    : null
        );
    }

}
