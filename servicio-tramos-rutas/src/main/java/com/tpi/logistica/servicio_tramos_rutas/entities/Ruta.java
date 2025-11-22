package com.tpi.logistica.servicio_tramos_rutas.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ruta")
@Data
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_solicitud", nullable = false)
    private Integer solicitudId; // FK hacia servicio_solicitudes

    @Column(name = "cantidad_tramos")
    private Integer cantidadTramos;

    @Column(name = "cantidad_depositos")
    private Integer cantidadDepositos;
}
