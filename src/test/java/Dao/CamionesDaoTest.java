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
import modelo.Camion;

/**
 *
 * @author Mouli
 */
public class CamionesDaoTest {
    @Test
    void testAgregarCamionLocal() {
        CamionesDao dao = new CamionesDao();
        Camion c = new Camion(0, "TRUX-50", "Volvo", "FH", 2024, 0, Camion.Estado.Disponible);
        
        // Verificamos que los datos del objeto a insertar son correctos
        assertEquals("TRUX-50", c.getPatente());
        assertNotNull(dao);
    }
}
