package com.groupD.corepackage;

/**
 *
 * @author fayimora & rahat
 */
public class ComputerPlayer extends Player
{
    static int count=0;
    private final String computerName = "ComputerPlayer"+(++count);
    
    public ComputerPlayer(String name, Token token)
    {
        super(name, token);
    }

    /*         AI         */
    /* @author Rahat
    ** Will return a 1 or -1 
    ** Where 1 will cause player to move forward
    **  and -1 will cause it to go backwards
     * 
     * @ parameter int n = number rolled on die
    */
    public int getDecision(int n, Board b)
    {
        //x is forward, y is backwards
	Token aToken = this.getToken();
        int x = aToken.getPosition() + n;
        int y = aToken.getPosition()-n;
        int max = b.getCell().length;
        //check if opponent can be bumped
        if (x< max && b.getCell(x).isOccupied()){
            return 1;
        }
        else if (y>0 && b.getCell(y).isOccupied()){
            return 0;
        }

        //check for ladder
        for (int i=0; i<b.getUppers().length; i++){
            if (x < max && x == b.getUppers()[i]){
                return 1;
            }
            else if (y > 0 && y == b.getUppers()[i]) {
                return -1;
            }
        }
        
        // try avoid snake
        for (int i=0; i<b.getDowners().length; i++) {
            int lowest;
            if (x < max && x == b.getDowners()[i]){
                lowest = b.getCell(x).getCellNumber();
                for (int j=0; j<b.getDowners().length-1; j++){
                    if (y > 0 && y == b.getDowners()[j]) {
                        if (lowest > y){
                            return 1;
                        }
                        return -1;
                    }
                }
                return -1;
            }
        }
        
        // make a normal move
        return 1;
    }
}


