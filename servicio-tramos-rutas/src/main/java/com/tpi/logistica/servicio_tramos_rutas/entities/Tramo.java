package com.tpi.logistica.servicio_tramos_rutas.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tramo")
@Data
public class Tramo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_direccion_origen", nullable = false)
    private Integer direccionOrigenId;

    @Column(name = "id_direccion_destino", nullable = false)
    private Integer direccionDestinoId;

    @Column(name = "id_camion")
    private Integer camionId;

    @Column(name = "id_estado", nullable = false)
    private Integer estadoId;

    @Column(name = "id_tipo", nullable = false)
    private Integer tipoId;

    @Column(name = "solicitud_id")
    private Integer solicitudId;

    // DISTANCIA Y DURACIÓN ESTIMADA (OBLIGATORIO)
    @Column(name = "distancia_aprox")
    private Double distanciaAprox;

    @Column(name = "duracion_aprox")
    private Double duracionAprox;

    // FECHAS ESTIMADAS (OBLIGATORIO)
    @Column(name = "fecha_hora_inicio_est")
    private LocalDateTime fechaHoraInicioEstimada;

    @Column(name = "fecha_hora_fin_est")
    private LocalDateTime fechaHoraFinEstimada;

    // FECHAS REALES
    @Column(name = "fecha_hora_inicio")
    private LocalDateTime fechaHoraInicio;

    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin;

    // COSTOS
    @Column(name = "costo_aprox")
    private double costoAprox;

    @Column(name = "costo_real")
    private double costoReal;

    // DEPOSITO INVOLUCRADO (SI EL TIPO DE TRAMO LO REQUIERE)
    @Column(name = "deposito_id")
    private Integer depositoId;
}
