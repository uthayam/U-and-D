package com.groupD.corepackage;

/**
 *
 * @author fayimora
 */
public class ComputerPlayer extends Player
{
    static int count=0;
    private final String computerName = "ComputerPLayer"+(++count);
    
    public ComputerPlayer(String name, Token token)
    {
        super(name, token);
    }
}
