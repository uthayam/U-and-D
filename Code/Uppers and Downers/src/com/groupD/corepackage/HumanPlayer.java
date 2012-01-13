package com.groupD.corepackage;

/**
 * @author fayimora
 */
public class HumanPlayer extends Player 
{
    public HumanPlayer(String name, Token token)
    {
        super(name,token);
    }

	public HumanPlayer(String name, Token token, int totalScore, int highestScore, int gamesPlayed, int gamesWon) {
        super(name, token, totalScore, highestScore, gamesPlayed, gamesWon);
    }
    
    public void moveToken(int to)
    {
        super.moveToken(to);
    }
    
}

