package android.project.clinicapp.models;

public class Fecha {
    private int numberDia;
    private Boolean disponible;

    public Fecha(int numberDia, Boolean disponible) {
        this.numberDia = numberDia;
        this.disponible = disponible;
    }

    public int getNumberDia() {
        return numberDia;
    }

    public void setNumberDia(int numberDia) {
        this.numberDia = numberDia;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
}
