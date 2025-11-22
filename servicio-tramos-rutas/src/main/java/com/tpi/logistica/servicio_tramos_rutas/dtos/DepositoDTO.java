package com.tpi.logistica.servicio_tramos_rutas.dtos;

import com.tpi.logistica.servicio_tramos_rutas.entities.Deposito;

import lombok.Data;

@Data
public class DepositoDTO {

    private int id;
    private String nombre;
    private Integer direccionId;
    private Double costoEstadiaDiaria;
    private Double capacidadMaxima;
    private boolean activo;

    public DepositoDTO(int id, String nombre, Integer direccionId,
                       double costoEstadiaDiaria, double capacidadMaxima, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.direccionId = direccionId;
        this.costoEstadiaDiaria = costoEstadiaDiaria;
        this.capacidadMaxima = capacidadMaxima;
        this.activo = activo;
    }

    public static DepositoDTO toDto(Deposito d) {
        return new DepositoDTO(
                d.getId(),
                d.getNombre(),
                d.getDireccionId(),
                d.getCostoEstadiaDiaria(),
                d.getCapacidadMaxima(),
                d.isActivo()
        );
    }
}

