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
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class BoardImage extends JPanel
{
    private BufferedImage image;

    public BoardImage() 
    {
       	try 
	{                
            image = ImageIO.read(new File("board-image.jpg"));
       	}
 	catch (IOException ex) {}
    }

    public void paintComponent(Graphics g) 
    {
        g.drawImage(image, 0, 0, null);
    }

    public Image getImage()
    {
	return image;
    }
}