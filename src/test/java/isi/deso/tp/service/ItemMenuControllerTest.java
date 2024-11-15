/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package isi.deso.tp.service;

import isi.deso.tp.model.ItemMenu;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Nacho
 */
public class ItemMenuControllerTest {
    
    public ItemMenuControllerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of listarItemMenu method, of class ItemMenuController.
     */
    @Test
    public void testListarItemMenu() {
        System.out.println("listarItemMenu");
        ItemMenuController instance = new ItemMenuController();
        List<ItemMenu> expResult = null;
        List<ItemMenu> result = instance.listarItemMenu();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of crearItemMenu method, of class ItemMenuController.
     */
    @Test
    public void testCrearItemMenu() {
        System.out.println("crearItemMenu");
        ItemMenu itemMenu = null;
        ItemMenuController instance = new ItemMenuController();
        instance.crearItemMenu(itemMenu);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarItemMenu method, of class ItemMenuController.
     */
    @Test
    public void testActualizarItemMenu() {
        System.out.println("actualizarItemMenu");
        ItemMenu itemMenu = null;
        ItemMenuController instance = new ItemMenuController();
        instance.actualizarItemMenu(itemMenu);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarItemMenu method, of class ItemMenuController.
     */
    @Test
    public void testEliminarItemMenu() {
        System.out.println("eliminarItemMenu");
        int idItemMenu = 0;
        ItemMenuController instance = new ItemMenuController();
        instance.eliminarItemMenu(idItemMenu);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarItemMenu method, of class ItemMenuController.
     */
    @Test
    public void testBuscarItemMenu() {
        System.out.println("buscarItemMenu");
        int idItemMenu = 0;
        ItemMenuController instance = new ItemMenuController();
        List<ItemMenu> expResult = null;
        List<ItemMenu> result = instance.buscarItemMenu(idItemMenu);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarItemPorNombre method, of class ItemMenuController.
     */
    @Test
    public void testBuscarItemPorNombre() {
        System.out.println("buscarItemPorNombre");
        String nombre = "";
        ItemMenuController instance = new ItemMenuController();
        ItemMenu expResult = null;
        ItemMenu result = instance.buscarItemPorNombre(nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
