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

    public List<Camion> listarCamiones(Integer id, String patente, String marca, String modelo, Integer anio) {
        List<Camion> lista = new ArrayList<>();
        String sql = "SELECT id_camion, patente, marca, modelo, anio, kilometraje_acumulado, estado FROM camiones";

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Camion(
                        rs.getInt("id_camion"),
                        rs.getString("patente"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("anio"),
                        rs.getFloat("kilometraje_acumulado"),
                        Camion.Estado.valueOf(rs.getString("estado"))
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar camiones: " + e.getMessage());
        }
        return lista;
    }

    public boolean agregarCamion(Camion camion) {

        

        // 1. Consulta para ver si la patente ya existe
        String sqlCheck = "SELECT COUNT(*) FROM camiones WHERE patente = ?";
        String sqlInsert = "INSERT INTO camiones (patente, marca, modelo, anio, kilometraje_acumulado) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection()) {
            // Validación previa
            try (PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {
                psCheck.setString(1, camion.getPatente());
                ResultSet rs = psCheck.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    System.err.println("Error: La patente ya está registrada.");
                    return false; // Retorna falso si ya existe
                }
            }

            // Si no existe, procede con el insert
            try (PreparedStatement psInsert = conn.prepareStatement(sqlInsert)) {
                psInsert.setString(1, camion.getPatente());
                psInsert.setString(2, camion.getMarca());
                psInsert.setString(3, camion.getModelo());
                psInsert.setInt(4, camion.getAnio());
                psInsert.setFloat(5, camion.getKilometro_acumulado());

                return psInsert.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }

    public boolean modificarCamion(Camion camion) {
        String sqlUpdate = "UPDATE camiones SET patente = ?, marca = ?, modelo = ?, anio = ?, kilometraje_acumulado = ?, estado = ? WHERE id_camion = ?";

        try (Connection conn = Conexion.getConnection(); PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {

            psUpdate.setString(1, camion.getPatente());
            psUpdate.setString(2, camion.getMarca());
            psUpdate.setString(3, camion.getModelo());
            psUpdate.setInt(4, camion.getAnio());

            // AQUÍ ESTÁ EL TRUCO: Pasamos el kilometraje que rescatamos en el formulario
            psUpdate.setFloat(5, camion.getKilometro_acumulado());

            psUpdate.setString(6, camion.getEstado().name());
            psUpdate.setInt(7, camion.getId_camion());

            return psUpdate.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarCamion(int id) {
        String sql = "DELETE FROM camiones WHERE id_camion = ?";

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar el camión: " + e.getMessage());
            return false;
        }
    }

}
