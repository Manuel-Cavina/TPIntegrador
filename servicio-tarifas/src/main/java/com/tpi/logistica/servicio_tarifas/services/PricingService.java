package com.tpi.logistica.servicio_tarifas.services;

import com.tpi.logistica.servicio_tarifas.dtos.CotizacionDTO;

public interface PricingService {
    CotizacionDTO cotizar(int solicitudId, double distanciaKm, double pesoKg, double volumenM3, String tipoTarifa);
}
