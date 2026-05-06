package com.empresa.barber_dashboard_service.dto.response;

public class BarberoRankingResponse {

    private Long barberoId;
    private String nombre;
    private Boolean disponible;
    private long cantidadCitas;
    private long citasCompletadas;

    public BarberoRankingResponse() {}

    public BarberoRankingResponse(Long barberoId, String nombre, Boolean disponible,
                                  long cantidadCitas, long citasCompletadas) {
        this.barberoId = barberoId;
        this.nombre = nombre;
        this.disponible = disponible;
        this.cantidadCitas = cantidadCitas;
        this.citasCompletadas = citasCompletadas;
    }

    public Long getBarberoId() { return barberoId; }
    public void setBarberoId(Long barberoId) { this.barberoId = barberoId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Boolean getDisponible() { return disponible; }
    public void setDisponible(Boolean disponible) { this.disponible = disponible; }

    public long getCantidadCitas() { return cantidadCitas; }
    public void setCantidadCitas(long cantidadCitas) { this.cantidadCitas = cantidadCitas; }

    public long getCitasCompletadas() { return citasCompletadas; }
    public void setCitasCompletadas(long citasCompletadas) { this.citasCompletadas = citasCompletadas; }
}
