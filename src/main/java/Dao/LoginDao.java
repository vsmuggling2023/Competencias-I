/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import conn.Conexion;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Usuario;


/**
 *
 * @author Mouli
 */
public class LoginDao {
    private String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error al encriptar contraseña", ex);
        }
    }

    /**
     * Valida si el usuario y contraseña son correctos.
     *
     * @param usuario El nombre de usuario ingresado.
     * @param password La contraseña en texto plano (se encriptará aquí dentro).
     * @return true si las credenciales son válidas, false si no.
     */
    public Usuario autenticar(String usuario, String password) {
        String passHash = sha256(password);
        String sql = "SELECT * FROM usuarios WHERE nombre = ? AND password = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, passHash);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId_usuario(rs.getInt("id_usuario"));
                    u.setNombre(rs.getString("nombre"));
                    u.setEmail(rs.getString("email"));
                    u.setTipo_usuario(rs.getString("tipo_usuario"));
                    return u;
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error SQL en LoginDao: " + e.getMessage());
            return null;
        }
}  
}
