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
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column (name = "id_direccion")
    private Integer direccionId; 

    @Column(name = "costo_estadia_diaria")
    private double costoEstadiaDiaria;

    @Column(name = "capacidad_maxima")
    private double capacidadMaxima;

    @Column(nullable = false)
    private boolean activo = true;
}

