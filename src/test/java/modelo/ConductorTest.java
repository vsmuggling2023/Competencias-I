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
public class ConductorTest {
    @Test
    void testConstructorFull() {
        Conductor c = new Conductor(1, "12.345.678-9", "Juan", "Perez", "A4", "99887766", 5);
        assertEquals("Juan", c.getNombre());
        assertEquals(5, c.getId_camion());
    }
}
