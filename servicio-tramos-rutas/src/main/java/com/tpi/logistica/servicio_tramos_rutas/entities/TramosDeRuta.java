package com.tpi.logistica.servicio_tramos_rutas.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tramos_de_ruta")
@Data
public class TramosDeRuta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ruta_id", nullable = false)
    private int rutaId; // FK hacia Ruta

    @Column(name = "tramo_id", nullable = false)
    private int tramoId; // FK hacia Tramo

    @Column(name = "orden", nullable = false)
    private int orden; // orden del tramo dentro de la ruta
}
