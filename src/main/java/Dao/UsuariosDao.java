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
    public List<Usuario> listarCiudades(String idStr, String nombre, String distrito, String codigoPais, String poblacionStr) {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT id_usuario, Nombre, Email, tipo_usuario FROM usuarios";
        
        ArrayList<String> conditions = new ArrayList<>();
        ArrayList<Object> params = new ArrayList<>();

        /*Filtro ID
        if (idStr != null && !idStr.isEmpty() && !idStr.equals("Ingresa el ID")) {
            try {
                conditions.add("ID = ?");
                params.add(Integer.parseInt(idStr));
            } catch (NumberFormatException e) {}
        }
        // Filtro Nombre
        if (nombre != null && !nombre.isEmpty() && !nombre.equals("Ingresa el nombre")) {
            conditions.add("Name LIKE ?");
            params.add("%" + nombre + "%");
        }
        // Filtro Distrito
        if (distrito != null && !distrito.isEmpty() && !distrito.equals("Ingresa el Distrito")) {
            conditions.add("District LIKE ?");
            params.add("%" + distrito + "%");
        }
        // Filtro Código País
        if (codigoPais != null && !codigoPais.isEmpty() && !codigoPais.equals("Ingresa el código de pais")) {
            conditions.add("CountryCode LIKE ?");
            params.add(codigoPais + "%");
        }
        // Filtro Población
        if (poblacionStr != null && !poblacionStr.isEmpty() && !poblacionStr.equals("Ingresa la población")) {
            try {
                conditions.add("Population >= ?");
                params.add(Integer.parseInt(poblacionStr));
            } catch (NumberFormatException e) {}
        }

        if (!conditions.isEmpty()) {
            sql += " WHERE " + String.join(" AND ", conditions);
        }
        sql += " LIMIT 100";*/

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("Nombre"),
                        rs.getString("Email"),
                        rs.getString("tipo_usuario")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar ciudades: " + e.getMessage());
        }
        return lista;
    }
    
}
