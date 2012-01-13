
package com.groupD.uipackage.cli;

/**
 *
 * @author mesfin
 */

import java.util.*;
import com.groupD.corepackage.*;
import com.groupD.utilpackage.*;

public class CLI
{
	private Senterej gameBoard;
	private Scanner in;
	
	public CLI(Game g)
	{
		gameBoard = new Senterej(g.getBoard());
		in = new Scanner(System.in);
	}

	public void setGame(Game g)
	{
		gameBoard = new Senterej(g.getBoard());
	}
	
	public void showTitle()
	{
		System.out.println("");
		System.out.println("                          ___   ___    ___  ____   ___");
		System.out.println("                  |    | |   \\ |   \\  |    |    \\ /   \\");
		System.out.println("                  |    | |   | |   |  |    |    | |     ");
		System.out.println("                  |    | |___/ |___/  |___ |____/ \\____");
		System.out.println("                  |    | |     |      |    |   \\       \\");
		System.out.println("                  |    | |     |      |    |    \\      |");
		System.out.println("                   \\__/  |     |      |___ |     \\ \\___/");
		System.out.println("                                   _____");
		System.out.println("                                   \\   /");
		System.out.println("                                    \\ /");
		System.out.println("                                    /\\");
		System.out.println("                                   /  \\  /");
		System.out.println("                                  /    \\/");
		System.out.println("                                 /_____/\\");
		System.out.println("             ____     __                      ___  ____   ___");
		System.out.println("             |   \\   /  \\  |  |  | |\\     |  |    |    \\ /   \\");
		System.out.println("             |    | |    | |  |  | | \\    |  |    |    | |     ");
		System.out.println("             |    | |    | |  |  | |  \\   |  |___ |____/ \\____");
		System.out.println("             |    | |    | |  |  | |   \\  |  |    |   \\       \\");
		System.out.println("             |    | |    | |  |  | |    \\ |  |    |    \\      |");
		System.out.println("             |___/   \\__/   \\/ \\/  |     \\|  |___ |     \\ \\___/");
		System.out.println("");
	}
	
	public String contPrompt()
	{
		System.out.print("\nEnter 'c' to continue: ");
		return in.next();
	}
	
	public void showRules()
	{
		System.out.println("\n					_Rules_");
		System.out.print("\n1) To start, each player rolls the die to determine who goes first. The player rolling the highest number ");
		System.out.print("begins.\n");
		System.out.print("\n2) Each player, in turn throws the die. After the very first throw, the player can choose either to move forward ");
		System.out.print("their playing token the same number of squares as shown on the die or move back the same number of squares.\n");
		System.out.println("\n3) The game begins at square 1 and finishes when a player reaches square 100.");
		System.out.println("\n4) If a player rolls a 6 they get another go.");
		System.out.print("\n5) If a playing token lands on a square occupied by an upper, it is carried up the ");
		System.out.print("upper and placed in the square at the top of the upper.\n");
		System.out.print("\n6) If a playing token lands on a square occupied by a downer, it is carried down the downer and placed in the ");
		System.out.print("square at the end of the downer.\n");
		System.out.print("\n7) When a playing token lands on a square already occupied by an opponent, the opponent is 'bumped' and must ");
		System.out.print("start again from square 1.\n");
		System.out.print("\n8) To land in the final square (100), a player must throw the exact number on the die. If the throw of the die ");
		System.out.print("turns up a number that is too high, the turn is missed.\n");
	}
	
	public String getGamingOption()
	{
		System.out.println("\n _____________			______________");
		System.out.println("|One Player(o)|			|Two Players(t)|");
		System.out.println("|_____________|			|______________|");
		System.out.print("\nEnter your choice of game mode from the above menu: ");
		return in.next();
	}
	
	public void showCommands()
	{
		System.out.println("\n_User commands_");
		System.out.println("r	-	roll die");
		System.out.println("l	-	show game rules");
		System.out.println("u	-	show user commands");
		System.out.println("q	-	quit\n");
	}
	
	public String getName()
	{
		System.out.print("\nEnter your name: ");
		return in.next();
	}
	
	public String getNameOne()
	{
		System.out.print("\nPlayer 1, enter your name: ");
		return in.next();
	}

	public String getNameTwo()
	{
		System.out.print("\nPlayer 2, enter your name: ");
		return in.next();
	}
	
	public void introducePlayers(HumanPlayer h, ComputerPlayer c)
	{
		System.out.println("\n"+h.getName()+", you will play against "+c.getName()+".");
	}
	
	public void showStarter(Player p1)
	{
		System.out.println("\nThe player who has rolled the highest number is "+p1.getName()+".");
	}
	
	public void showSymbols(Player p1, Player p2)
	{
		System.out.println("\n"+p1.getName() + " is represented by this token: "+getSymbol(p1.getToken()));
		System.out.println(p2.getName() + " is represented by this token: "+getSymbol(p2.getToken()));
	}
	
	public String startPrompt()
	{
		System.out.print("\nEnter 's' to start the game: ");
		return in.next();
	}
	
	public String getRoll(Player p)
	{
		System.out.print("\nYour turn, "+p.getName()+". Roll the die: ");
		return in.next();
	}
	
	public String getChoicePos(Player p, int a, int b)
	{
		System.out.print("\n" + p.getName() + ", you can either move to square " + a + " or to square " + b + ".\nChoose wisely: ");
		return in.next();
	}
	
	public void showLonePos(Player p, int n)
	{
		System.out.println("\n" + p.getName() + ", you can only move to square " + n + ".");
	}
	
	public void showBonusTurn(Player p)
	{
		System.out.println("\n" + p.getName() + ", you rolled a 6, which means you get another turn.");
	}

	public void showMissedTurn(Player p)
	{
		System.out.print("\n" + p.getName() + ", the number you rolled is too high for the final square, and therefore you have missed ");
		System.out.print("your turn.\n");
	}

	public void showUpperMove(Player p)
	{
		System.out.print("\n" + p.getName() + ", landed on an UPPER, and therefore has been moved up to square ");
		System.out.print(p.getToken().getPosition()+"\n");
	}

	public void showDownerMove(Player p)
	{
		System.out.print("\n" + p.getName() + ", landed on an DOWNER, and therefore has been moved down to square ");
		System.out.print(p.getToken().getPosition()+"\n");
	}

	public void showBump(Player p)
	{
		System.out.println("\n" + p.getName() + " has been bumped.");
	}
	
	public void move(Player p)
	{
		if(p.getToken().getColor().equals("red"))
		{
			moveRed(p.getToken().getPosition());
		}
		else if(p.getToken().getColor().equals("blue"))
		{
			moveBlue(p.getToken().getPosition());
		}
	}
	
	private void moveRed(int n)
	{
		gameBoard.moveRed(n);
	}
	
	private void moveBlue(int n)
	{
		gameBoard.moveBlue(n);
	}
	
	public void displayBoard()
	{
		gameBoard.draw();
		gameBoard.display();
	}
	
	public void displayDie(Die die, Player p)
	{
		System.out.println("\n"+p.getName() + " has rolled:\n");
		(new DieDrawing(die)).display();
	}
	
	public void displayScores(Player p1, Player p2)
	{	
		ScoreBoard scoreBoard = new ScoreBoard(p1.getName(), p2.getName());
		scoreBoard.updateScores(p1.getTotalScore(), p2.getTotalScore());
		scoreBoard.display();
	}
	
	public void displayWinner(Player p)
	{
		System.out.println("\nGAME OVER");
		WinnerDisplay wd = new WinnerDisplay(p.getName());
		wd.draw();
		wd.display();
	}
	
	public String getGameLoadingOption()
	{
		System.out.print("\nWould you like to load an existing game? (y/n): ");
		return in.next();
	}
	
	public String getGameSavingOption()
	{
		System.out.print("\nWould you like to save the game before exiting? (y/n): ");
		return in.next();
	}
	
	public String getPlayerSavingOption()
	{
		System.out.print("\nWould you like to save your scores before exiting? (y/n): ");
		return in.next();
	}
	
	public String getSavingName()
	{
		System.out.print("\nEnter the name of your game: ");
		return in.next();
	}
	
	public String getUpperPos()
	{
		System.out.print("\nEnter the positions for uppers in the format 'position,position,position...': ");
		return in.next();
	}
	
	public String getUpperDes()
	{
		System.out.print("\nEnter the destinations for uppers in the format 'destination,destination,destination...': ");
		return in.next();
	}
	
	public String getUpperPoints()
	{
		System.out.print("\nEnter the points for uppers in the format 'point,point,point...': ");
		return in.next();
	}
	
	public String getDownerPos()
	{
		System.out.print("\nEnter the positions for downers in the format 'position,position,position...': ");
		return in.next();
	}
	
	public String getDownerDes()
	{
		System.out.print("\nEnter the destinations for downers in the format 'destination,destination,destination...': ");
		return in.next();
	}
	
	public String getDownerPoints()
	{
		System.out.print("\nEnter the points for downers in the format 'point,point,point...': ");
		return in.next();
	}
	
	public String bonusPrompt()
	{
		System.out.print("\nWould you like to add bonus points on the board (y/n): ");
		return in.next();
	}
	
	public String getBonusPos()
	{
		System.out.print("\nEnter the positions for bonuses in the format 'position,position,position...': ");
		return in.next();
	}
	
	public String getBonusPoints()
	{
		System.out.print("\nEnter the points for bonuses in the format 'point,point,point...': ");
		return in.next();
	}
	
	public String getBoardType()
	{
		System.out.println("\n _____________			______________");
		System.out.println("|Standard(s)  |			|Custom made(c)|");
		System.out.println("|_____________|			|______________|");
		System.out.print("\nEnter your choice of board from the above menu: ");
		return in.next();
	}
	
	public String getRowSize()
	{
		System.out.print("\nEnter the number of rows for your board (must be >= 4 and <= 26): ");
		return in.next();
	}
	
	public String getColumnSize()
	{
		System.out.print("\nEnter the number of columns for your board (must be >= 4 and <= 26): ");
		return in.next();
	}
	
	public void showInputError()
	{
		System.out.println("\nWRONG INPUT");
	}
	
	public void showNameError()
	{	
		System.out.println("\nYou have entered the name of your opponent. Please enter a different name.");
	}

	public void showSizeExceeded()
	{
		System.out.println("\n[Board not visible since it exceeds the screen size]");
	}
	
	public void showEndCredits()
	{
		System.out.println("\nCopyright © 2011-2012		Group D");
	}
	
	public String getGameId()
	{
		System.out.println("\nEnter the id of the game you want to continue, from the list above.");
		return in.next();
	}
	
	/*public void showGameList()
	{
		TreeMap<String,Game> gamelist = FileHandler.getAllGames();
		System.out.println("\n_List of games_");
		for(String id : gamelist.keySet())
		{
			System.out.println(id);
		}
	}*/
	
	private String getSymbol(Token t)
	{
		return gameBoard.getSymbol(t);
	}
	
	/**
	 * This inner class provides an ASCII representation 
	 * of the board belonging to the game used to create 
	 * the CLI object.
	 */
	private static final class Senterej
	{
		private String drawing;
		private int curpos;
		private int tokenpos;
		private boolean alternate;
		private Board board;
		private int red;
		private int blue;
		private boolean redplaced;
		private boolean blueplaced;
		private String bluesymbol;
		private String redsymbol;
		private int rows;
		private int columns;
		private int finalsq;
		private String space;
		private String dash;

		/**
		 * A Senterej object is created by taking a Board object, 
		 * and getting its properties, such as the size of its rows 
		 * and columns. The finalsq variable refers to the position 
		 * of the final square on the board, and is equivalent to  
		 * the size of the board. curpos (current position) and tokenpos 
		 * refer to the position of the current cell and any token to 
		 * be drawn, respectively.
		 * The drawing of the board starts from the top. Since the 
		 * cells on the board are put together in a zig-zag structure, 
		 * the direction of each row alternates between left-to-right 
		 * and right-to-left. The boolean variable alternate is therefore 
		 * initialized in the such a way that if the number of rows is even, 
		 * then the row goes from left to right, and from right to left 
		 * otherwise. A board drawing is divided into three parts. A roof, 
		 * a body (consisting of all the rows), and a base. Each cell is in 
		 * turn divided into a header (containing the cell number or a special 
		 * cell indicator), a middle (possibly containing a token), and a footer.
		 * The tokens' positions are reperesented by the variables red and blue, 
		 * and start at 1. redplaced and blueplaced are boolean variables that 
		 * indicate if any of the tokens has been drawn (placed) already.
		 */
		public Senterej(Board board)
		{
			this.board = board;
			rows = board.getRows();
			columns = board.getColumns();
			drawing = "";
			int size = rows*columns;
			finalsq = size;
			curpos = size;
			tokenpos = size;
			alternate = (rows%2==0) ? false : true;
			red = 1;
			blue = 1;
			redplaced = false;
			blueplaced = false;
			bluesymbol = "♟";
			redsymbol = "♙";
			space = "";
			dash = "";
			makeExtra();
		}

		/**
		 * This method draws the top of the board. The roof consists of two lines 
		 * on top of one another that give the board a border. They are each drawn 
		 * using a for-loop. The last lines of the roof, are smaller than the rest, 
		 * to keep it from going outside the board.
		 */
		private void addRoof()
		{		
			drawing += " __";
			for(int i=0; i<columns; i++)
			{
				if(i==(columns-1))
				{
					drawing += "______"+dash;;
				}
				else
				{
					drawing += "_______"+dash;;
				}
			}
			drawing += "__\n";
			drawing += "|  ";
			for(int i=0; i<columns; i++)
			{
				if(i==(columns-1))
				{
					drawing += "______"+dash;;
				}
				else
				{
					drawing += "_______"+dash;;
				}
			}
			drawing += "  |\n";
		}
		
		/**
		 * As each header is drawn, the position of the next cell 
		 * decrements by 1, (100 to 99 to 98...) since the board 
		 * is being drawn top-down.
		 * As each middle part of the cell is drawn, we check if 
		 * it is possible to draw a token on the cell (if any of 
		 * the tokens have not been placed yet), and leave it 
		 * blank if it is not possible.
		 * The footer is the last part of the cell to be drawn.
		 * A new line is added between the header, the middle and 
		 * the footer, through the addFinish method.
		 * Finally, the drawing alternates direction.
		 */
		private void addRowFromRight()
		{
			addWall();
			for(int i=0; i<columns; i++)
			{
				addHeader(curpos);
				curpos--;
			}
			addFinish();
			addWall();
			for(int i=0; i<columns; i++)
			{
				if(!redplaced || !blueplaced)
				{
					if(!addToken(tokenpos))
					{
						addMiddle();
					}
				}
				else
				{
					addMiddle();
				}
				tokenpos--;
			}
			addFinish();
			addWall();
			for(int i=0; i<columns; i++)
			{
				addFooter();
			}
			addFinish();
			alternate = !alternate;
		}

		/**
		 * This method is similar to the above method, except for a two subtle differences.
		 * The first for-loop starts from a position opposite to curpos, increments upto curpos.
		 * For example, on a 10-by-10 board, if the curpos = 90, the row starts from 80, hence 
		 * it goes (80 to 81 to 82...90).
		 * The second for-loop adds a token on the mirror of its current position 
		 * (83 if it should be on 88, for example).
		 */
		private void addRowFromLeft()
		{
			int limit = curpos;
			addWall();
			for(int i=curpos-(columns-1); i<=limit; i++)
			{
				addHeader(i);
				curpos--;
			}
			addFinish();
			addWall();
			for(int i=(columns-1); i>=0; i--)
			{
				if(!redplaced || !blueplaced)
				{
					if(!addToken(mirror(tokenpos)))
					{
						addMiddle();
					}
				}
				else
				{
					addMiddle();
				}
				tokenpos--;
			}
			addFinish();
			addWall();
			for(int i=0; i<columns; i++)
			{
				addFooter();
			}
			addFinish();
			alternate = !alternate;
		}

		// used at the start of every row by addRow methods
		/**
		 * This method adds a border to the board at the start of a new row. 
		*/
		private void addWall()
		{
			drawing += "| |";
		} 

		// used initially by addRow methods
		/**
		 * If the current cell is not a special cell, the header 
		 * will include the position of the cell. The space 
		 * between the cell number and the barrier varies 
		 * according to the number of digits of for the cell 
		 * number.
		 */
		private void addHeader(int n)
		{
			if(!addSpecialCell(n))
			{
				if(String.valueOf(n).length()==1)
				{
					drawing += "" + n + getSpace(n) + "     |";
				}
				else if(String.valueOf(n).length()==2)
				{	
					drawing += "" + n + getSpace(n) + "    |";
				}
				else if(String.valueOf(n).length()==3)
				{
					drawing += "" + n + getSpace(n) + "   |";
				}
				else
				{	
					drawing += "" + n + getSpace(n) + "  |";
				}
			}
		}

		/**
		 * This method checks if the current cell is a special cell, and if so, 
		 * draws a representation according to its type and returns true, but 
		 * otherwise, it simply returns false.
		 */
		private boolean addSpecialCell(int i)
		{
			if(board.getCell(i) instanceof SpecialCell)
	        {
	            SpecialCell temp = (SpecialCell) board.getCell(i);
	            if(temp.getCellType().name().equals("UPPER"))
	            {
	                addUpper(temp.getCellNumber());
					return true;
	            }
	            else if(temp.getCellType().name().equals("DOWNER"))
	            {
	                addDowner(temp.getCellNumber());
					return true;
	            }
		        else if(temp.getCellType().name().equals("BONUS"))
		        {
		            addBonus(temp.getCellNumber());
					return true;
		        }
	        }
			return false;
		}

		// used by addSpecialCell
		/**
		 * This method indicates that the cell is an upper and specifies its cell number.
		 * The space between the 'U' and the cell number varies 
		 * according to the number of digits of for the cell 
		 * number.
		 */
		private void addUpper(int n)
		{
			if(String.valueOf(n).length()==1)
			{
				drawing += "U    "+ getSpace(n) +n+"|";
			}
			else if(String.valueOf(n).length()==2)
			{
				drawing += "U   "+ getSpace(n) +n+"|";
			}
			else if(String.valueOf(n).length()==3)
			{
				drawing += "U  "+ getSpace(n) +n+"|";
			}
			else
			{
				drawing += "U "+ getSpace(n) +n+"|";
			}
		}
		
		/**
		 * This method indicates that the cell is a downer and specifies its cell number.
		 * The space between the 'D' and the cell number varies 
		 * according to the number of digits of for the cell 
		 * number.
		 */
		private void addDowner(int n)
		{
			if(String.valueOf(n).length()==1)
			{
				drawing += "D    "+ getSpace(n) +n+"|";
			}
			else if(String.valueOf(n).length()==2)
			{
				drawing += "D   "+ getSpace(n) +n+"|";
			}
			else if(String.valueOf(n).length()==3)
			{
				drawing += "D  "+ getSpace(n) +n+"|";
			}
			else
			{
				drawing += "D "+ getSpace(n) +n+"|";
			}
		}
		
		/**
		 * This method indicates that the cell is a bonus and specifies its cell number.
		 * The space between the cell number and the 'B' varies 
		 * according to the number of digits of for the cell 
		 * number.
		 */
		private void addBonus(int n)
		{
			if(String.valueOf(n).length()==1)
			{
				drawing += n+ getSpace(n) +"    B|";
			}
			else if(String.valueOf(n).length()==2)
			{
				drawing += n+ getSpace(n) +"   B|";
			}
			else if(String.valueOf(n).length()==3)
			{
				drawing += n+ getSpace(n) +"  B|";
			}
			else
			{
				drawing += n+ getSpace(n) +" B|";
			}
		}

		// used bluely by addRow methods
		/**
		 * Adds blank space to the drawing
		 */
		private void addMiddle()
		{
			drawing += space+"      |";
		}

		/**
		 * If any/both of the token are at the given positions, 
		 * the method draws them, indicates that they have been 
		 * placed and returns true. Otherwise it returns false.
		 */
		private boolean addToken(int pos)
		{
			if((red == pos) && (blue == pos))
			{
				drawing += " "+bluesymbol+"  "+redsymbol+space+" |";
				redplaced = true;
				blueplaced = true;
				return true;
			}
			else if(red == pos)
			{
				drawing += "   "+redsymbol+space+"  |";
				redplaced = true;
				return true;
			}
			else if(blue == pos)
			{
				drawing += "   "+bluesymbol+space+"  |";
				blueplaced = true;
				return true;
			}
			return false;
		}

		// used lastly by addRow methods
		/**
		 * draws the footer of the cells in a row.
		 */
		private void addFooter()
		{
			drawing += dash+"______|";
		}

		// used at the end of every row by addRow methods
		private void addFinish()
		{
			drawing += " |\n";
		}
		
		/**
		 * This method draws the bottom of the board. The base consists of two borders 
		 * and a line between them. The line is drawn using a for-loop. 
		 * The last line of the base is smaller than the rest, 
		 * to keep it from going outside the board.
		 */
		private void addBase()
		{
			drawing += "|__";
			for(int i=0; i<columns; i++)
			{
				if(i==(columns-1))
				{
					drawing += "______"+dash;;
				}
				else
				{
					drawing += "_______"+dash;;
				}
			}
			drawing += "__|\n";
		}
		
		/**
		 * This method designs the spaces and lines needed to draw the 
		 * board accurately, using the size of the board as a basis. 
		 * If the size of the board is greater than 3 digits, then the 
		 * lines and spaces on the board drawing are enlarged. 
		 */
		private void makeExtra()
		{
			int size = String.valueOf(rows*columns).length()-3;
			if(size>0)
			{
				for(int i=0; i<size; i++)
				{
					space += " ";
					dash += "_";
				}
			}
		}
		
		/**
		 * returns a space lesser by one bit, if the current cell is the 
		 * final cell and the final cell's number has more than three digits. 
		 * Otherwise it returns the current space. This helps to all the cell 
		 * in line.
		 */
		private String getSpace(int n)
		{
			if(String.valueOf(n).length()==String.valueOf(finalsq).length() && String.valueOf(finalsq).length()>3)
			{
				return space.substring(1);
			}
			return space;
		}

		/**
		 * returns the reflection of the current position.
		 * The reflection is found between the lowerbound 
		 * and the upperbound of the given position
		 * (For example, the lowerbound of 88 = 80 and 
		 * upperbound of 88 = 90). However if the position 
		 * is divisible by the size of the column, then the 
		 * lowerbound is decremented by that size. 
		 * (For example, the lowerbound of 90 on a 10-by-10 
		 * board is 80 and its upperbound is itself). The 
		 * upperbound is simply the size of the columns added 
		 * to the lowerbound. The variable 'current' is initially 
		 * assigned to the value of the upperbound. The integer 
		 * array 'reverse' holds the positions of the cells in 
		 * the row in a reverse order. The reflection is eventually 
		 * found by putting the given cell's place in the row, 
		 * inside the reversed row, and getting the item at that 
		 * index.
		 */
		private int mirror(int n)
		{
			int lowerbound = (n/columns)*columns;
			if(n%columns==0)
			{
				lowerbound -= columns;
			}
			int upperbound = lowerbound+columns;
			int current = upperbound;
			int[] reverse = new int[columns];
			for(int i=0; i<columns; i++)
			{
				reverse[i] = current;
				current--;
			}
			
			return reverse[(n-lowerbound)-1];
		}
		
		public String getSymbol(Token t)
		{
			if(t.getColor().equals("red"))
			{
				return "♙";
			}
			else if(t.getColor().equals("blue"))
			{
				return "♟";
			}
			else
			{
				return null;
			}
		}

		// directly used by CLI
		public void moveRed(int position)
		{
			red = position;
		}

		public void moveBlue(int position)
		{
			blue = position;
		}

		/**
		 * draws the entire board using the methods above. It reinitializes 
		 * the variables that are instrumental in those methods, before adding 
		 * the roof of the board, filling in the rows (alternating directions), 
		 * and finally drawing the base of the board.
		 */
		public void draw()
		{	
			drawing = "";
			int size = rows*columns;
			curpos = size;
			tokenpos = size;
			alternate = (rows%2==0) ? false : true;
			redplaced = false;
			blueplaced = false;

			addRoof();
			for(int i=0; i<rows; i++)
			{
				if(alternate)
				{
					addRowFromLeft();
				}
				else
				{
					addRowFromRight();
				}
			}
			addBase();
		}

		public void display()
		{
			System.out.print(drawing);
		}
	}
	
	/**
	 * provides an ASCII representation of the game's six-sided die.
	 */
	private static final class DieDrawing
	{
		private String drawing;
		private final String space = "		"; 
		public DieDrawing(Die die)
		{
			int n = die.getNumberRolled();
			if(n==1)
			{
				drawing = one();
			}
			else if(n==2)
			{
				drawing = two();
			}
			else if(n==3)
			{
				drawing = three();
			}
			else if(n==4)
			{
				drawing = four();
			}
			else if(n==5)
			{
				drawing = five();
			}
			else if(n==6)
			{
				drawing = six();
			}
		}

		private String one()
		{
			String die = space+" _______\n";
			die += space+"|       |\n";
			die += space+"|   •   |\n";
			die += space+"|       |\n";
			die += space+"|_______|";
			return die;
		}

		private String two()
		{
			String die = space+" _______\n";
			die += space+"| •     |\n";
			die += space+"|       |\n";
			die += space+"|     • |\n";
			die += space+"|_______|\n";
			return die;
		}

		private String three()
		{
			String die = space+" _______\n";
			die += space+"| •     |\n";
			die += space+"|   •   |\n";
			die += space+"|     • |\n";
			die += space+"|_______|\n";
			return die;
		}

		private String four()
		{
			String die = space+" _______\n";
			die += space+"| •   • |\n";
			die += space+"|       |\n";
			die += space+"| •   • |\n";
			die += space+"|_______|";
			return die;
		}

		private String five()
		{
			String die = space+" _______\n";
			die += space+"| •   • |\n";
			die += space+"|   •   |\n";
			die += space+"| •   • |\n";
			die += space+"|_______|";
			return die;
		}

		private String six()
		{
			String die = space+" _______\n";
			die += space+"| •   • |\n";
			die += space+"| •   • |\n";
			die += space+"| •   • |\n";
			die += space+"|_______|";
			return die;
		}

		public void display()
		{
			System.out.println(drawing);
		}
	}
	
	/**
	 * provides an ASCII representation for the scores accumulated by the given players.
	 */
	private static final class ScoreBoard
	{
		private String name1;
		private String name2;
		private int score1;
		private int score2;
		private String drawing;

		public ScoreBoard()
		{
			this("HOME", "AWAY");
		}

		public ScoreBoard(String name1, String name2)
		{
			this(name1, name2, 0, 0);
		}
		
		/**
		 * Only the first four letters of the names of the players are shown. 
		 * They are shown in uppercase. If both are equal the first character 
		 * of their name is shown, followed by their number.
		 */
		public ScoreBoard(String nm1, String nm2, int s1, int s2)
		{
			name1 = (nm1.length()>4) ? nm1.substring(0, 4) : nm1;
			name1 = name1.toUpperCase();
			name2 = (nm2.length()>4) ? nm2.substring(0, 4) : nm2;
			name2 = name2.toUpperCase();
			if(name1.equals(name2))
			{
				name1 = name1.substring(0, 1)+1;
				name2 = name2.substring(0, 1)+2;
			}
			score1 = s1;
			score2 = s2;
			drawing = "";
		}

		public void updateScores(int s1, int s2)
		{
			score1 = s1;
			score2 = s2;
			draw();
		}

		/**
		 * draws the scoreboard, a box showing the names and scores of the two players. 
		 * It adds extra lines to the box if the display is bigger than 31 characters.
		 */
		private void draw()
		{
			String display = "|   "+name1+" : "+score1+"   |   "+name2+" : "+score2+"   |";
			drawing = "";
			drawing += " _____________________________";
			if(display.length()>31)
			{
				addExtra(display.length()-31);
			}
			drawing += "\n";
			drawing += display;
			drawing += "\n";
			drawing += "|_____________________________";
			if(display.length()>31)
			{
				addExtra(display.length()-31);
			}
			drawing += "|\n";
		} 

		private void addExtra(int n)
		{
			for(int i=0; i<n; i++)
			{
				drawing += "_";
			}
		}

		public void display()
		{
			System.out.print(drawing);
		}
	}
	
	/**
	 * provides an ASCII box showing the name of the winner.
	 */
	private static final class WinnerDisplay
	{
		private String name;
		private String drawing;

		public WinnerDisplay(String name)
		{
			this.name = name;
			drawing = "";
		}
		
		/**
		 * very similar to the method of the same name in the ScoreBoard class.
		 */
		public void draw()
		{
			String display = "|     WINNER : "+name+"     |";
			drawing = "";
			drawing += " ____________________";
			if(display.length()>22)
			{
				addExtra(display.length()-22);
			}
			drawing += "\n";
			drawing += display;
			drawing += "\n";
			drawing += "|____________________";
			if(display.length()>22)
			{
				addExtra(display.length()-22);
			}
			drawing += "|\n";
		} 

		private void addExtra(int n)
		{
			for(int i=0; i<n; i++)
			{
				drawing += "_";
			}
		}

		public void display()
		{
			System.out.print(drawing);
		}
	}
}