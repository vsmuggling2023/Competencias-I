/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Santo Tomas
 */
public class Camion {
    private int id_camion;
    private String Patente;
    private String Marca;
    private String Modelo;
    private int Anio;
    private float Kilometro_acumulado;
    public enum Estado{Disponible, Asignado, Mantenimiento}
    
    private Estado estado;
    public Camion() {
    }

    public Camion(int id_camion, String Patente, String Marca, String Modelo, int Anio, float Kilometro_acumulado, Estado estado) {
        this.id_camion = id_camion;
        this.Patente = Patente;
        this.Marca = Marca;
        this.Modelo = Modelo;
        this.Anio = Anio;
        this.Kilometro_acumulado = Kilometro_acumulado;
        this.estado = estado;
    }

    public int getId_camion() {
        return id_camion;
    }

    public void setId_camion(int id_camion) {
        this.id_camion = id_camion;
    }

    public String getPatente() {
        return Patente;
    }

    public void setPatente(String Patente) {
        this.Patente = Patente;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public int getAnio() {
        return Anio;
    }

    public void setAnio(int Anio) {
        this.Anio = Anio;
    }

    public float getKilometro_acumulado() {
        return Kilometro_acumulado;
    }

    public void setKilometro_acumulado(float Kilometro_acumulado) {
        this.Kilometro_acumulado = Kilometro_acumulado;
    }
    
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
