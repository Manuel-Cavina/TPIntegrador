package com.tpi.logistica.servicio_tarifas.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tarifas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tipo;

    private String descripcion;

    private double costoBase;

    private double costoPorKm;

    private double costoPorKg;

    private double costoPorVolumen;

    private boolean activa;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;
}
