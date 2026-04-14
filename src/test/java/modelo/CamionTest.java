/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package modelo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mouli
 */
public class CamionTest {
    @Test
    void testGettersAndSetters() {
        Camion c = new Camion();
        c.setId_camion(1);
        c.setPatente("ABCD-12");
        c.setEstado(Camion.Estado.Disponible);
        
        assertEquals(1, c.getId_camion());
        assertEquals("ABCD-12", c.getPatente());
        assertEquals(Camion.Estado.Disponible, c.getEstado());
    }
}