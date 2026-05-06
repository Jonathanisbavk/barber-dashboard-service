package com.empresa.barber_dashboard_service.controller;

import com.empresa.barber_dashboard_service.dto.response.BarberoRankingResponse;
import com.empresa.barber_dashboard_service.dto.response.ConteoEstadoResponse;
import com.empresa.barber_dashboard_service.dto.response.ResumenResponse;
import com.empresa.barber_dashboard_service.dto.response.ServicioTopResponse;
import com.empresa.barber_dashboard_service.service.DashboardService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/resumen")
    public ResumenResponse resumen() {
        return dashboardService.obtenerResumen();
    }

    @GetMapping("/citas/estado")
    public List<ConteoEstadoResponse> citasPorEstado() {
        return dashboardService.citasPorEstado();
    }

    @GetMapping("/servicios/top")
    public List<ServicioTopResponse> serviciosTop() {
        return dashboardService.serviciosTop();
    }

    @GetMapping("/barberos/ranking")
    public List<BarberoRankingResponse> rankingBarberos() {
        return dashboardService.rankingBarberos();
    }

    @GetMapping("/ingresos")
    public Map<String, Double> ingresos() {
        return dashboardService.ingresos();
    }
}
