package Dao;

import conn.Conexion;
import modelo.Conductor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConductorDao {

    public List<Conductor> listarConductores() {
        List<Conductor> lista = new ArrayList<>();

        String sql = "SELECT c.id_conductor, c.rut, c.nombre, c.apellido, " +
              "c.tipo_licencia, c.telefono, c.id_camion " +
              "FROM conductores c " +
              "LEFT JOIN camiones cam ON c.id_camion = cam.id_camion";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Conductor(
                    rs.getInt("id_conductor"),
                    rs.getString("rut"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("tipo_licencia"),
                    rs.getString("telefono"),
                    rs.getInt("id_camion")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar conductores: " + e.getMessage());
        }

        return lista;
    }

    public boolean agregarConductor(Conductor conductor) {
        String sql = "INSERT INTO conductores (rut, nombre, apellido, tipo_licencia, telefono) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, conductor.getRut());
            ps.setString(2, conductor.getNombre());
            ps.setString(3, conductor.getApellido());
            ps.setString(4, conductor.getTipo_licencia());
            ps.setString(5, conductor.getTelefono());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al agregar el conductor: " + e.getMessage());
            return false;
        }
    }

    public boolean modificarConductor(Conductor conductor) {
        String sql = "UPDATE conductores SET rut = ?, nombre = ?, apellido = ?, tipo_licencia = ?, telefono = ? WHERE id_conductor = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, conductor.getRut());
            ps.setString(2, conductor.getNombre());
            ps.setString(3, conductor.getApellido());
            ps.setString(4, conductor.getTipo_licencia());
            ps.setString(5, conductor.getTelefono());
            ps.setInt(6, conductor.getId_conductor());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al modificar el conductor: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarConductor(int id) {
        String sql = "DELETE FROM conductores WHERE id_conductor = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar el conductor: " + e.getMessage());
            return false;
        }
    }
    //Es booleano ya que el resultado es si se asigna o no.
    public boolean asignarCamion(int idConductor, int idCamion) {
        String sql1 = "UPDATE conductores SET id_camion = ? WHERE id_conductor = ?";
        String sql2 = "UPDATE camiones SET estado = 'Asignado' WHERE id_camion = ?";

        try (Connection conn = Conexion.getConnection()) {
            conn.setAutoCommit(false); // transacción

            try (PreparedStatement ps1 = conn.prepareStatement(sql1);
                 PreparedStatement ps2 = conn.prepareStatement(sql2)) {

                // actualizar conductor
                ps1.setInt(1, idCamion);
                ps1.setInt(2, idConductor);
                ps1.executeUpdate();

                // actualizar estado del camión
                ps2.setInt(1, idCamion);
                ps2.executeUpdate();

                conn.commit();
                return true;

            } catch (SQLException e) {
                conn.rollback();
                System.err.println("Error al asignar camión: " + e.getMessage());
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
            return false;
        }
    }
    
}