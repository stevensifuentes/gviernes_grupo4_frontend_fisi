package android.project.clinicapp.models;

public class Solicitud {
    private String nombre;
    private String apellidos;
    private Integer historial_id;
    private String email;

    public Solicitud(String nombre, String apellidos, Integer historial_id, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.historial_id = historial_id;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellido(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getHistorial_id() {
        return historial_id;
    }

    public void setHistorial_id(Integer historial_id) {
        this.historial_id = historial_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
