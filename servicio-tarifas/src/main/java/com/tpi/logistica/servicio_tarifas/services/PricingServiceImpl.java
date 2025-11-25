package com.tpi.logistica.servicio_tarifas.services;

import com.tpi.logistica.servicio_tarifas.dtos.CotizacionDTO;
import com.tpi.logistica.servicio_tarifas.entities.Cotizacion;
import com.tpi.logistica.servicio_tarifas.entities.Tarifa;
import com.tpi.logistica.servicio_tarifas.repositories.CotizacionRepository;
import com.tpi.logistica.servicio_tarifas.repositories.TarifaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PricingServiceImpl implements PricingService {

    private final TarifaRepository tarifaRepo;
    private final CotizacionRepository cotizacionRepo;

    @Override
    public CotizacionDTO cotizar(int solicitudId, double distanciaKm, double pesoKg, double volumenM3, String tipoTarifa) {

        Tarifa tarifa = tarifaRepo.findFirstByTipoAndActivaTrueOrderByFechaInicioDesc(tipoTarifa);
        if (tarifa == null) {
            throw new IllegalArgumentException("No existe tarifa activa para el tipo: " + tipoTarifa);
        }

        double total =
                tarifa.getCostoBase()
                        + (distanciaKm * tarifa.getCostoPorKm())
                        + (pesoKg * tarifa.getCostoPorKg())
                        + (volumenM3 * tarifa.getCostoPorVolumen());

        total = Math.round(total * 100.0) / 100.0;

        Cotizacion c = new Cotizacion();
        c.setSolicitudId(solicitudId);
        c.setTipoCalculo("ESTIMADO");
        c.setDistanciaKm(distanciaKm);
        c.setPesoKg(pesoKg);
        c.setVolumenM3(volumenM3);
        c.setCostoTotal(total);
        c.setFechaCalculo(LocalDateTime.now());

        cotizacionRepo.save(c);

        return CotizacionDTO.toDto(c);
    }
}
