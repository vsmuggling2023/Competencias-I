package modelo;

import java.util.Date;

public class Mantenimiento {

    private int id_mantenimiento;
    private int id_camion;
    private String descripcion;
    private Date fecha;
    private double costo;

 
    public Mantenimiento() {
    }

    public int getId_mantenimiento() {
        return id_mantenimiento;
    }

    public void setId_mantenimiento(int id) {
        this.id_mantenimiento = id;
    }

    public int getId_camion() {
        return id_camion;
    }

    public void setId_camion(int id) {
        this.id_camion = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String desc) {
        this.descripcion = desc;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}
