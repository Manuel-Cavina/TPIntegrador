package com.tpi.logistica.servicio_tramos_rutas.dtos;

import com.tpi.logistica.servicio_tramos_rutas.entities.Deposito;

import lombok.Data;

@Data
public class DepositoDTO {

    private int id;
    private String nombre;
    private String direccion;
    private double latitud;
    private double longitud;
    private double costoEstadiaDiaria;
    private double capacidadMaxima;
    private boolean activo;

    public DepositoDTO(int id, String nombre, String direccion,
                       double latitud, double longitud,
                       double costoEstadiaDiaria, double capacidadMaxima, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.costoEstadiaDiaria = costoEstadiaDiaria;
        this.capacidadMaxima = capacidadMaxima;
        this.activo = activo;
    }

    public static DepositoDTO toDto(Deposito d) {
        return new DepositoDTO(
                d.getId(),
                d.getNombre(),
                d.getDireccion(),
                d.getLatitud(),
                d.getLongitud(),
                d.getCostoEstadiaDiaria(),
                d.getCapacidadMaxima(),
                d.isActivo()
        );
    }
}

