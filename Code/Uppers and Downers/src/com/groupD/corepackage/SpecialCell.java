package com.groupD.corepackage;

/**
 * @author Dominic
 */
public class SpecialCell extends Cell
{
    
    public enum cellType {
        UPPER, DOWNER, BONUS
    }
    
    cellType type;
    
    public SpecialCell(int cellnumber, String type)
    {
        super(cellnumber);
        
        if(type.toLowerCase().equals("upper"))
            this.type = cellType.UPPER;
        else if(type.toLowerCase().equals("downer"))
            this.type=cellType.DOWNER;
        else
            this.type=cellType.BONUS;
    }
    
    public Enum getCellType(){
        return type;
    }
}
