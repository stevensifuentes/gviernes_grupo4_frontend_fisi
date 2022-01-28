package android.project.clinicapp.models;

public class CitaProgramada {
    private String especialidad;
    private String fecha;
    private String hora;
    private Integer historial_id;

    public CitaProgramada(String especialidad, String fecha, String hora, Integer historial_id) {
        this.especialidad = especialidad;
        this.fecha = fecha;
        this.hora = hora;
        this.historial_id = historial_id;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Integer getHistorial_id() {
        return historial_id;
    }

    public void setHistorial_id(Integer historial_id) {
        this.historial_id = historial_id;
    }
}
