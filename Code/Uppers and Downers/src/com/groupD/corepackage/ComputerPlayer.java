package com.groupD.corepackage;

/**
 *
 * @author fayimora
 */
public class ComputerPlayer extends Player
{
    static int count=0;
    private final String computerName = "ComputerPlayer"+(++count);
    
    public ComputerPlayer(String name, Token token)
    {
        super(name, token);
    }

    /*         AI         */
    public int getDecision(int n)
    {
	return n;
    }
}

