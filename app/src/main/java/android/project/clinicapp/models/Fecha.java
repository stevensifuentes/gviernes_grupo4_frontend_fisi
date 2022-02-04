package android.project.clinicapp.models;

public class Fecha {
    private int dia;
    private Boolean disponible;

    public Fecha(int dia, Boolean disponible) {
        this.dia = dia;
        this.disponible = disponible;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
}
