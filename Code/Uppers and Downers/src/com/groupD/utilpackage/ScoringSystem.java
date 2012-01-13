package com.groupD.utilpackage;

/**
 *
 * @author mesfin
 */

import com.groupD.corepackage.*;
import java.util.*;

public class ScoringSystem 
{
    private Board board;
    private HashMap<Integer,Integer> table;
    private boolean automatic;
    
    public ScoringSystem(Board board, boolean automatic)
    {
        this.board = board;
        this.table = new HashMap<Integer,Integer>();
	this.automatic = automatic;
        this.assignScores(automatic);
    }
    
    private void assignScores(boolean automatic)
    {
	int[] uppers = board.getUppers();
	int[] downers = board.getDowners();
	int[] bonuses = board.getBonuses();
		
	if(automatic)
	{
            int upper = 10;
            int downer = -10;
            int bonus = 50;

            for(int i=0; i<uppers.length; i++)
            {
		table.put(uppers[i], upper);
            	upper *= 2;
            }
            for(int i=0; i<downers.length; i++)
            {
		table.put(downers[i], downer);
            	downer *= 2;
            }
            for(int i=0; i<bonuses.length; i++)
            {
		table.put(bonuses[i], bonus);
            }
	}
        else
        {
            int[] upperScores = board.getUpperScores();
            int[] downerScores = board.getDownerScores();
            int[] bonusScores = board.getBonusScores();
			
            for(int i=0; i<uppers.length; i++)
            {
		table.put(uppers[i], upperScores[i]);
            }
            for(int i=0; i<downers.length; i++)
            {
		table.put(downers[i], downerScores[i]);
            }
            for(int i=0; i<bonuses.length; i++)
            {
		table.put(bonuses[i], bonusScores[i]);
            }
	}
    }
    
    public int getScore(int position)
    {
        Integer score = table.get(position);
        if(score == null)
        {
            return 0;
        }
        return score;
    }
	
    public HashMap<Integer,Integer> getTable()
    {
	return table;
    }
	
    public boolean isAutomatic()
    {
	return automatic;
    }
}
