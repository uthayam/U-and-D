package com.groupD.corepackage;

/**
 *
 * @author Uthayam
 */
public class Board
{ 
    private int rows; 
    private int columns; 
    private Cell[] cells;
    private int[] uppers;
    private int[] downers;
    private int[] bonuses;
    private int[] upperScores;
    private int[] downerScores;
    private int[] bonusScores;
    
    public Board()
    {
        this(10,10);
    }
    
    public Board(int rows, int columns)
    {
        this.rows = rows; 
        this.columns = columns;
        cells = new Cell[rows*columns];
	int upperA[] = {2, 28, 38, 47, 80}; 
        int upperB[] = {24, 52, 42, 84, 99};
        int downerA[] = {26, 56, 72, 93, 98}; 
        int downerB[] =  {14, 27, 70, 74 ,44};
	int bonuses[] = new int[0];
        setCells(upperA, upperB, downerA, downerB, bonuses);
	upperScores = new int[upperA.length];
	downerScores = new int[downerA.length];
	bonusScores = new int[bonuses.length];
    }

    public Board(int rows, int columns, int[] upperA, int[] upperB, int[] upperS, int[] downerA, int[] downerB, int[] downerS, int[] bonus,
        int[] bonusSc) throws IllegalArgumentException
    {
        if(upperA.length != upperB.length || upperA.length != upperS.length || downerA.length != downerB.length 
            || downerA.length != downerS.length || bonus.length != bonusSc.length) 
        {
            throw new IllegalArgumentException("Numbers of positions, destinations, and scores must be equal to each other.");
        }
        this.rows = rows; 
        this.columns = columns;
        cells = new Cell[rows*columns];
        setCells(upperA, upperB, downerA, downerB, bonus);
        upperScores = upperS;
        downerScores = downerS;
        bonusScores = bonusSc;
    }
    
    private void setCells(int[] upperA, int[] upperB, int[] downerA, int[] downerB, int[] bonus)
    {        
	uppers = upperA;
        int upCount = 0; 
	downers = downerA;
	bonuses = bonus;
        int downCount = 0; 
        
        for(int i=0; i<cells.length; i++)
        {
            if(contains(i+1, upperA))
            {
                cells[i] =  new SpecialCell(upperB[upCount], "UPPER");
                upCount++;
            }
            else if (contains(i+1, downerA))
            {
                cells[i] =  new SpecialCell(downerB[downCount], "DOWNER");
                downCount++;
            }
            else if (contains(i+1, bonus))
            {
                cells[i] =  new SpecialCell(i+1, "BONUS");
            }
            else
                cells[i] =  new Cell(i+1);
        }
    }
    
    private boolean contains(int num, int[] array)
    {
        for(int j=0; j<array.length; j++)
        {
            if(num == array[j])
            return true;
        }
        return false; 
    }
    
    public Cell getCell(int n)
    {
        return cells[n-1];
    }
    
    public Cell [] getCell()
    {
        return cells;
    }

    public int[] getUppers()
    {
	return uppers;
    }
	
    public int[] getDowners()
    {
	return downers; 
    }
	
	public int[] getBonuses()
	{
            return bonuses;
	}

	public void occupyCell(int n)
	{
            (cells[n-1]).fill(true);
	}
	
	public void leaveCell(int n)
	{
            (cells[n-1]).fill(false);
	}

    public int getRows()
    {
        return rows;
    }

	public void setRows(int r)
	{
            rows = r;
	}
    
    public int getColumns()
    {
        return columns;
    }
	
	public void setColumns(int c)
	{
            columns = c;
	}
	
	public int[] getUpperScores()
	{
            return upperScores;
	}
	
	public void setUpperScores(int[] us)
	{
            upperScores = us;
	}
	
	public int[] getDownerScores()
	{
            return downerScores;
	}
	
	public void setDownerScores(int[] ds)
	{
            downerScores = ds;
	}
	
	public int[] getBonusScores()
	{
            return bonusScores;
	}
	
	public void setBonusScores(int[] bs)
	{
            bonusScores = bs;
	}
}

