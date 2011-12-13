package com.groupD.corepackage;

/**
 *
 * @author Mesfin
 */
public class Game 
{
    private Die die;
    private Player p1;
    private Player p2;
    private Board board;
    
    public Game()
    {
        die = new Die();
        p1 = new HumanPlayer("human", new Token("red"));
        p2 = new ComputerPlayer("computer", new Token("blue"));
        board = new Board();
    }
    
    public Game(String name)
    {
        die = new Die();
        p1 = new HumanPlayer(name, new Token("red"));
        p2 = new ComputerPlayer("computer", new Token("blue"));
        board = new Board();
    }
    
    public Game(String name1, String name2)
    {
        die = new Die();
        p1 = new HumanPlayer(name1, new Token("red"));
        p2 = new ComputerPlayer(name2, new Token("blue"));
        board = new Board();
    }
    
    public Game(String name1, String name2, int rows, int columns)
    {
        die = new Die();
        p1 = new HumanPlayer(name1, new Token("red"));
        p2 = new ComputerPlayer(name2, new Token("blue"));
        board = new Board(rows, columns);
    }
    
    public void start()
    {
        die.roll();
        int result1 = die.getNumberRolled();
        die.roll();
        int result2 = die.getNumberRolled(); 
        while(result1==result2)
        {
            die.roll();
            result1 = die.getNumberRolled();
            die.roll();
            result2 = die.getNumberRolled();
        }
        if(result1>result2)
        {
            takeTurn(p1);
        }
        else
        {
            takeTurn(p2);
        }
    }
    
    ///////////////////////////////////////
    
    public void end(Player p)
    {
        p.updateScoreboard(true);
    }
    
    public void load(int id)
    {
        
    }
    
    public void save()
    {
    
    }
    
    public void takeTurn(Player p)
    {
        int rolled = die.getNumberRolled();
        Cell position = board.getCell(p.getToken().getPosition()+rolled);
        if(position.isOccupied())
        {
            p.moveToken(reposition(position.getCellNumber(), rolled));
        }
        else
        {
            p.moveToken(position.getCellNumber());
        }
        if(p.getToken().getPosition() == 100)
        {
            end(p);
        }
        else
        {
            if(p.getToken().getColor().equals("red"))
            {
                takeTurn(p2);
            }
            else
            {
                takeTurn(p1);
            }
        }
    }
    
    private int reposition(int p, int r)
    {
        for(int i=0; i<r; i++)
        {
            p -= 1;
            if(p < 1)
            {
                return p+1;
            }
        }
        return p;
    }
}