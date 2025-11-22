package com.tpi.logistica.servicio_tramos_rutas.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RutaOSRMResponse {
    private double distancia; // metros
    private double duracion;  // segundos
}
