/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupD.uipackage;

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
@Suite.SuiteClasses({com.groupD.uipackage.cli.CliSuite.class, com.groupD.uipackage.gui.GuiSuite.class})
public class UipackageSuite {

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
