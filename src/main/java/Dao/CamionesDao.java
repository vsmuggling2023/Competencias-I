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
        String sql = "SELECT id_camion, patente, marca, modelo, anio, kilometraje_acumulado, estado FROM camiones";

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
                        rs.getString("patente"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("anio"),
                        rs.getFloat("kilometraje_acumulado"),
                        Camion.Estado.valueOf(rs.getString("estado")) // <-- aquí agregamos el estado
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar camiones: " + e.getMessage());
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

    public boolean modificarCamion(Camion camion) {
        String sql = "UPDATE camiones SET patente = ?, marca = ?, modelo = ?, anio = ?, kilometraje_acumulado = ?, estado = ? WHERE id_camion = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, camion.getPatente());
            ps.setString(2, camion.getMarca());
            ps.setString(3, camion.getModelo());
            ps.setInt(4, camion.getAnio());
            ps.setFloat(5, camion.getKilometro_acumulado());

            // Estado (enum → String)
            ps.setString(6, camion.getEstado().name());

            // ID
            ps.setInt(7, camion.getId_camion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al modificar el camión: " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminarCamion(int id) {
        String sql = "DELETE FROM camiones WHERE id_camion = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar el camión: " + e.getMessage());
            return false;
        }
    }
}
