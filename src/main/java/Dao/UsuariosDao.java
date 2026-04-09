/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import conn.Conexion;
import modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mouli
 */
public class UsuariosDao {

    public List<Usuario> listarUsuario() { // Simplifica a sin parámetros para carga general
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT id_usuario, Nombre, Email, tipo_usuario FROM usuarios";

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("Nombre"),
                        rs.getString("Email"),
                        rs.getString("tipo_usuario")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
        }
        return lista;
    }

    public boolean agregarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (Nombre, Email, tipo_usuario) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getTipo_usuario());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al agregar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean modificarUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET Nombre = ?, Email = ?, tipo_usuario = ? WHERE id_usuario = ?";
        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getTipo_usuario());
            ps.setInt(4, usuario.getId_usuario());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

}
