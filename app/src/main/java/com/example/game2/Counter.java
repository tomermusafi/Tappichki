package com.example.game2;

public class Counter {
    private int counter;

    public Counter(int counter) {
        this.counter = counter;
    }
    public Counter() {

    }

    public int getCounter() {
        return counter;
    }

    public Counter setCounter(int counter) {
        this.counter = counter;
        return this;
    }

    public void increaseCounter(){
        counter = counter ++;
        setCounter(counter);
    }
}
