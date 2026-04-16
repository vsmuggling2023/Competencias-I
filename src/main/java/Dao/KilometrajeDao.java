/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import conn.Conexion;
import java.sql.*;

public class KilometrajeDao {

    public boolean registrarRecorrido(int idCamion, int idUsuario, double kmRecorridos) {
        String sqlInsert = "INSERT INTO kilometraje (id_camion, id_usuario, kilometros_recorridos) VALUES (?, ?, ?)";
        String sqlUpdate = "UPDATE camiones SET kilometraje_acumulado = kilometraje_acumulado + ? WHERE id_camion = ?";

        Connection conn = null;
        try {
            conn = Conexion.getConnection();
            conn.setAutoCommit(false); 

        
            PreparedStatement psInsert = conn.prepareStatement(sqlInsert);
            psInsert.setInt(1, idCamion);
            psInsert.setInt(2, idUsuario);
            psInsert.setDouble(3, kmRecorridos);
            psInsert.executeUpdate();

            
            PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
            psUpdate.setDouble(1, kmRecorridos);
            psUpdate.setInt(2, idCamion);
            psUpdate.executeUpdate();

            conn.commit();
            return true;
        } catch (SQLException e) {
            if (conn != null) try {
                conn.rollback();
            } catch (SQLException ex) {
            }
            System.err.println("Error al registrar kilometraje: " + e.getMessage());
            return false;
        }
    }
}
