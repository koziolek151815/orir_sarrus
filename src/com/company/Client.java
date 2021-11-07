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
            float matrix[][] = { { 0, 6, 3 }, { 3, 4,5 }, { 1, 2, 3 } };

            long timeElapsed1;
            long timeElapsed2;
            {
                System.out.println("Starting det calculation for 3 agents");

                long start = System.currentTimeMillis();
                float result = inter.calcDet(matrix);
                long finish = System.currentTimeMillis();
                timeElapsed1 = finish - start;

                System.out.println("Matrix det : " + result + " Finished in " + timeElapsed1 + " ms");
            }
            {
                System.out.println("Starting det calculation for 1 agent");

                long start = System.currentTimeMillis();
                float result = inter.calcDetSingleAgent(matrix);
                long finish = System.currentTimeMillis();
                timeElapsed2 = finish - start;

                System.out.println("Matrix det : " + result + " Finished in " + timeElapsed2 + " ms");
                System.out.println("Acceleration : " + (float)timeElapsed2/(float)timeElapsed1);
            }


        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
