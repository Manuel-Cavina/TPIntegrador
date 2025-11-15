package com.tpi.logistica.servicio_tramos_rutas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "deposito")
@Data
public class Deposito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String direccion; // dirección completa textual

    @Column(precision = 12, scale = 8)
    private double latitud;

    @Column(precision = 12, scale = 8)
    private double longitud;

    @Column(name = "costo_estadia_diaria", precision = 14, scale = 2)
    private double costoEstadiaDiaria;

    @Column(name = "capacidad_maxima", precision = 10, scale = 2)
    private double capacidadMaxima;

    @Column(nullable = false)
    private boolean activo = true;
}

