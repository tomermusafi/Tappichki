package com.example.game2;

class Record {

    private int score;
    private String userName;

    public Record(int score , String userName) {
        this.score = score;
        this.userName = userName;
    }

    public int getScore() {
        return score;
    }

    public Record setScore(int score) {
        this.score = score;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public Record setUserName(String userName) {
        this.userName = userName;
        return this;
    }
}
