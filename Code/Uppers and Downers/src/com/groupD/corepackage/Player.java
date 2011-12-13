package com.groupD.corepackage;
/**
 *
 * @author rahat
 */
public abstract class Player {
    private String name;
    private Token token;
    private int totalScore;
    private int highestScore;
    private int gamesPlayed;
    private int gamesWon;

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
    
    public void updateScoreboard (boolean win) {
        if (win) {
            this.gamesWon++;
            this.gamesPlayed++;
            this.totalScore++;
            if (totalScore > highestScore){
                this.highestScore = this.totalScore;
            }
        }
        else {
            this.gamesPlayed++;
        }
    }

    public Token getToken() {
        return this.token;
    }

    public void moveToken(int reposition) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    
}
