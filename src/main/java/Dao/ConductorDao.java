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

        String sql = "SELECT c.id_conductor, c.rut, c.nombre, c.apellido, "
                + "c.tipo_licencia, c.telefono, c.id_camion "
                + "FROM conductores c "
                + "LEFT JOIN camiones cam ON c.id_camion = cam.id_camion";

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

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

        String sqlCheck = "SELECT COUNT(*) FROM conductores WHERE rut = ?";
        String sqlInsert = "INSERT INTO conductores (rut, nombre, apellido, tipo_licencia, telefono) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection()) {

            try (PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {
                psCheck.setString(1, conductor.getRut());
                ResultSet rs = psCheck.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    return false;
                }
            }

            try (PreparedStatement psInsert = conn.prepareStatement(sqlInsert)) {
                psInsert.setString(1, conductor.getRut());
                psInsert.setString(2, conductor.getNombre());
                psInsert.setString(3, conductor.getApellido());
                psInsert.setString(4, conductor.getTipo_licencia());
                psInsert.setString(5, conductor.getTelefono());
                return psInsert.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al agregar conductor: " + e.getMessage());
            return false;
        }
    }

    public boolean modificarConductor(Conductor conductor) {
        String sql = "UPDATE conductores SET rut = ?, nombre = ?, apellido = ?, tipo_licencia = ?, telefono = ? WHERE id_conductor = ?";

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

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

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar el conductor: " + e.getMessage());
            return false;
        }
    }

    public boolean asignarCamion(int idConductor, int idCamion) {

        String sqlGetAnterior = "SELECT id_camion FROM conductores WHERE id_conductor = ?";

        String sqlLiberarAnterior = "UPDATE camiones SET estado = 'Disponible' WHERE id_camion = ?";

        String sqlAsignarNuevo = "UPDATE conductores SET id_camion = ? WHERE id_conductor = ?";

        String sqlMarcarAsignado = "UPDATE camiones SET estado = 'Asignado' WHERE id_camion = ?";

        try (Connection conn = Conexion.getConnection()) {
            conn.setAutoCommit(false); // Iniciamos transacción

            try (PreparedStatement psGet = conn.prepareStatement(sqlGetAnterior); PreparedStatement psLiberar = conn.prepareStatement(sqlLiberarAnterior); PreparedStatement psAsignar = conn.prepareStatement(sqlAsignarNuevo); PreparedStatement psMarcar = conn.prepareStatement(sqlMarcarAsignado)) {

                // 1. Obtener ID del camión anterior
                psGet.setInt(1, idConductor);
                ResultSet rs = psGet.executeQuery();
                if (rs.next()) {
                    int idAnterior = rs.getInt("id_camion");
                    // 2. Si tenía un camión (id > 0) y es distinto al nuevo, lo liberamos
                    if (idAnterior > 0 && idAnterior != idCamion) {
                        psLiberar.setInt(1, idAnterior);
                        psLiberar.executeUpdate();
                    }
                }

                // 3. Actualizar el conductor con el nuevo camión
                psAsignar.setInt(1, idCamion); // idCamion puede ser 0 si se elige "Sin Camión"
                psAsignar.setInt(2, idConductor);
                psAsignar.executeUpdate();

                // 4. Si se asignó un camión real, cambiar su estado a 'Asignado'
                if (idCamion > 0) {
                    psMarcar.setInt(1, idCamion);
                    psMarcar.executeUpdate();
                }

                conn.commit(); // Guardar todos los cambios
                return true;

            } catch (SQLException e) {
                conn.rollback(); // Si algo falla, deshacer todo
                System.err.println("Error en la transacción de asignación: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
            return false;
        }
    }

}
