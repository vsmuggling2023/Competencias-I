/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import modelo.Usuario;
/**
 *
 * @author Mouli
 */
public class LoginDaoTest {
    @Test
    void testLoginFallaConDatosIncorrectos() {
        LoginDao loginDao = new LoginDao();
        // Intentamos logear con un usuario que no existe
        Usuario resultado = loginDao.autenticar("user_falso", "password123");
        assertNull(resultado, "El login debería fallar y retornar null");
    }
}
