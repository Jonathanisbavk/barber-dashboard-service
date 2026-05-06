package com.empresa.barber_dashboard_service.dto.response;

public class ResumenResponse {

    private long totalClientes;
    private long totalBarberos;
    private long totalServicios;
    private long totalCitas;
    private long citasPendientes;
    private long citasCompletadas;
    private double ingresoTotal;

    public long getTotalClientes() { return totalClientes; }
    public void setTotalClientes(long totalClientes) { this.totalClientes = totalClientes; }

    public long getTotalBarberos() { return totalBarberos; }
    public void setTotalBarberos(long totalBarberos) { this.totalBarberos = totalBarberos; }

    public long getTotalServicios() { return totalServicios; }
    public void setTotalServicios(long totalServicios) { this.totalServicios = totalServicios; }

    public long getTotalCitas() { return totalCitas; }
    public void setTotalCitas(long totalCitas) { this.totalCitas = totalCitas; }

    public long getCitasPendientes() { return citasPendientes; }
    public void setCitasPendientes(long citasPendientes) { this.citasPendientes = citasPendientes; }

    public long getCitasCompletadas() { return citasCompletadas; }
    public void setCitasCompletadas(long citasCompletadas) { this.citasCompletadas = citasCompletadas; }

    public double getIngresoTotal() { return ingresoTotal; }
    public void setIngresoTotal(double ingresoTotal) { this.ingresoTotal = ingresoTotal; }
}
