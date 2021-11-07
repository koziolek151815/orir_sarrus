package com.company;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

// Creating Remote interface for our application
public interface ServerInterface extends Remote {

    float calcDetSingleAgent(float[][] matrix) throws RemoteException, InterruptedException, ExecutionException, TimeoutException;
    float calcDet(float[][] matrix) throws RemoteException, InterruptedException, ExecutionException, TimeoutException;
}
