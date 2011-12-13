package com.groupD.corepackage;

/**
 * @author madina
 */
public class Token
{
    private int position;
    private String color;
    public Token(String color)
    {
       this.position = 1;
       this.color = color;
    }
    
    public String getColor()
    {
       return this.color;
    }
    
    public int getPosition()
    {
       return this.position;
    }
    
    public void move(int to)
    {
       this.position = to;
    }
}
