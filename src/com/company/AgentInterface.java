package com.company;

import java.rmi.Remote;
import java.rmi.RemoteException;

// Creating Remote interface for our application
public interface AgentInterface extends Remote {
    float multiply(float a, float b, float c) throws RemoteException;
    float add(float a, float b, float c) throws RemoteException;
    float sub(float a, float b) throws RemoteException;
}
