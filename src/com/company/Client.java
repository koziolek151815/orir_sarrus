package com.company;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private Client() {}
    public static void main(String[] args) {
        System.err.println("Client:");
        try {
            // Looking up the registry for the remote object
            ServerInterface inter = (ServerInterface) Utility.WaitForRegistry("ServerInterface");
            float matrix[][] = { { 1, 6, 3 }, { 3, 4,5 }, { 1, 2, 3 } };

            // Calling the remote method using the obtained object
            System.out.println("Starting det calculation");
            float result = inter.calcDet(matrix);
            System.out.println("Matrix det : " + result);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
