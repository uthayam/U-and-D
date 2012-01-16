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
public class TokenTest {
    Token aToken;
    String color = "Red";
    
    public TokenTest() {
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
    public void testGetColor() {
        System.out.println("testGetColor");
        aToken= new Token(color);
        String expResult = "Red";
        String result = aToken.getColor();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetPosition() {
        System.out.println("testGetPosition");
        aToken= new Token(color);
        int expResult = 1;
        int result = aToken.getPosition();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetPosition() {
        System.out.println("testSetPosition");
        aToken= new Token(color);
        int expResult = 8;
        aToken.setPosition(8);
        int result = aToken.getPosition();
        assertEquals(expResult, result);
    }

    @Test
    public void testMove() {
        System.out.println("testMove");
        aToken= new Token(color);
        int expResult = 15;
        aToken.move(14);
        int result = aToken.getPosition();
        assertEquals(expResult, result);
    }
}
