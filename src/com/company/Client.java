package com.company;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    private Client() {}
    public static void main(String[] args) {
        try {
            // Getting the registry
            Registry registry = LocateRegistry.getRegistry(null);

            // Looking up the registry for the remote object
            ServerInterface stub = (ServerInterface) registry.lookup("Hello");
            float matrix[][] = { { 1, 2, 3 }, { 3, 4,5 }, { 1, 2, 3 } };
            // Calling the remote method using the obtained object
            stub.calcDet(matrix);
            // System.out.println("Remote method invoked");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
