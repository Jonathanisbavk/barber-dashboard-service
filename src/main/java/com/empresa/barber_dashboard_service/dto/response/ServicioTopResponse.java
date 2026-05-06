package com.empresa.barber_dashboard_service.dto.response;

public class ServicioTopResponse {

    private Long servicioId;
    private String nombre;
    private double precio;
    private long cantidadCitas;
    private double ingresoGenerado;

    public ServicioTopResponse() {}

    public ServicioTopResponse(Long servicioId, String nombre, double precio,
                               long cantidadCitas, double ingresoGenerado) {
        this.servicioId = servicioId;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadCitas = cantidadCitas;
        this.ingresoGenerado = ingresoGenerado;
    }

    public Long getServicioId() { return servicioId; }
    public void setServicioId(Long servicioId) { this.servicioId = servicioId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public long getCantidadCitas() { return cantidadCitas; }
    public void setCantidadCitas(long cantidadCitas) { this.cantidadCitas = cantidadCitas; }

    public double getIngresoGenerado() { return ingresoGenerado; }
    public void setIngresoGenerado(double ingresoGenerado) { this.ingresoGenerado = ingresoGenerado; }
}
