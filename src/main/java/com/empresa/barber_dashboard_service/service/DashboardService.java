package com.empresa.barber_dashboard_service.service;

import com.empresa.barber_dashboard_service.dto.response.BarberoRankingResponse;
import com.empresa.barber_dashboard_service.dto.response.ConteoEstadoResponse;
import com.empresa.barber_dashboard_service.dto.response.ResumenResponse;
import com.empresa.barber_dashboard_service.dto.response.ServicioTopResponse;

import java.util.List;
import java.util.Map;

public interface DashboardService {

    ResumenResponse obtenerResumen();

    List<ConteoEstadoResponse> citasPorEstado();

    List<ServicioTopResponse> serviciosTop();

    List<BarberoRankingResponse> rankingBarberos();

    Map<String, Double> ingresos();
}
