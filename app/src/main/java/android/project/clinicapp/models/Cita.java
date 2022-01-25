package android.project.clinicapp.models;

public class Cita {
    private String especialidad;
    private String hora;
    private String fecha;
    private int disponible;
    private String historial_id;

    // Constructur para Mostrar Cita por usuario
    public Cita(String especialidad, String hora, String fecha, int disponible) {
        this.especialidad = especialidad;
        this.hora = hora;
        this.fecha = fecha;
        this.disponible = disponible;
    }

    // Constructor para Añadir Cita
    public Cita(String especialidad, String hora, String fecha, String historial_id) {
        this.especialidad = especialidad;
        this.hora = hora;
        this.fecha = fecha;
        this.historial_id = historial_id;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public String getHistorial_id() {
        return historial_id;
    }

    public void setHistorial_id(String historial_id) {
        this.historial_id = historial_id;
    }
}
