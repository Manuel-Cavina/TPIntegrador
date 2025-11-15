package com.tpi.logistica.servicio_contenedores.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "contenedores")
@Data
public class Contenedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nro_identificacion", nullable = false, unique = true)
    private int nroIdentificacion;

    @Column(nullable = false)
    private double peso;

    @Column(nullable = false)
    private double volumen;

    @Column(name = "estado_id", nullable = false)
    private int estadoId;

    @Column(name = "ubicacion_id", nullable = false)
    private int ubicacionId;

    @Column(name = "cliente_id", nullable = false)
    private int clienteId;
}

