package com.tpi.logistica.servicio_tramos_rutas.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ruta")
@Data
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_solicitud", nullable = false)
    private int solicitudId; // FK hacia servicio_solicitudes

    @Column(name = "cantidad_tramos")
    private int cantidadTramos;

    @Column(name = "cantidad_depositos")
    private int cantidadDepositos;
}
