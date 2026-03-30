/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import conn.Conexion;
import modelo.Camion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Santo Tomas
 */
public class CamionesDao {
    public List<Camion> listarCamiones(Integer id, String patente, String marca,
                                       String modelo, Integer anio) {
        List<Camion> lista = new ArrayList<>();
        String sql = "SELECT id_camion, patente, marca, modelo, anio, kilometraje_acumulado FROM camiones";
        
        ArrayList<String> conditions = new ArrayList<>();
        ArrayList<Object> params = new ArrayList<>();

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Camion(
                        rs.getInt("id_camion"),
                        rs.getString("Patente"),
                        rs.getString("Marca"),
                        rs.getString("Modelo"),
                        rs.getInt("Anio"),
                        rs.getFloat("Kilometraje_acumulado")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar ciudades: " + e.getMessage());
        }
        return lista;
    }
    public boolean agregarCamion(Camion camion) {
        String sql = "INSERT INTO camiones (patente, marca, modelo, anio) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, camion.getPatente());
            ps.setString(2, camion.getMarca());
            ps.setString(3, camion.getModelo());
            ps.setInt(4, camion.getAnio());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al agregar el camión: " + e.getMessage());
            return false;
        }
    }

    // 3. Método para MODIFICAR un país
    /*public boolean modificarPais(Pais pais, String codigoOriginal) {
        String sql = "UPDATE country SET Code = ?, Name = ?, Continent = ?, Population = ? WHERE Code = ?";

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            // AQUÍ TAMBIÉN CORREGIMOS
            ps.setString(1, pais.getCode());
            ps.setString(2, pais.getName());
            ps.setString(3, pais.getContinent());

            String pobTexto = pais.getPopulation().replace(".", "").replace(",", "").trim();
            if (pobTexto.isEmpty()) {
                ps.setInt(4, 0);
            } else {
                ps.setInt(4, Integer.parseInt(pobTexto));
            }

            ps.setString(5, codigoOriginal);

            return ps.executeUpdate() > 0;

        } catch (SQLException | NumberFormatException e) {
            System.err.println("Error al modificar país: " + e.getMessage());
            return false;
        }
    }*/
}
