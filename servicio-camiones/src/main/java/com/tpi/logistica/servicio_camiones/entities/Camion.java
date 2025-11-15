package com.tpi.logistica.servicio_camiones.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "camiones")
@Data
public class Camion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true, length = 10)
    private String dominio;

    @Column(name = "nombre_transportista", nullable = false, length = 100)
    private String nombreTransportista;

    @Column(length = 20)
    private String telefono;

    @Column(name = "capacidad_peso", nullable = false)
    private double capacidadPeso;

    @Column(name = "capacidad_volumen", nullable = false)
    private double capacidadVolumen;

    @Column(name = "consumo_km")
    private double consumoKm;

    @Column(name = "costo_km")
    private double costoKm;

    @Column(name = "estado_id", nullable = false)
    private int estadoId;
}
