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
public class BoardTest {

    Board standardBoard;
    Board customBoard;
    int upperA[] = {5, 14, 69, 188, 157};
    int upperB[] = {91, 52, 145, 200, 217};
    int upperS[] = {1, 2, 3, 4, 5};
    int downerA[] = {15, 56, 145, 199, 225};
    int downerB[] = {3, 27, 30, 74, 40};
    int downerS[] = {-1, -2, -3, -4, -5};
    int bonus[] = {101, 201};
    int bonusSc[] = {10, 20};

    public BoardTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        standardBoard = new Board();
        customBoard = new Board(15, 15, upperA, upperB, upperS, downerA, downerB, downerS, bonus, bonusSc); //board size = 225
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getCell method, of class Board.
     */
    @Test
    public void testGetCell() {
        System.out.println("getCell");
        assertEquals(24, standardBoard.getCell(24).getCellNumber());
        assertEquals(100, standardBoard.getCell(100).getCellNumber());
        assertEquals(200, customBoard.getCell(200).getCellNumber());
        //assertEquals(101,standardBoard.getCell(101).getCellNumber()); 
        // Since the standard board contain only 100 cells, the last code
        // which is trying to get cell 101, will throw an arrayoutofbound exception. 
    }

    /**
     * Test of getUppers method, of class Board.
     */
    @Test
    public void testGetUppers() {
        System.out.println("getUppers");
        assertEquals(2, standardBoard.getUppers()[0]);
        assertEquals(28, standardBoard.getUppers()[1]);
        assertEquals(188, customBoard.getUppers()[3]);
    }

    /**
     * Test of getDowners method, of class Board.
     */
    @Test
    public void testGetDowners() {
        System.out.println("getDownerssssssssss");
        assertEquals(26, standardBoard.getDowners()[0]);
        assertEquals(56, standardBoard.getDowners()[1]);
        assertEquals(199, customBoard.getDowners()[3]);
    }

    /**
     * Test of getBonuses method, of class Board.
     */
    @Test
    public void testGetBonuses() {
        System.out.println("getBonuses");
        assertEquals(101, customBoard.getBonuses()[0]);
    }

    /**
     * Test of occupyCell method, of class Board.
     */
    @Test
    public void testOccupyCell() {
        System.out.println("occupyCell");
        assertFalse(standardBoard.getCell(12).isOccupied());
        standardBoard.occupyCell(12);
        assertTrue(standardBoard.getCell(12).isOccupied());
        //assertFalse(standardBoard.getCell(12).isOccupied());
    }

    /**
     * Test of leaveCell method, of class Board.
     */
    @Test
    public void testLeaveCell() {
        System.out.println("leaveCell");
        assertFalse(standardBoard.getCell(12).isOccupied());
        standardBoard.occupyCell(12);
        assertTrue(standardBoard.getCell(12).isOccupied());
        standardBoard.leaveCell(12);
        assertFalse(standardBoard.getCell(12).isOccupied());
    }

    /**
     * Test of getRows method, of class Board.
     */
    @Test
    public void testGetRows() {
        System.out.println("getRows");
        assertEquals(10, standardBoard.getRows());
    }

    /**
     * Test of setRows method, of class Board.
     */
    @Test
    public void testSetRows() {
        System.out.println("setRows");
        assertEquals(10, standardBoard.getRows());
        standardBoard.setRows(15);
        assertEquals(15, standardBoard.getRows());
    }

    /**
     * Test of getColumns method, of class Board.
     */
    @Test
    public void testGetColumns() {
        System.out.println("getColumns");
        assertEquals(10, standardBoard.getColumns());
    }

    /**
     * Test of setColumns method, of class Board.
     */
    @Test
    public void testSetColumns() {
        System.out.println("setColumns");
        assertEquals(10, standardBoard.getColumns());
        standardBoard.setColumns(13);
        assertEquals(13, standardBoard.getColumns());
    }

    /**
     * Test of getUpperScores method, of class Board.
     */
    @Test
    public void testGetUpperScores() {
        System.out.println("getUpperScores");
        //int expectedArray[] = {2,2,3,4,5}; // This will fail the test: "arrays first differed at elemetn [0]"
        int expectedArray[] = {1, 2, 3, 4, 5};
        assertArrayEquals(expectedArray, customBoard.getUpperScores());
    }

    /**
     * Test of setUpperScores method, of class Board.
     */
    @Test
    public void testSetUpperScores() {
        System.out.println("setUpperScores");
        int expectedArray[] = {1, 2, 3, 4, 5};
        assertArrayEquals(expectedArray, customBoard.getUpperScores());
        int changeArray[] = {9, 8, 7, 6, 5};
        customBoard.setUpperScores(changeArray);
        assertArrayEquals(changeArray, customBoard.getUpperScores());
    }

    /**
     * Test of getDownerScores method, of class Board.
     */
    @Test
    public void testGetDownerScores() {
        System.out.println("getDownerScores");
        int expectedArray[] = {-1, -2, -3, -4, -5};
        assertArrayEquals(expectedArray, customBoard.getDownerScores());
    }

    /**
     * Test of setDownerScores method, of class Board.
     */
    @Test
    public void testSetDownerScores() {
        System.out.println("setDownerScores");
        //int expectedArray[] = {-1,-2,-3,-4,-5};
        int changeArray[] = {-9, 8, -4, 6, -5};
        customBoard.setDownerScores(changeArray);
        assertArrayEquals(changeArray, customBoard.getDownerScores());
    }

    /**
     * Test of getBonusScores method, of class Board.
     */
    @Test
    public void testGetBonusScores() {
        System.out.println("getBonusScores");
        int expectedArray[] = {10, 20};
        //int expectedArray[] = {11,20};
        assertArrayEquals(expectedArray, customBoard.getBonusScores());
        int changeArray[] = {9, 8, 7, 6, 5};
        customBoard.setBonusScores(changeArray);
        assertArrayEquals(changeArray, customBoard.getBonusScores());
    }

    /**
     * Test of setBonusScores method, of class Board.
     */
    @Test
    public void testSetBonusScores() {
        System.out.println("setBonusScores");
        int expectedArray[] = {10, 20};
        //int expectedArray[] = {11,20};
        assertArrayEquals(expectedArray, customBoard.getBonusScores());
        int changeArray[] = {9, 8, 7, 6, 5};
        customBoard.setBonusScores(changeArray);
        assertArrayEquals(changeArray, customBoard.getBonusScores());
    }
}