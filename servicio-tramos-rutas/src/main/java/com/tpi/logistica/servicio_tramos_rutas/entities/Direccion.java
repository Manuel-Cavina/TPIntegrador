package com.tpi.logistica.servicio_tramos_rutas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "direccion")
@Data
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 20)
    private String tipo; // "origen", "destino" o "depósito"

    @Column(nullable = false, length = 100)
    private String calle;

    @Column(nullable = false)
    private int numeracion;

    @Column(precision = 12, scale = 8)
    private double latitud;

    @Column(precision = 12, scale = 8)
    private double longitud;
}
