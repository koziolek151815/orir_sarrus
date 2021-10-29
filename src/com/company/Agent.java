package com.company;

public class Agent implements Runnable {
    private volatile float result;
    private float a;
    private float b;
    private float c;

    public Agent(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void run(){
        setResult(this.a * this.b * this.c);
    }

    public void setResult(float result) {
        this.result = result;
    }

    public float getResult() {
        return result;
    }
}
