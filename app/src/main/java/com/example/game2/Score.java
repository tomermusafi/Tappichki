package com.example.game2;

public class Score {

    private String userName = "";
    private int score;

    public Score() {
    }

    public Score(String userName, int score) {
        this.userName = userName;
        this.score = score;

    }

    public String getUserName() {
        return userName;
    }

    public Score setUserName(String title) {
        this.userName = title;
        return this;
    }

    public int getScore() {
        return score;
    }

    public Score setScore(int score) {
        this.score = score;
        return this;
    }
}
