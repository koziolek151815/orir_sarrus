package com.company;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public Server() {}
    public static void main(String args[]) {
        try {
            System.err.println("Server:");

            // Instantiating the implementation class
            ServerImpl obj = new ServerImpl();

            // Exporting the object of implementation class
            // (here we are exporting the remote object to the stub)
            ServerInterface stub = (ServerInterface) UnicastRemoteObject.exportObject(obj, 0);

            // Binding the remote object (stub) in the registry
            Registry registry = LocateRegistry.getRegistry();

            registry.bind("ServerInterface", stub);
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
