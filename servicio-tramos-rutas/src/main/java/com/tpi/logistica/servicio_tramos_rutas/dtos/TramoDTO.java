package com.tpi.logistica.servicio_tramos_rutas.dtos;
    
import com.tpi.logistica.servicio_tramos_rutas.entities.Tramo;
import lombok.Data;

@Data
public class TramoDTO {

    private int id;
    private int direccionOrigenId;
    private int direccionDestinoId;
    private int camionId;
    private int estadoId;
    private int tipoId;
    private String fechaHoraInicio;
    private String fechaHoraFin;
    private double costoAprox;
    private double costoReal;

    public TramoDTO(int id, int direccionOrigenId, int direccionDestinoId, int camionId,
                    int estadoId, int tipoId, String fechaHoraInicio, String fechaHoraFin,
                    double costoAprox, double costoReal) {
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
                t.getCostoReal()
        );
    }
}
