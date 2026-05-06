package com.empresa.barber_dashboard_service.model;

public class Cita {

    private Long id;
    private Long clienteId;
    private Long barberoId;
    private Long servicioId;
    private String fecha;
    private String hora;
    private String estado;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public Long getBarberoId() { return barberoId; }
    public void setBarberoId(Long barberoId) { this.barberoId = barberoId; }

    public Long getServicioId() { return servicioId; }
    public void setServicioId(Long servicioId) { this.servicioId = servicioId; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
