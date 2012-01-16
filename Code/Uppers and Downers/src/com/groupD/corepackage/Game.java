package com.groupD.corepackage;

/**
 * @author mesfin
 */

import com.groupD.utilpackage.ScoringSystem;
import com.groupD.utilpackage.FileHandler;

public class Game 
{
    private Die die;
    private Player p1;
    private Player p2;
    private Board board;
	private int finalsq;
	private ScoringSystem score;
	private String id; 
	private boolean finished;
	private boolean started;
	private boolean loaded;
	private Player winner;
	private Player onUpper;
	private Player onDowner;
	private Player onBonus;
	private Player turn;
	private Player bonusTurn;
	private Player missedTurn;
	private Player stuckPlayer;
	private Player bumpedPlayer;
	private int choice;
    
    public Game()
    {
		this("Player");
    }
	
    public Game(String name)
    {
		this(name, "Invincible");
                p2 = new ComputerPlayer("Invincible", new Token("blue"));
    }
    
    public Game(String name1, String name2)
    {
		this(name1, name2, 10, 10);
    }
    
    public Game(String name1, String name2, int rows, int columns)
    {
        die = new Die();
        p1 = new HumanPlayer(name1, new Token("red"));
        p2 = new HumanPlayer(name2, new Token("blue"));
        board = new Board(rows, columns);
		finalsq = board.getRows()*board.getColumns();
		score = new ScoringSystem(board, true);
		id = "";
		finished = false;
		started = false;
		loaded = false;
		choice = 0;
    }

	public Game(Player p1, Player p2, Board b, ScoringSystem sc)
	{
		die = new Die();
                this.p1 = p1;
                this.p2 = p2;
                board = b;
		finalsq = board.getRows()*board.getColumns();
		score = sc;
		id = "";
		finished = false;
		started = false;
		loaded = false;
		choice = 0;
	}
	
	public Game(String name, int rows, int columns, int[] upperA, int[] upperB, int[] upperS, int[] downerA, int[] downerB, 
		int[] downerS, int[] bonus, int[] bonusSc)
    {
        this(name, "", rows, columns, upperA, upperB, upperS, downerA, downerB, downerS, bonus, bonusSc);
		p2 = new ComputerPlayer("Invincible", new Token("blue"));
    }
	
    public Game(String name1, String name2, int rows, int columns, int[] upperA, int[] upperB, int[] upperS, int[] downerA, int[] downerB, 
		int[] downerS, int[] bonus, int[] bonusSc)
    {
        die = new Die();
        p1 = new HumanPlayer(name1, new Token("red"));
        p2 = new HumanPlayer(name2, new Token("blue"));
        board = new Board(rows, columns, upperA, upperB, upperS, downerA, downerB, downerS, bonus, bonusSc);
		finalsq = board.getRows()*board.getColumns();
		score = new ScoringSystem(board, false);
		id = "";
		finished = false;
		started = false;
		loaded = false;
		choice = 0;
    }
    
	/**
	 * The die is rolled, and an array containing the possible cells 
	 * the player can move to is returned.
	 */
    public int[] start() throws UnsupportedOperationException
    {
		if(finished)
		{
			throw new UnsupportedOperationException("Cannot start a game that is finished.");
		}
		if(loaded)
		{
			throw new UnsupportedOperationException("Cannot start a game that is loaded.");
		}
		if(started)
		{
			throw new UnsupportedOperationException("Cannot start a game that is already started.");
		}
		started = true;
		int[] advance = new int[2];
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
		advance[0] = result1;
		advance[1] = result2;
        if(result1>result2)
        {
			turn = p1;
        }
        else
        {
			turn = p2;
        }
		return advance;
    }
    
    public void end() throws UnsupportedOperationException
    {
		if(finished)
		{
			throw new UnsupportedOperationException("Cannot end a game that is already finished.");
		}
		if(!started && !loaded)
		{
			throw new UnsupportedOperationException("Cannot end a game that is not started.");
		}
		finished = true;
		started = false;
		loaded = false;
		Player p = null;
                p = this.getWinner();
                if(p != null)
                {
                    p.updateScoreboard(true);
                    if(p.getToken().getColor().equals("red"))
                    {
			p2.updateScoreboard(false);
                    }
                    else
                    {
			p1.updateScoreboard(false);
                    }
                }
    }
    
    public void quit() throws UnsupportedOperationException
    {
		if(finished)
		{
			throw new UnsupportedOperationException("Cannot quit a game that is finished.");
		}
		if(!started && !loaded)
		{
			throw new UnsupportedOperationException("Cannot quit a game that is not started.");
		}
		p1.updateScoreboard(false);
		p2.updateScoreboard(false);
		started = false;
		loaded = false;
    }
	
    public void load(String gameId) throws UnsupportedOperationException
    {
		/*if(started)
		{
			throw new UnsupportedOperationException("Cannot load a game that is started.");
		}
		Game lg = FileHandler.getGame(gameId);
		die = lg.getDie();
		p1 = lg.getPlayer1();
		p2 = lg.getPlayer2();
		if(lg.getTurn().equals(p1))
		{
			turn = p1;
		}
		else
		{
			turn = p2;
		}
		board = lg.getBoard();
		score = lg.getScoringSystem();
		this.id = lg.getId();
                loaded = true;*/
    }
    
    public void save() throws UnsupportedOperationException
    {
		/*if(!started && !loaded)
		{
			throw new UnsupportedOperationException("Cannot end a game that is not started.");
		}
		FileHandler.removeGame(id);
		FileHandler.addGame(this);*/
    }

    /**
     * takes a player and assigns the current turn based on the color of the player's token.
     * If the player is a human, it lets them take a human turn, otherwise computer turn.
     * If the player has rolled a 6 on the die, they get a bonus turn.
     */
    public void takeTurn(Player p) throws UnsupportedOperationException
    {	
		if(finished)
		{
			throw new UnsupportedOperationException("Cannot play a game that is finished.");
		}
		if(!started && !loaded)
		{
			throw new UnsupportedOperationException("Cannot play a game that is not started or loaded.");
		}
		setDefault();
		
		if(p.getToken().getColor().equals("red"))
		{
			turn = p2;
		}
		else
		{
			turn = p1;
		}
		
		if(p instanceof HumanPlayer)
		{
			humanTurn(p);
		}
		else
		{
			compuTurn(p);
		}
		
		if(die.getNumberRolled()==6)
		{
			bonusTurn = p;
			turn = p;
		}
    }
	
	/**
	 * gets the two possible moves the player can make. 
	 * If the first choice is a negative value, it specifies 
	 * that the player is stuck (with no decisions to make by 
	 * themselves) and moves them forward, after checking if 
	 * the position is occupied and bumping the player who is 
	 * occupying the cell, if that is the case.
	 * If the second choice exceeds the board size, then it 
	 * specifies that the player has missed their turn.
	 * Otherwise, it gets the player's choice, which could be 
	 * set in a UI class, where the user enters their chosen move.
	 * If the choice is equal to the default value of 0, it moves 
	 * the player forward. If the player's choice is equal to the 
	 * first option it moves the player backward, and moves them 
	 * forward if it is equal to the second option.
	 */
	private void humanTurn(Player p)
	{
		int[] choice = calculateMove(p);
		if(choice[0]<1)
		{
			stuckPlayer = p;
			if(isOccupied(choice[1]))
			{
				bump(p);
			}
			movePlayer(p, die.getNumberRolled());
		}
		else if(choice[1]>finalsq)
		{
			missedTurn = p;
		}	
		else
		{
			int move = getChoice();
			if(move == 0)
			{
				move = p.getToken().getPosition()+die.getNumberRolled();
				if(isOccupied(move))
				{
					bump(p);
				}
				movePlayer(p, die.getNumberRolled());
			}
			else
			{
				if(isOccupied(move))
				{
					bump(p);
				}
				if(move == choice[0])
				{
					movePlayer(p, -1*die.getNumberRolled());
				}
				else
				{
					movePlayer(p, die.getNumberRolled());
				}	
			}
		}
	}
	
	/**
	 * If the second choice is within the board, and the first 
	 * choice is negative, it moves the player forward. If both 
	 * choices are within the board, then it gets a decision from 
	 * the computer player through a method that uses AI, and moves 
	 * the player in the chosen direction.
	 */
	private void compuTurn(Player p)
	{
		int[] choice = calculateMove(p);
				
		if(choice[1]<=finalsq)
		{
			if(choice[0]<1)
			{
                                stuckPlayer = p;
				if(isOccupied(choice[1]))
				{
					bump(p);
				}
				movePlayer(p, die.getNumberRolled());
				return;
			}
			
			ComputerPlayer cp = (ComputerPlayer) p;
			int decision = cp.getDecision(die.getNumberRolled(), board);
			
			if(decision>0)
			{
				if(isOccupied(choice[1]))
				{
					bump(p);
				}
				movePlayer(p, die.getNumberRolled());
			}
			else
			{
				if(isOccupied(choice[0]))
				{
					bump(p);
				}
				movePlayer(p, -1*die.getNumberRolled());
			}
		}
                else
		{
			missedTurn = p;
		}	
	}
	
	public void rollDie() throws UnsupportedOperationException
	{
		if(finished)
		{
			throw new UnsupportedOperationException("Cannot play a game that is finished.");
		}
		if(!started && !loaded)
		{
			throw new UnsupportedOperationException("Cannot play a game that is not started or loaded.");
		}
		die.roll();
	}
	
	private int[] calculateMove(Player p)
	{
		int[] choices = new int[2];
		choices[0] = p.getToken().getPosition() - die.getNumberRolled();
		choices[1] = p.getToken().getPosition() + die.getNumberRolled();
		return choices;
	}
	
	/**
	 * calls the checkSpecialCell as long as the player occupies special 
	 * cells (For example, the player can directly jump from an upper to 
	 * a downer to an upper...).
	 */
	private void upperOrDowner(Player p)
	{
		int special = checkSpecialCell(p);
		while(special != 0)
		{
			special = checkSpecialCell(p);
		}
	}
	
	/**
	 * If the cell that the player is currently occupying is 
	 * an upper or a downer, it moves the player to that cell's 
	 * destination. It then specifies that the player is on an 
	 * upper or a downer and returns 1 or -1, respectively. If 
	 * the cell is an ordinary or a bonus cell (which it specifies), 
	 * it simply returns 0.
	 */
	private int checkSpecialCell(Player p)
	{
		int pos = p.getToken().getPosition();
		Cell currcell = board.getCell(pos);
		if(currcell instanceof SpecialCell)
		{
			SpecialCell cell = (SpecialCell) currcell;
			if(!cell.getCellType().name().equals("BONUS"))
			{
				if(isOccupied(cell.getCellNumber()))
				{
					bump(p);
				}
				board.leaveCell(p.getToken().getPosition());
				p.moveToken(cell.getCellNumber()-pos);
				board.occupyCell(p.getToken().getPosition());
				if(cell.getCellType().name().equals("UPPER"))
                                {
					onUpper = p;
					return 1;
                                }
                                else if(cell.getCellType().name().equals("DOWNER"))
                                {
					onDowner = p;
					return -1;
                                }
			}	
			else
			{
				onBonus = p;
			}
		}
		return 0;
	}
	
	private boolean isOccupied(int n)
	{
		return board.getCell(n).isOccupied();
	}
	
	/**
	 * Moves the opponent back to position 1;
	 * p1 has a red token, p2 has a blue token.
	 */
	private void bump(Player p)
	{
		if(p.getToken().getColor().equals("red"))
		{
			board.leaveCell(p2.getToken().getPosition());
			p2.moveToken(1-p2.getToken().getPosition());
			board.occupyCell(p2.getToken().getPosition());
			bumpedPlayer = p2;
		}
		else
		{	
			board.leaveCell(p1.getToken().getPosition());
			p1.moveToken(1-p1.getToken().getPosition());
			board.occupyCell(p1.getToken().getPosition());
			bumpedPlayer = p1;
		}
	}
	
	/**
	 * It moves the player to the given position, adds to their 
	 * total score, checks if they have landed on an upper or a 
	 * downer, and declares them as a winner if they have landed 
	 * on the final cell.
	 */
	private void movePlayer(Player p, int n)
	{	
		board.leaveCell(p.getToken().getPosition());
		p.moveToken(n);
		board.occupyCell(p.getToken().getPosition());
		p.updateTotalScore(score.getScore(p.getToken().getPosition()));
		upperOrDowner(p);
		
		if(p.getToken().getPosition()==finalsq)
		{
			winner = p;
		}
	}
	
	private int getChoice()
	{
		return choice;
	}
	
	public void setChoice(int n)
	{
		if(finished)
		{
			throw new UnsupportedOperationException("Cannot play a game that is finished.");
		}
		if(!started && !loaded)
		{
			throw new UnsupportedOperationException("Cannot play a game that is not started or loaded.");
		}
		choice = n;
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
    public Board getBoard()
    {
        return board;
    }
	
	public void setBoard(Board b)
	{
		board = b;
	}
	
	public Die getDie()
	{
		return die;
	}
	
	public void setDie(Die d)
	{
		die = d;
	}
		
	public Player getPlayer1()
	{
		return p1;
	}
	
	public void setPlayer1(Player p)
	{
		p1 = p;
	}
	
	public Player getPlayer2()
	{
		return p2;
	}

	public void setPlayer2(Player p)
	{
		p2 = p;
	}
	
	public int[] getChoices(Player p)
	{
		return calculateMove(p);
	}
	
	public Player getWinner()
	{
		return winner;
	}
	
	public Player getOnUpper()
	{
		return onUpper;
	}
	
	public Player getOnDowner()
	{
		return onDowner;
	}
	
	public Player getOnBonus()
	{
		return onBonus;
	}
	
	public Player getTurn()
	{
		return turn;
	}
	
	public void setTurn(Player t)
	{
		turn = t;
	}
	
	public Player getBonusTurn()
	{
		return bonusTurn;
	}
	
	public Player getMissedTurn()
	{
		return missedTurn;
	}
	
	public Player getStuckPlayer()
	{
		return stuckPlayer;
	}
	
	public Player getBumpedPlayer()
	{
		return bumpedPlayer;
	}
	
	public int getFinalSquare()
	{
		return finalsq;
	}
	
	public boolean isFinished()
	{
		return finished;
	}
    
	public boolean isStarted()
	{
		return started;
	}
	
	public boolean isLoaded()
	{
		return loaded;
	}
	
	public ScoringSystem getScoringSystem()
	{
		return score;
	}
	
	public void setScoringSystem(ScoringSystem sc)
	{
		score = sc;
	}
	
	private void setDefault()
	{
		winner = null;
		onUpper = null;
		onDowner = null;
		onBonus = null;
		turn = null;
		bonusTurn = null;
		missedTurn = null;
		stuckPlayer = null;
		bumpedPlayer = null;
	}
}
