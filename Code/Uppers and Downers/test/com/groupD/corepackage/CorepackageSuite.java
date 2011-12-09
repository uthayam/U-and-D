/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupD.corepackage;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author fayimora
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({com.groupD.corepackage.BoardTest.class, com.groupD.corepackage.ComputerPlayerTest.class, com.groupD.corepackage.CellTest.class, com.groupD.corepackage.PlayerTest.class, com.groupD.corepackage.HumanPlayerTest.class, com.groupD.corepackage.SpecialCellTest.class, com.groupD.corepackage.DieTest.class, com.groupD.corepackage.TokenTest.class, com.groupD.corepackage.GameTest.class})
public class CorepackageSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
