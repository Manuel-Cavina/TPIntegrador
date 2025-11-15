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
@Table(name = "cotizaciones")
@Data
public class Cotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "solicitud_id", nullable = false)
    private int solicitudId; // FK hacia el microservicio de solicitudes

    @Column(name = "tipo_calculo", nullable = false, length = 20)
    private String tipoCalculo; // "ESTIMADO" o "REAL"

    @Column(name = "distancia_km", precision = 14, scale = 2)
    private double distanciaKm;

    @Column(name = "peso_kg", precision = 14, scale = 2)
    private double pesoKg;

    @Column(name = "volumen_m3", precision = 14, scale = 2)
    private double volumenM3;

    @Column(name = "costo_total", precision = 14, scale = 2)
    private double costoTotal;

    @Column(name = "fecha_calculo", nullable = false)
    private LocalDateTime fechaCalculo = LocalDateTime.now();
}

