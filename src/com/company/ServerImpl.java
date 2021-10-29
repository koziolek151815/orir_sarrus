package com.company;

import java.rmi.RemoteException;

// Implementing the remote interface
public class ServerImpl implements ServerInterface {

    @Override
    public float calcDet(float[][] matrix) throws RemoteException {
        return 0;
    }
}
