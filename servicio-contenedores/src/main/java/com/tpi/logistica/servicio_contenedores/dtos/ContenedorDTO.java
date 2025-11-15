package com.tpi.logistica.servicio_contenedores.dtos;

import com.tpi.logistica.servicio_contenedores.entities.Contenedor;

import lombok.Data;

@Data
public class ContenedorDTO {

    private int id;
    private int nroIdentificacion;
    private double peso;
    private double volumen;
    private int estadoId;
    private int ubicacionId;
    private int clienteId;

    public ContenedorDTO(int id, int nroIdentificacion, double peso, double volumen,
                         int estadoId, int ubicacionId, int clienteId) {
        this.id = id;
        this.nroIdentificacion = nroIdentificacion;
        this.peso = peso;
        this.volumen = volumen;
        this.estadoId = estadoId;
        this.ubicacionId = ubicacionId;
        this.clienteId = clienteId;
    }

    public static ContenedorDTO toDto(Contenedor c) {
        return new ContenedorDTO(
                c.getId(),
                c.getNroIdentificacion(),
                c.getPeso(),
                c.getVolumen(),
                c.getEstadoId(),
                c.getUbicacionId(),
                c.getClienteId()
        );
    }
}