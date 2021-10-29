package com.company;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Utility {
    public static Remote WaitForRegistry(String name) throws InterruptedException {
        Remote result = null;
        do {
            try {
                Registry registry = LocateRegistry.getRegistry(null);
                result = registry.lookup(name);
            }
            catch (Exception e) {
                System.err.println("Could not find remote! Retrying in a second!");
                Thread.sleep(1000);
            }
        }
        while (result == null);
        return result;
    }
}
