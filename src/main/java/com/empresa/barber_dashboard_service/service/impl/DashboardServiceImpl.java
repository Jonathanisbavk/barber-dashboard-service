package com.empresa.barber_dashboard_service.service.impl;

import com.empresa.barber_dashboard_service.client.BarberServiceClient;
import com.empresa.barber_dashboard_service.dto.response.BarberoRankingResponse;
import com.empresa.barber_dashboard_service.dto.response.ConteoEstadoResponse;
import com.empresa.barber_dashboard_service.dto.response.ResumenResponse;
import com.empresa.barber_dashboard_service.dto.response.ServicioTopResponse;
import com.empresa.barber_dashboard_service.model.Barbero;
import com.empresa.barber_dashboard_service.model.Cita;
import com.empresa.barber_dashboard_service.model.Servicio;
import com.empresa.barber_dashboard_service.service.DashboardService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    private static final String ESTADO_COMPLETADA = "COMPLETADA";
    private static final String ESTADO_PENDIENTE = "PENDIENTE";

    private final BarberServiceClient client;

    public DashboardServiceImpl(BarberServiceClient client) {
        this.client = client;
    }

    @Override
    public ResumenResponse obtenerResumen() {
        List<Cita> citas = client.listarCitas();
        Map<Long, Servicio> servicios = indexarServicios();

        long completadas = citas.stream()
                .filter(c -> ESTADO_COMPLETADA.equalsIgnoreCase(c.getEstado()))
                .count();
        long pendientes = citas.stream()
                .filter(c -> ESTADO_PENDIENTE.equalsIgnoreCase(c.getEstado()))
                .count();
        double ingresoTotal = citas.stream()
                .filter(c -> ESTADO_COMPLETADA.equalsIgnoreCase(c.getEstado()))
                .mapToDouble(c -> precioDe(c, servicios))
                .sum();

        ResumenResponse r = new ResumenResponse();
        r.setTotalClientes(client.listarClientes().size());
        r.setTotalBarberos(client.listarBarberos().size());
        r.setTotalServicios(servicios.size());
        r.setTotalCitas(citas.size());
        r.setCitasPendientes(pendientes);
        r.setCitasCompletadas(completadas);
        r.setIngresoTotal(ingresoTotal);
        return r;
    }

    @Override
    public List<ConteoEstadoResponse> citasPorEstado() {
        Map<String, Long> conteo = client.listarCitas().stream()
                .collect(Collectors.groupingBy(
                        c -> c.getEstado() == null ? "DESCONOCIDO" : c.getEstado().toUpperCase(),
                        Collectors.counting()));
        return conteo.entrySet().stream()
                .map(e -> new ConteoEstadoResponse(e.getKey(), e.getValue()))
                .sorted(Comparator.comparingLong(ConteoEstadoResponse::getCantidad).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<ServicioTopResponse> serviciosTop() {
        List<Cita> citas = client.listarCitas();
        Map<Long, Servicio> servicios = indexarServicios();

        Map<Long, Long> conteoPorServicio = citas.stream()
                .filter(c -> c.getServicioId() != null)
                .collect(Collectors.groupingBy(Cita::getServicioId, Collectors.counting()));

        return servicios.values().stream()
                .map(s -> {
                    long cantidad = conteoPorServicio.getOrDefault(s.getId(), 0L);
                    double precio = s.getPrecio() == null ? 0.0 : s.getPrecio();
                    return new ServicioTopResponse(s.getId(), s.getNombre(), precio,
                            cantidad, cantidad * precio);
                })
                .sorted(Comparator.comparingLong(ServicioTopResponse::getCantidadCitas).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<BarberoRankingResponse> rankingBarberos() {
        List<Cita> citas = client.listarCitas();
        List<Barbero> barberos = client.listarBarberos();

        Map<Long, Long> totalPorBarbero = citas.stream()
                .filter(c -> c.getBarberoId() != null)
                .collect(Collectors.groupingBy(Cita::getBarberoId, Collectors.counting()));

        Map<Long, Long> completadasPorBarbero = citas.stream()
                .filter(c -> c.getBarberoId() != null
                        && ESTADO_COMPLETADA.equalsIgnoreCase(c.getEstado()))
                .collect(Collectors.groupingBy(Cita::getBarberoId, Collectors.counting()));

        return barberos.stream()
                .map(b -> new BarberoRankingResponse(
                        b.getId(),
                        b.getNombre(),
                        b.getDisponible(),
                        totalPorBarbero.getOrDefault(b.getId(), 0L),
                        completadasPorBarbero.getOrDefault(b.getId(), 0L)))
                .sorted(Comparator.comparingLong(BarberoRankingResponse::getCantidadCitas).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Double> ingresos() {
        List<Cita> citas = client.listarCitas();
        Map<Long, Servicio> servicios = indexarServicios();

        double total = citas.stream()
                .mapToDouble(c -> precioDe(c, servicios))
                .sum();
        double completadas = citas.stream()
                .filter(c -> ESTADO_COMPLETADA.equalsIgnoreCase(c.getEstado()))
                .mapToDouble(c -> precioDe(c, servicios))
                .sum();
        double pendientes = citas.stream()
                .filter(c -> ESTADO_PENDIENTE.equalsIgnoreCase(c.getEstado()))
                .mapToDouble(c -> precioDe(c, servicios))
                .sum();

        Map<String, Double> r = new LinkedHashMap<>();
        r.put("ingresoTotal", total);
        r.put("ingresoCompletadas", completadas);
        r.put("ingresoPendiente", pendientes);
        return r;
    }

    private Map<Long, Servicio> indexarServicios() {
        return client.listarServicios().stream()
                .filter(s -> s.getId() != null)
                .collect(Collectors.toMap(Servicio::getId, Function.identity(),
                        (a, b) -> a, HashMap::new));
    }

    private double precioDe(Cita cita, Map<Long, Servicio> servicios) {
        if (cita.getServicioId() == null) return 0.0;
        Servicio s = servicios.get(cita.getServicioId());
        if (s == null || s.getPrecio() == null) return 0.0;
        return s.getPrecio();
    }
}
