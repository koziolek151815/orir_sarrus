package com.company;

import java.rmi.Remote;
import java.rmi.RemoteException;

// Creating Remote interface for our application
public interface ServerInterface extends Remote {
    float calcDet(float[][] matrix) throws RemoteException;
}
