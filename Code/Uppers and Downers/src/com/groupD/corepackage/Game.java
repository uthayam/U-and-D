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
	Player p = this.getWinner();
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
	if(started)
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
        loaded = true;
    }
    
    public void save() throws UnsupportedOperationException
    {
		if(!started && !loaded)
		{
			throw new UnsupportedOperationException("Cannot end a game that is not started.");
		}
		FileHandler.removeGame(id);
		FileHandler.addGame(this);
    }
    
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
	
	private void compuTurn(Player p)
	{
		int[] choice = calculateMove(p);
				
		if(choice[1]<=finalsq)
		{
			if(choice[0]<0)
			{
				movePlayer(p, die.getNumberRolled());
				return;
			}
			
			ComputerPlayer cp = (ComputerPlayer) p;
			int decision = cp.getDecision(die.getNumberRolled());
			
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
		
		if(p.getToken().getPosition()==finalsq)
		{
			winner = p;
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
	
	private void upperOrDowner(Player p)
	{
		int special = checkSpecialCell(p);
		while(special != 0)
		{
			special = checkSpecialCell(p);
		}
	}
	
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
				p.updateTotalScore(score.getScore(p.getToken().getPosition()));
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
	
	private void movePlayer(Player p, int n)
	{	
		board.leaveCell(p.getToken().getPosition());
		p.moveToken(n);
		p.updateTotalScore(score.getScore(p.getToken().getPosition()));
		board.occupyCell(p.getToken().getPosition());
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