package com.groupD.uipackage.cli;

/**
 *
 * @author mesfin
 */

import java.util.*;
import com.groupD.corepackage.*;
import com.groupD.utilpackage.*;

public class Main
{	
	private static Game game;
	private static CLI console;
	
	public static void main(String[] args)
	{
		game = new Game();
		console = new CLI(game);
		String input = "";
		start();
		while(true)
		{
			rollDie(game.getTurn());
			int[] choice = game.getChoices(game.getTurn());
			if(choice[0]>0 && choice[1]<=game.getFinalSquare() && (game.getTurn() instanceof HumanPlayer))
			{
				game.setChoice(getValidMove(game.getTurn(), choice[0], choice[1]));
			}
                        game.takeTurn(game.getTurn());
			if(game.getWinner() != null)
			{
				endGame();
			}
			if(game.getStuckPlayer() != null && (game.getStuckPlayer() instanceof HumanPlayer))
			{
				console.showLonePos(game.getStuckPlayer(), choice[1]);
			}
			/*if(game.getOnUpper() != null)
			{
				console.showUpperMove(game.getOnUpper());
			}
			if(game.getOnDowner() != null)
			{
				console.showDownerMove(game.getOnDowner());
			}*/
			if(game.getBumpedPlayer() != null)
			{
				console.showBump(game.getBumpedPlayer());
			}
			if(game.getMissedTurn() != null && (game.getMissedTurn() instanceof HumanPlayer))
			{
				console.showMissedTurn(game.getMissedTurn());
				putBreak();
			}
			console.move(game.getPlayer1());
			console.move(game.getPlayer2());
			displayUpdates();
			if(game.getBonusTurn() != null && (game.getTurn() instanceof HumanPlayer))
			{
				console.showBonusTurn(game.getBonusTurn());
			}
		}
	}
	
	private static void start()
        {
		console.showTitle();
                putBreak();
		console.showRules();
                putBreak();
		console.showCommands();
                putBreak();
		/*if(gameLoadingOption())
		{
			console.setGame(game);
			console.move(game.getPlayer1());
			console.move(game.getPlayer2());
			displayUpdates();
			return;
		}*/
		String mode = console.getGamingOption();
		while(!mode.equals("o") && !mode.equals("t"))
		{
                        showAlt(mode);
			mode = console.getGamingOption();
		}
		String boardType = console.getBoardType();
		while(!boardType.equals("s") && !boardType.equals("c"))
		{
                        showAlt(boardType);
			boardType = console.getBoardType();
		}
		if(mode.equals("o") && boardType.equals("s"))
		{
			String name = console.getName();
                        checkName(name);
			while(name.equals(game.getPlayer2().getName()))
			{
				console.showNameError();
				name = console.getName();
                                checkName(name);
			}
			game = new Game(name);
			console.introducePlayers((HumanPlayer) game.getPlayer1(), (ComputerPlayer) game.getPlayer2());
			putBreak();
			//new BoardFrame();
		}
		else if(mode.equals("t") && boardType.equals("s"))
		{
			String name1 = "";
			String name2 = "";
			name1 = console.getNameOne();
                        checkName(name1);
			name2 = console.getNameTwo();
                        checkName(name2);
			while(name2.equals(name1))
			{
				console.showNameError();
				name2 = console.getNameTwo();
                                checkName(name2);
			}
			game = new Game(name1, name2);
			//new BoardFrame();
		}
		else if(mode.equals("o") && boardType.equals("c"))
		{
			String name = console.getName();
                        checkName(name);
			while(name.equals(game.getPlayer2().getName()))
			{
				console.showNameError();
				name = console.getName();
                                checkName(name);
			}
			int rows = getRowSize();
			int columns = getColumnSize(); 
			int max = rows*columns;
			int[] upperA = getUpperPos(max);
			int[] upperB = getUpperDes(upperA, max);
			int[] upperP = getUpperPoints(upperA);
			int[] downerA = getDownerPos(upperA, max);
			int[] downerB = getDownerDes(downerA, max);
			int[] downerP = getDownerPoints(downerA);
			int[] bonusA = new int[0];
			int[] bonusP = new int[0];
			if(getBonusChoice())
			{
				bonusA = getBonusPos(upperA, downerA, max);
				bonusP = getBonusPoints(bonusA);
			}
			game = new Game(name, rows, columns, upperA, upperB, upperP, downerA, downerB, downerP, bonusA, bonusP);
			console.introducePlayers((HumanPlayer) game.getPlayer1(), (ComputerPlayer) game.getPlayer2());
			putBreak();
		}
		else if(mode.equals("t") && boardType.equals("c"))
		{
			String name1 = "";
			String name2 = "";
			name1 = console.getNameOne();
                        checkName(name1);
			name2 = console.getNameTwo();
                        checkName(name2);
			while(name2.equals(name1))
			{
				console.showNameError();
				name2 = console.getNameTwo();
                                checkName(name2);
			}
			int rows = getRowSize();
			int columns = getColumnSize(); 
			int max = rows*columns;
			int[] upperA = getUpperPos(max);
			int[] upperB = getUpperDes(upperA, max);
			int[] upperP = getUpperPoints(upperA);
			int[] downerA = getDownerPos(upperA, max);
			int[] downerB = getDownerDes(downerA, max);
			int[] downerP = getDownerPoints(downerA);
			int[] bonusA = new int[0];
			int[] bonusP = new int[0];
			if(getBonusChoice())
			{
				bonusA = getBonusPos(upperA, downerA, max);
				bonusP = getBonusPoints(bonusA);
			}
			game = new Game(name1, name2, rows, columns, upperA, upperB, upperP, downerA, downerB, downerP, bonusA, bonusP);
		}
		int[] result = game.start();
		Die temp = new Die();
		temp.setNumberRolled(result[0]);
		console.setGame(game);
		console.displayDie(temp, game.getPlayer1());
		temp.setNumberRolled(result[1]);
		console.displayDie(temp, game.getPlayer2());
		console.showStarter(game.getTurn());
		console.showSymbols(game.getPlayer1(), game.getPlayer2());
		putBreak();
		displayUpdates();
    }
	
	private static void putBreak()
	{
		String input = console.contPrompt();
		while(!input.equals("c"))
		{
			showAlt(input);
			input = console.contPrompt();
		}
	}
	
	private static void rollDie(Player p)
	{
		if(p instanceof HumanPlayer)
		{
			String input = console.getRoll(p);
			while(!input.equals("r"))
			{
				showAlt(input);
				input = console.getRoll(p);
			}
		}
		game.rollDie();
		console.displayDie(game.getDie(), p);
	}
	
	private static void displayUpdates()
	{	
		console.displayScores(game.getPlayer1(), game.getPlayer2());
		if(game.getBoard().getColumns()<=26)
		{
			console.displayBoard();	
		}
		else
		{
			console.showSizeExceeded();
		}
	}
	
	/*private static String getSavingName()
	{
		TreeMap<String,Game> gamelist = FileHandler.getAllGames();
		String input = console.getSavingName();
		while(gamelist.containsKey(input))
		{
			showAlt(input);
			input = console.getSavingName();
		}
		return input;
	}*/
	
	private static int getValidMove(Player p, int a, int b)
	{
		String input = "";
		try
		{
			input = console.getChoicePos(p, a, b);
			int n = Integer.parseInt(input);
			while(n!=a && n!=b)
			{
				console.showInputError();
				n = Integer.parseInt(console.getChoicePos(p, a, b));
			}
			return n;
		}
		catch(NumberFormatException e)
		{
			showAlt(input);
			return getValidMove(p, a, b);
		}
	}
	
	/*private static String getValidGameId()
	{
		TreeMap<String,Game> gamelist = FileHandler.getAllGames();
		console.showGameList();
		String input = console.getGameId();
		while(!gamelist.containsKey(input))
		{
			showAlt(input);
			console.showGameList();
			input = console.getGameId();
		}
		return input;
	}*/
	
	private static void showAlt(String input)
	{
		if(input.equals("u"))
		{
			console.showCommands();
		}
		else if(input.equals("l"))
		{
			console.showRules();
		}
		else if(input.equals("q"))
		{
			quitGame();
		}
		else
		{
			console.showInputError();
		}
	}
        
        private static void checkName(String input)
	{
		if(input.equals("u"))
		{
			console.showCommands();
		}
		else if(input.equals("l"))
		{
			console.showRules();
		}
		else if(input.equals("q"))
		{
			quitGame();
		}
	}
	
	/*private static boolean gameSavingOption()
	{
		String input = console.getGameSavingOption();
		while(!input.equals("y") && !input.equals("n"))
		{
			if(input.equals("x"))
			{
				return false;
			}
			input = console.getGameSavingOption();
		}
		if(input.equals("y"))
		{
			if(game.getId().equals(""))
			{
				game.setId(getSavingName());
			}
			game.save();
		}
		return true;
	}*/
	
	/*private static boolean gameLoadingOption()
	{
		String input = console.getGameLoadingOption();
		while(!input.equals("y") && !input.equals("n"))
		{
			input = console.getGameLoadingOption();
		}
		if(input.equals("y"))
		{
			game.load(getValidGameId());
			return true;
		}
		return false;
	}*/
	
	private static int[] getUpperPos(int max)
	{
                
		String input = console.getUpperPos();
		try
		{
			String[] strray = input.split(",");
			int[] pos = new int[strray.length];
			for(int i=0; i<strray.length; i++)
			{
				pos[i] = Integer.parseInt(strray[i]);
				if(pos[i]<=0 || pos[i]>max)
				{
					console.showInputError();
					return getUpperPos(max);
				}
			}
			return pos;
		}
		catch(Exception e)
		{
			showAlt(input);
			return getUpperPos(max);
		}
	}
	
	private static int[] getUpperDes(int[] pos, int max)
	{
		String input = console.getUpperDes();
		try
		{
			String[] strray = input.split(",");
			if(strray.length != pos.length)
			{
				console.showInputError();
				return getUpperDes(pos, max);
			}
			int[] des = new int[strray.length];
			for(int i=0; i<strray.length; i++)
			{
				des[i] = Integer.parseInt(strray[i]);
				if(des[i]<=pos[i] || des[i]<=0 || des[i]>max)
				{
					console.showInputError();
					return getUpperDes(pos, max);
				}
			}
			return des;
		}
		catch(Exception e)
		{
			showAlt(input);
			return getUpperDes(pos, max);
		}
	}
	
	private static int[] getUpperPoints(int[] pos)
	{
		String input = console.getUpperPoints();
		try
		{
			String[] strray = input.split(",");
			if(strray.length != pos.length)
			{
				console.showInputError();
				return getUpperPoints(pos);
			}
			int[] points = new int[strray.length];
			for(int i=0; i<strray.length; i++)
			{
				points[i] = Integer.parseInt(strray[i]);
			}
			return points;
		}
		catch(Exception e)
		{
			showAlt(input);
			return getUpperPoints(pos);
		}
	}
	
	private static int[] getDownerPos(int[] upperPos, int max)
	{
		String input = console.getDownerPos();
		try
		{
			String[] strray = input.split(",");
			int[] pos = new int[strray.length];
			for(int i=0; i<strray.length; i++)
			{
				pos[i] = Integer.parseInt(strray[i]);
				if(pos[i]<=0 || pos[i]>max || isIn(upperPos, pos[i]))
				{
					console.showInputError();
					return getDownerPos(upperPos, max);
				}
			}
			return pos;
		}
		catch(Exception e)
		{
			showAlt(input);
			return getDownerPos(upperPos, max);
		}
	}
	
	private static int[] getDownerDes(int[] pos, int max)
	{
		String input = console.getDownerDes();
		try
		{
			String[] strray = input.split(",");
			if(strray.length != pos.length)
			{
				console.showInputError();
				return getDownerDes(pos, max);
			}
			int[] des = new int[strray.length];
			for(int i=0; i<strray.length; i++)
			{
				des[i] = Integer.parseInt(strray[i]);
				if(des[i]>=pos[i] || des[i]<=0 || des[i]>max)
				{
					console.showInputError();
					return getDownerDes(pos, max);
				}
			}
			return des;
		}
		catch(Exception e)
		{
                        showAlt(input);
			return getDownerDes(pos, max);
		}
	}
	
	private static int[] getDownerPoints(int[] pos)
	{
		String input = console.getDownerPoints();
		try
		{
			String[] strray = input.split(",");
			if(strray.length != pos.length)
			{
				console.showInputError();
				return getDownerPoints(pos);
			}
			int[] points = new int[strray.length];
			for(int i=0; i<strray.length; i++)
			{
				points[i] = Integer.parseInt(strray[i]);
			}
			return points;
		}
		catch(Exception e)
		{
                        showAlt(input);
			return getDownerPoints(pos);
		}
	}
	
	private static boolean getBonusChoice()
	{
		String input = console.bonusPrompt();
		while(!input.equals("y") && !input.equals("n"))
		{
			console.showInputError();
			input = console.bonusPrompt();
		}
		if(input.equals("y"))
		{
			return true;
		}
		return false;
	}
	
	private static int[] getBonusPos(int[] upperPos, int[] downerPos, int max)
	{
		String input = console.getBonusPos();
		try
		{
			String[] strray = input.split(",");
			int[] pos = new int[strray.length];
			for(int i=0; i<strray.length; i++)
			{
				pos[i] = Integer.parseInt(strray[i]);
				if(pos[i]<=0 || pos[i]>max || isIn(upperPos, pos[i]) || isIn(downerPos, pos[i]))
				{
					console.showInputError();
					return getBonusPos(upperPos, downerPos, max);
				}
			}
			return pos;
		}
		catch(Exception e)
		{
                        showAlt(input);
			return getBonusPos(upperPos, downerPos, max);
		}
	}
	
	private static int[] getBonusPoints(int[] pos)
	{
		String input = console.getBonusPoints();
		try
		{
			String[] strray = input.split(",");
			if(strray.length != pos.length)
			{
				console.showInputError();
				return getBonusPoints(pos);
			}
			int[] points = new int[strray.length];
			for(int i=0; i<strray.length; i++)
			{
				points[i] = Integer.parseInt(strray[i]);
			}
			return points;
		}
		catch(Exception e)
		{
                        showAlt(input);
			return getBonusPoints(pos);
		}
	}
	
	private static boolean isIn(int[] array, int n)
	{
		for(int i=0; i<array.length; i++)
		{
			if(array[i]==n)
			{
				return true;
			}
		}
		return false;
	}
	
	private static int getRowSize()
	{
		String input = console.getRowSize();
		try
		{
			int size = Integer.parseInt(input);
			while(size<4 || size>26)
			{
				console.showInputError();
				size = Integer.parseInt(console.getRowSize());
			}
			return size;
		}
		catch(Exception e)
		{
                        showAlt(input);
			return getRowSize();
		}
	}
	
	private static int getColumnSize()
	{
                String input = console.getColumnSize();
		try
		{
			int size = Integer.parseInt(input);
			while(size<4 || size>26)
			{
				console.showInputError();
				size = Integer.parseInt(console.getColumnSize());
			}
			return size;
		}
		catch(Exception e)
		{
                        showAlt(input);
			return getColumnSize();
		}
	}
	
	private static void endGame()
	{	
		game.end();
		console.move(game.getPlayer1());
		console.move(game.getPlayer2());
		displayUpdates();
		console.displayWinner(game.getWinner());
		console.showEndCredits();
		System.exit(0);
	}
	
	private static void quitGame()
    {
		/*if(!gameSavingOption() && !game.isFinished())
		{
			return;
		}*/
                if(game.isStarted())
                {
                    game.quit();
                }
		console.showEndCredits();
		System.exit(0);
    }
}