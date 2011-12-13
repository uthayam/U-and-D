package com.groupD.corepackage;

/**
 *
 * @author Uthayam
 */
public class Board
{ 
    private int rows; 
    private int columns; 
    private Cell cells[] = new Cell[100];
    
    public Board()
    {
        this(10,10);
    }
    
    public Board(int rows, int columns)
    {
        this.rows = rows; 
        this.columns = columns; 
        setCells();
    }
    
    private void setCells()
    {        
        int upperA[] = {2, 28, 38, 47, 80}; 
        int upperB[] = {24, 52, 42, 84, 99};
        int upCount = 0; 
        int downerA[] = {24, 56, 72, 93, 98}; 
        int downerB[] =  {14, 27, 70, 74 ,44}; 
        int downCount = 0; 
        
        for(int i=0; i<cells.length; i++)
        {
            if(contains(i+1, upperA))
            {
                cells[i] =  new SpecialCell(upperB[upCount], "UPPER");
                System.out.println(i); 
                upCount++;
            }
            else if (contains(i+1, downerA))
            {
                cells[i] =  new SpecialCell(downerB[downCount], "DOWNER");
                System.out.println(i); 
                downCount++;
            }
            
            else
                cells[i] =  new Cell(i+1);
        }
    }
    
    public boolean contains(int num, int []array)
    {
        for(int j=0; j<array.length; j++)
        {
            if(num == array[j])
                return true;
        }
        return false; 
    }
    
   /* public int getRows()
    {
        return rows; 
    }
    
    public int getColumns()
    {
        return columns; 
    } */

    public Cell getCell(int i) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}

