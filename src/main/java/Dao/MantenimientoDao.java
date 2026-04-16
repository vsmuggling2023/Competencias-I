package Dao;

import java.sql.*;
import conn.Conexion;
import modelo.Mantenimiento;

public class MantenimientoDao {

    public boolean registrarMantenimiento(Mantenimiento m) {

        String sql = "INSERT INTO mantenimientos (id_camion, descripcion, fecha_mantenimiento, costo) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, m.getId_camion());
            ps.setString(2, m.getDescripcion());

            ps.setDate(3, new java.sql.Date(m.getFecha().getTime()));

            ps.setDouble(4, m.getCosto());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar mantenimiento: " + e.getMessage());
            return false;
        }

    }

    public java.util.List<Mantenimiento> listarPorCamion(int idCamion) {
        java.util.List<Mantenimiento> lista = new java.util.ArrayList<>();
        String sql = "SELECT id_mantenimiento, id_camion, descripcion, fecha_mantenimiento, costo FROM mantenimientos WHERE id_camion = ?";

        try (java.sql.Connection conn = Conexion.getConnection(); java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCamion);
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Mantenimiento m = new Mantenimiento();
                    m.setId_mantenimiento(rs.getInt("id_mantenimiento"));
                    m.setId_camion(rs.getInt("id_camion"));
                    m.setDescripcion(rs.getString("descripcion"));
                    m.setFecha(rs.getDate("fecha_mantenimiento"));
                    m.setCosto(rs.getDouble("costo"));
                    lista.add(m);
                }
            }
        } catch (java.sql.SQLException e) {
            System.err.println("Error al listar historial: " + e.getMessage());
        }
        return lista;

    }
}