package com.empresa.barber_dashboard_service.client;

import com.empresa.barber_dashboard_service.model.Barbero;
import com.empresa.barber_dashboard_service.model.Cita;
import com.empresa.barber_dashboard_service.model.Cliente;
import com.empresa.barber_dashboard_service.model.Servicio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class BarberServiceClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public BarberServiceClient(RestTemplate restTemplate,
                               @Value("${barber.service.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public List<Cliente> listarClientes() {
        try {
            Cliente[] clientes = restTemplate.getForObject(baseUrl + "/clientes", Cliente[].class);
            return List.of(clientes != null ? clientes : new Cliente[0]);
        } catch (Exception e) {
            System.out.println("Error al obtener clientes: " + e.getMessage());
            return List.of();
        }
    }

    public List<Barbero> listarBarberos() {
        try {
            Barbero[] barberos = restTemplate.getForObject(baseUrl + "/barberos", Barbero[].class);
            return List.of(barberos != null ? barberos : new Barbero[0]);
        } catch (Exception e) {
            System.out.println("Error al obtener barberos: " + e.getMessage());
            return List.of();
        }
    }

    public List<Servicio> listarServicios() {
        try {
            Servicio[] servicios = restTemplate.getForObject(baseUrl + "/servicios", Servicio[].class);
            return List.of(servicios != null ? servicios : new Servicio[0]);
        } catch (Exception e) {
            System.out.println("Error al obtener servicios: " + e.getMessage());
            return List.of();
        }
    }

    public List<Cita> listarCitas() {
        try {
            Cita[] citas = restTemplate.getForObject(baseUrl + "/citas", Cita[].class);
            return List.of(citas != null ? citas : new Cita[0]);
        } catch (Exception e) {
            System.out.println("Error al obtener citas: " + e.getMessage());
            return List.of();
        }
    }
}
