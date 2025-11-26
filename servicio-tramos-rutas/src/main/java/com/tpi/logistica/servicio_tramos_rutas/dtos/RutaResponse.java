package com.tpi.logistica.servicio_tramos_rutas.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RutaResponse {
    private Integer rutaId;
    private String mensaje;
}
