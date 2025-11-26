package com.tpi.logistica.servicio_tramos_rutas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ruta")
@Data
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_solicitud", nullable = false)
    private Integer solicitudId;

    @Column(name = "cantidad_tramos")
    private Integer cantidadTramos;

    @Column(name = "cantidad_depositos")
    private Integer cantidadDepositos;

    // DISTANCIA TOTAL (metros)
    @Column(name = "distancia_total")
    private Double distanciaTotal;

    // DURACIÓN TOTAL (segundos)
    @Column(name = "duracion_total")
    private Double duracionTotal;
}
