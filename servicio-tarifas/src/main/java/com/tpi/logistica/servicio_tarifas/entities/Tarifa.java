package com.tpi.logistica.servicio_tarifas.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tarifas")
@Data
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo; // Ej: "BASE", "COMBUSTIBLE", "DEPOSITO", "TRANSPORTE"

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "costo_base", precision = 14, scale = 2)
    private double costoBase;

    @Column(name = "costo_por_km", precision = 14, scale = 2)
    private double costoPorKm;

    @Column(name = "costo_por_kg", precision = 14, scale = 2)
    private double costoPorKg;

    @Column(name = "costo_por_volumen", precision = 14, scale = 2)
    private double costoPorVolumen;

    @Column(name = "activa", nullable = false)
    private boolean activa = true;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio = LocalDateTime.now();

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;
}
