package android.project.clinicapp.models;

public class ConsultarHoras {

    private String especialidad;
    private String fecha;

    public ConsultarHoras(String especialidad, String fecha) {
        this.especialidad = especialidad;
        this.fecha = fecha;
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
}
