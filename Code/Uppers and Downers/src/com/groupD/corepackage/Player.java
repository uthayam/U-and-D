package com.groupD.corepackage;

/**
 *
 * @author rahat
 */

public abstract class Player {
    protected String name;
    protected Token token;
    protected int totalScore;
    protected int highestScore;
    protected int gamesPlayed;
    protected int gamesWon;

    public Player(String name, Token token) {
        this(name,token,0,0,0,0);
    }
    
    public Player(String name, Token token, int totalScore, int highestScore, int gamesPlayed, int gamesWon) {
        this.name = name;
        this.token = token;
        this.totalScore = totalScore;
        this.highestScore = highestScore;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
    }

    public String getName() {
	return name;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public int getTotalScore() {
        return totalScore;
    }
	
    public void updateTotalScore(int score){	
	this.totalScore += score;
    }
    
    public void updateScoreboard (boolean win) {
        if (win) {
            this.gamesWon++;
            this.gamesPlayed++;
            if (totalScore > highestScore){
                this.highestScore = this.totalScore;
            }
        }
        else {
            this.gamesPlayed++;
        }
    }
    
    public void moveToken(int to)                  
    {
	token.move(to);
    }
    
    public Token getToken()
    {
        return token;
    }
}