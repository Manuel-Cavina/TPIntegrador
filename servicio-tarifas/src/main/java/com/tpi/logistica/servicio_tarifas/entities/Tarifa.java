package com.tpi.logistica.servicio_tarifas.entities;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tarifas")
@Data
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(length = 255)
    private String descripcion;

    private double costoBase;
    private double costoPorKm;
    private double costoPorKg;
    private double costoPorVolumen;

    @Column(nullable = false)
    private boolean activa = true;

    @Column(nullable = false)
    private LocalDateTime fechaInicio = LocalDateTime.now();

    private LocalDateTime fechaFin;
}
