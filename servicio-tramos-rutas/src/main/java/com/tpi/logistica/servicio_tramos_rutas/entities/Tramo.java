package com.tpi.logistica.servicio_tramos_rutas.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tramo")
@Data
public class Tramo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(name = "id_direccion_origen", nullable = false)
    private Integer direccionOrigenId; // FK hacia Direccion

    @Column(name = "id_direccion_destino", nullable = false)
    private Integer direccionDestinoId; // FK hacia Direccion

    @Column(name = "id_camion", nullable = false)
    private Integer camionId; // FK hacia servicio_camiones

    @Column(name = "id_estado", nullable = false)
    private Integer estadoId; // FK hacia estado_tramo

    @Column(name = "id_tipo", nullable = false)
    private Integer tipoId; // FK hacia tipo_tramo

    @Column(name = "solicitud_id")
    private Integer solicitudId;

    @Column(name = "fecha_hora_inicio")
    private LocalDateTime fechaHoraInicio;

    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin;

    @Column(name = "costo_aprox")
    private double costoAprox;

    @Column(name = "costo_real")
    private double costoReal;
}
