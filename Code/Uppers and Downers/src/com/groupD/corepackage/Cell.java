package com.groupD.corepackage;

/**
 *
 * @author Dominic
 */
public class Cell {
    private int cellNumber;
    private Boolean isOccupied; 
    private Token owner;    // ?
    
    public Cell(int cellnumber){
        cellNumber = cellnumber;
	isOccupied = false;
    }
    
    public int getCellNumber(){
        return cellNumber;
    }
    
    public boolean isOccupied(){
        return isOccupied;
    }
    
    public void fill(boolean b){
        isOccupied = b; 
    }
    
    public Token getOwner(){
        return owner;
    }
    
    public void setOwner(Token t){
        owner = t;
        isOccupied = true;
    }
}

