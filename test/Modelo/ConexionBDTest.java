/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Equipo;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author caenhiro
 */
public class ConexionBDTest {
    
    public ConexionBDTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of buscaLogin method, of class ConexionBD.
     */
    @Test
    public void testBuscaLogin() {
        System.out.println("buscaLogin");
        String login = "";
        String pass = "";
        ConexionBD instance = new ConexionBD();
        String expResult = "";
        String result = instance.buscaLogin(login, pass);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscaNombreLogin method, of class ConexionBD.
     */
    @Test
    public void testBuscaNombreLogin() {
        System.out.println("buscaNombreLogin");
        String login_nombre = "";
        ConexionBD instance = new ConexionBD();
        ArrayList<String> expResult = null;
        ArrayList<String[]> result = instance.buscaNombreLogin(login_nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertaUsuario method, of class ConexionBD.
     */
    @Test
    public void testInsertaUsuario() {
        System.out.println("insertaUsuario");
        String login = "";
        String pass = "";
        String nombre = "";
        String categoria = "";
        ConexionBD instance = new ConexionBD();
        boolean expResult = false;
        boolean result = instance.insertaUsuario(login, pass, nombre, categoria);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertaEquipo method, of class ConexionBD.
     */
    @Test
    public void testInsertaEquipo() {
        System.out.println("insertaEquipo");
        int numeroInveInterInfo = 0;
        int numInvUnam = 0;
        String descrip = "";
        String modelo = "";
        String marca = "";
        String serie = "";
        String familia = "";
        String tipo = "";
        String prove = "";
        String clase = "";
        String uso = "";
        String nivel = "";
        String edoFisico = "";
        String area = "";
        String institu = "";
        String fecha = "";
        String responsable = "";
        ConexionBD instance = new ConexionBD();
        boolean expResult = false;
        boolean result = instance.insertaEquipo(numeroInveInterInfo, numInvUnam, descrip, modelo, marca, serie, familia, tipo, prove, clase, uso, nivel, edoFisico, area, institu, fecha, responsable);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscaEquipo method, of class ConexionBD.
     */
    @Test
    public void testBuscaEquipo() {
        System.out.println("buscaEquipo");
        int clave = 0;
        ConexionBD instance = new ConexionBD();
        ArrayList<Equipo> expResult = null;
        ArrayList<Equipo> result = instance.buscaEquipo(clave);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of regresaCatalogo method, of class ConexionBD.
     */
    @Test
    public void testRegresaCatalogo() {
        System.out.println("regresaCatalogo");
        String catalogo = "";
        ConexionBD instance = new ConexionBD();
        ArrayList expResult = null;
        ArrayList result = instance.regresaCatalogo(catalogo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class ConexionBD.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        ConexionBD.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
