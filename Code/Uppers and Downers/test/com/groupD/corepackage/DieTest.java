/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupD.corepackage;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fayimora
 */
public class DieTest {
    Die aDie;
    
    public DieTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetNumberOfSides() {
        System.out.println("getNumberOfSides");
        aDie = new Die();
        int expResult = 6;
        int result = aDie.getNumberOfSides();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetNumberRolled() {

        System.out.println("getNumberRolled");
        aDie = new Die();
        aDie.setNumberRolled(5);
        assertEquals(aDie.getNumberRolled(), 5);



    }

     @Test
    public void testSetNumberRolled() {
        System.out.println("setNumberRolled");
        aDie = new Die();
        aDie.setNumberRolled(4);
        assertEquals(aDie.getNumberRolled(), 4);
    }

    @Test
    public void testRoll() {
        System.out.println("roll");
        Die instance = new Die();
        instance.roll();
        int result = instance.getNumberRolled();
        assertTrue(result>0);
        assertTrue(result<6);

    }
}
