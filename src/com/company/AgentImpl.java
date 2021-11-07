package com.company;

import java.rmi.RemoteException;

public class AgentImpl implements AgentInterface{

    @Override
    public float multiply(float a, float b, float c) throws RemoteException, InterruptedException {
        Thread.sleep(500);
        return a*b*c;
    }

    @Override
    public float add(float a, float b, float c) throws RemoteException, InterruptedException {
        Thread.sleep(500);
        return a+b+c;
    }

    @Override
    public float sub(float a, float b) throws RemoteException, InterruptedException {
        Thread.sleep(500);
        return a-b;
    }
}
