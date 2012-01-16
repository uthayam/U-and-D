/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupD.uipackage.cli;

/**
 *
 * @author mesfin
 */
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class BoardFrame extends JFrame
{
	public BoardFrame()
	{
		super("Board Image");
		BoardImage panel = new BoardImage();
		this.getContentPane().add(panel);
		Image image = panel.getImage();
		this.setSize(image.getWidth(panel), image.getHeight(panel)+20);
		this.setVisible(true);
	}
}