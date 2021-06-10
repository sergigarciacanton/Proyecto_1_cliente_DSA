package com.example.dsa.models;

public class Game {
    private int id;
    private int playerId;
    private int duration;
    private int victory;
    private int score;

    public Game(){}

    public int getId() {
        return id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getDuration() {
        return duration;
    }

    public int getVictory() {
        return victory;
    }

    public int getScore() {
        return score;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setVictory(int victory) {
        this.victory = victory;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
