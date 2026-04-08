package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // Datos de AlwaysData proporcionados
    private static final String HOST = "mysql-softimelody.alwaysdata.net";
    private static final String USER = "439733";
    private static final String PASS = "MaxRage1";
    private static final String DB = "softimelody_proyecto_camiones"; 
    
    private static final String URL = "jdbc:mysql://" + HOST + "/" + DB + "?useSSL=false&serverTimezone=UTC";

    public static Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error de conexión a AlwaysData: " + e.getMessage());
            return null;
        }
    }

    // Método estático para solucionar los errores en CamionesDao, ConductorDao, etc.
    public static Connection getConnection() {
        return conectar();
    }
}