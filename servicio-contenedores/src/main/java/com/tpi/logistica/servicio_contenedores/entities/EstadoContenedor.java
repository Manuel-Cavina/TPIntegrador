package com.tpi.logistica.servicio_contenedores.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "estado_contenedor")
@Data
public class EstadoContenedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;
}
