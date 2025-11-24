package com.tpi.logistica.servicio_contenedores.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ubicaciones")
@Data
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String calle;

    @Column(nullable = false)
    private int numeracion;

    @Column(nullable = false)
    private double latitud;

    @Column(nullable = false)
    private double longitud;
}

