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
}
