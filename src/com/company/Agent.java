package com.company;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Agent {

    public Agent() {}
    public static void main(String args[]) {
        try {
            System.err.println("Agent" + args[0] + ":");
            // Instantiating the implementation class
            AgentImpl obj = new AgentImpl();

            // Exporting the object of implementation class
            // (here we are exporting the remote object to the stub)
            AgentInterface stub = (AgentInterface) UnicastRemoteObject.exportObject(obj, 0);

            // Binding the remote object (stub) in the registry
            Registry registry = LocateRegistry.getRegistry(null);
            registry.bind("Agent" + args[0], stub);
            System.err.println("Agent" + args[0] + " ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
