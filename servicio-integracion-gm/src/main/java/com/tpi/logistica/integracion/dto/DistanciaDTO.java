package com.tpi.logistica.integracion.dto;

import lombok.Data;


@Data
public class DistanciaDTO {
    
    private String origen;
    private String destino;
    private double kilometros;
    private String duracionTexto;

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public double getKilometros() {
        return kilometros;
    }

    public String getDuracionTexto() {
        return duracionTexto;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public void setKilometros(double kilometros) {
        this.kilometros = kilometros;
    }
    public void setDuracionTexto(String duracionTexto) {
        this.duracionTexto = duracionTexto;
    }
    


}

    