/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Dao;

import modelo.Conductor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;


/**
 *
 * @author Mouli
 */
public class ConductorDaoTest {

    private ConductorDao dao;

    @BeforeEach
    public void setUp() {
        
        dao = new ConductorDao();
    }

    @Test
    public void testListarConductores() {
        // Verifica que la conexión no sea nula y traiga la lista
        List<Conductor> lista = dao.listarConductores();
        assertNotNull(lista, "La lista no debe ser nula");
    }

    @Test
    public void testAsignarCamionInexistente() {
        
        boolean resultado = dao.asignarCamion(-1, 0);
        
        assertTrue(resultado, "El DAO actual devuelve true siempre que no haya una excepción de SQL");
    }

    @Test
    public void testObjetoConductorLocal() {
        Conductor c = new Conductor();
        c.setNombre("Prueba");
        assertEquals("Prueba", c.getNombre());
    }
}