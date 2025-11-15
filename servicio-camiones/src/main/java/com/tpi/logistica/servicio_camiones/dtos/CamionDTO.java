package com.tpi.logistica.servicio_camiones.dtos;


import com.tpi.logistica.servicio_camiones.entities.Camion;

import lombok.Data;

@Data
public class CamionDTO {

    private int id;
    private String dominio;
    private String nombreTransportista;
    private String telefono;
    private double capacidadPeso;
    private double capacidadVolumen;
    private double consumoKm;
    private double costoKm;
    private int estadoId;

    public CamionDTO(int id, String dominio, String nombreTransportista, String telefono,
                     double capacidadPeso, double capacidadVolumen,
                     double consumoKm, double costoKm, int estadoId) {
        this.id = id;
        this.dominio = dominio;
        this.nombreTransportista = nombreTransportista;
        this.telefono = telefono;
        this.capacidadPeso = capacidadPeso;
        this.capacidadVolumen = capacidadVolumen;
        this.consumoKm = consumoKm;
        this.costoKm = costoKm;
        this.estadoId = estadoId;
    }

    // Método estático para convertir Entity → DTO
    public static CamionDTO toDto(Camion camion) {
        return new CamionDTO(
                camion.getId(),
                camion.getDominio(),
                camion.getNombreTransportista(),
                camion.getTelefono(),
                camion.getCapacidadPeso(),
                camion.getCapacidadVolumen(),
                camion.getConsumoKm(),
                camion.getCostoKm(),
                camion.getEstadoId()
        );
    }
}
