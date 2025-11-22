package com.tpi.logistica.servicio_tramos_rutas.dtos;
    

import com.tpi.logistica.servicio_tramos_rutas.entities.Tramo;
import lombok.Data;

@Data
public class TramoDTO {

    private Integer id;
    private Integer direccionOrigenId;
    private Integer direccionDestinoId;
    private Integer camionId;
    private Integer estadoId;
    private Integer tipoId;
    private Integer solicitudId;
    private String fechaHoraInicio;
    private String fechaHoraFin;
    private double costoAprox;
    private double costoReal;

    public TramoDTO(Integer id, Integer direccionOrigenId, Integer direccionDestinoId, Integer camionId,
                    Integer estadoId, Integer tipoId, String fechaHoraInicio, String fechaHoraFin,
                    double costoAprox, double costoReal, Integer solicitudId) {
        this.id = id;
        this.direccionOrigenId = direccionOrigenId;
        this.direccionDestinoId = direccionDestinoId;
        this.camionId = camionId;
        this.estadoId = estadoId;
        this.tipoId = tipoId;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.costoAprox = costoAprox;
        this.costoReal = costoReal;
        this.solicitudId = solicitudId;
    }

    public static TramoDTO toDto(Tramo t) {
        return new TramoDTO(
                t.getId(),
                t.getDireccionOrigenId(),
                t.getDireccionDestinoId(),
                t.getCamionId(),
                t.getEstadoId(),
                t.getTipoId(),
                t.getFechaHoraInicio() != null ? t.getFechaHoraInicio().toString() : null,
                t.getFechaHoraFin() != null ? t.getFechaHoraFin().toString() : null,
                t.getCostoAprox(),
                t.getCostoReal(),
                t.getSolicitudId()
        );
    }
}
