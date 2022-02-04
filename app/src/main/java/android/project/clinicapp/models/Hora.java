package android.project.clinicapp.models;

public class Hora {
    private int hora;
    private Boolean disponible;

    public Hora(int hora, Boolean disponible) {
        this.hora = hora;
        this.disponible = disponible;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
}
