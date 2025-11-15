package com.tpi.logistica.servicio_solicitudes.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "solicitudes")
@Data
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nro_solicitud", nullable = false, unique = true)
    private int nroSolicitud;

    @Column(name = "id_contenedor", nullable = false)
    private int contenedorId;

    @Column(name = "id_cliente", nullable = false)
    private int clienteId;

    @Column(name = "costo_estimado", precision = 14, scale = 2)
    private double costoEstimado;

    @Column(name = "tiempo_estimado")
    private LocalDateTime tiempoEstimado;

    @Column(name = "costo_final", precision = 14, scale = 2)
    private double costoFinal;

    @Column(name = "tiempo_real")
    private LocalDateTime tiempoReal;

    @Column(name = "estado_id", nullable = false)
    private int estadoId;
}

