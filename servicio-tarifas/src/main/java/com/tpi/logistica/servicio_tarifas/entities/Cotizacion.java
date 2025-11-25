package com.tpi.logistica.servicio_tarifas.entities;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cotizaciones")
@Data
public class Cotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int solicitudId;

    @Column(nullable = false, length = 20)
    private String tipoCalculo;

    private double distanciaKm;
    private double pesoKg;
    private double volumenM3;
    private double costoTotal;

    @Column(nullable = false)
    private LocalDateTime fechaCalculo = LocalDateTime.now();
}
