/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.groupD.corepackage;

/**
 *
 * @author Dominic
 */
public class Cell {
    private int CellNumber;
    private Boolean isOccupied; 
    private Token owner;
    
    public Cell(int cellnumber){
        CellNumber = cellnumber;
    }
    
    public int getCellNumber(){
        return CellNumber;
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
