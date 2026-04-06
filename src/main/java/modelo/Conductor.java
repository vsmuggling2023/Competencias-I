package modelo;

public class Conductor {
    private int id_conductor;
    private String rut;
    private String nombre;
    private String apellido;
    private String tipo_licencia;
    private String telefono;
    private int id_camion;

    public Conductor() {
    }

    public Conductor(int id_conductor, String rut, String nombre, String apellido, String tipo_licencia, String telefono, int id_camion) {
        this.id_conductor = id_conductor;
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipo_licencia = tipo_licencia;
        this.telefono = telefono;
        this.id_camion = id_camion;
    }

    public int getId_conductor() {
        return id_conductor;
    }

    public void setId_conductor(int id_conductor) {
        this.id_conductor = id_conductor;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipo_licencia() {
        return tipo_licencia;
    }

    public void setTipo_licencia(String tipo_licencia) {
        this.tipo_licencia = tipo_licencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public int getId_camion() {
        return id_camion;
    }

    public void setId_camion(int id_camion) {
        this.id_camion = id_camion;
    }

}