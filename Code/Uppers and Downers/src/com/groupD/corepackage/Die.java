package com.groupD.corepackage;

/**
 *
 * @author madina
 */
public class Die
{
    private int numberOfSides;
    private int numberRolled;   

    public Die() {
        //the default constructor creates a 6-sided die
        this.numberOfSides = 6;
    }

    public int getNumberOfSides() {
        return numberOfSides;
    }


    public int getNumberRolled() {
        return numberRolled;
    }

    public void setNumberRolled(int numberRolled) throws IllegalArgumentException
    {
             if(numberRolled < 0 || numberRolled > this.getNumberOfSides()){
                     throw new IllegalArgumentException("Must be a number between 1 and "+ this.getNumberOfSides());
             }
            this.numberRolled = numberRolled;
    }
    
    public void roll(){
        int n = (int)(numberOfSides * Math.random()) + 1;
        setNumberRolled(n);
    }

}