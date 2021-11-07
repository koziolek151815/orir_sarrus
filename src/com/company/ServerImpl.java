package com.company;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.*;

// Implementing the remote interface
public class ServerImpl implements ServerInterface {
    AgentInterface agent1;
    AgentInterface agent2;
    AgentInterface agent3;


    public ServerImpl() throws InterruptedException {
        agent1 = (AgentInterface)Utility.WaitForRegistry("Agent1");
        agent2 = (AgentInterface)Utility.WaitForRegistry("Agent2");
        agent3 = (AgentInterface)Utility.WaitForRegistry("Agent3");
    }

    public void awaitTerminationAfterShutdown(ExecutorService threadPool) {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(10, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public float calcDetSingleAgent(float[][] matrix) throws RemoteException, InterruptedException, ExecutionException, TimeoutException {
        ExecutorService service = Executors.newFixedThreadPool(1);

        System.out.println("Starting multiplying");

        Future<Float> future1 = service.submit(()->agent1.multiply(matrix[0][0], matrix[1][1], matrix[2][2]));
        Future<Float> future2 = service.submit(()->agent1.multiply(matrix[1][0], matrix[2][1], matrix[0][2]));
        Future<Float> future3 = service.submit(()->agent1.multiply(matrix[2][0], matrix[0][1], matrix[1][2]));

        Future<Float> future4 = service.submit(()->agent1.multiply(matrix[2][0], matrix[1][1], matrix[0][2]));
        Future<Float> future5 = service.submit(()->agent1.multiply(matrix[1][0], matrix[0][1], matrix[2][2]));
        Future<Float> future6 = service.submit(()->agent1.multiply(matrix[0][0], matrix[2][1], matrix[1][2]));

        System.out.println("Waiting for multiply to end");
        future1.get(5, TimeUnit.SECONDS);
        future2.get(5, TimeUnit.SECONDS);
        future3.get(5, TimeUnit.SECONDS);
        future4.get(5, TimeUnit.SECONDS);
        future5.get(5, TimeUnit.SECONDS);
        future6.get(5, TimeUnit.SECONDS);
        System.out.println("Finished multiplying");


        System.out.println("Starting adding");
        Future<Float> future7 = service.submit(()->agent1.add(future1.get(), future2.get(), future3.get()));
        Future<Float> future8 = service.submit(()->agent1.add(future4.get(), future5.get(), future6.get()));

        System.out.println("Waiting for adding to end");
        future7.get(5, TimeUnit.SECONDS);
        future8.get(5, TimeUnit.SECONDS);
        System.out.println("Finished adding");

        System.out.println("Starting subtraction");
        Future<Float> result = service.submit(()->agent1.sub(future7.get(), future8.get()));

        System.out.println("Waiting for subtraction to end");

        return result.get(5, TimeUnit.SECONDS);
    }

    @Override
    public float calcDet(float[][] matrix) throws RemoteException, InterruptedException, ExecutionException, TimeoutException {
        ExecutorService service = Executors.newFixedThreadPool(3);

        System.out.println("Starting multiplying");

        Future<Float> future1 = service.submit(()->agent1.multiply(matrix[0][0], matrix[1][1], matrix[2][2]));
        Future<Float> future2 = service.submit(()->agent2.multiply(matrix[1][0], matrix[2][1], matrix[0][2]));
        Future<Float> future3 = service.submit(()->agent3.multiply(matrix[2][0], matrix[0][1], matrix[1][2]));

        Future<Float> future4 = service.submit(()->agent1.multiply(matrix[2][0], matrix[1][1], matrix[0][2]));
        Future<Float> future5 = service.submit(()->agent2.multiply(matrix[1][0], matrix[0][1], matrix[2][2]));
        Future<Float> future6 = service.submit(()->agent3.multiply(matrix[0][0], matrix[2][1], matrix[1][2]));

        System.out.println("Waiting for multiply to end");
        future1.get(5, TimeUnit.SECONDS);
        future2.get(5, TimeUnit.SECONDS);
        future3.get(5, TimeUnit.SECONDS);
        future4.get(5, TimeUnit.SECONDS);
        future5.get(5, TimeUnit.SECONDS);
        future6.get(5, TimeUnit.SECONDS);
        System.out.println("Finished multiplying");


        System.out.println("Starting adding");
        Future<Float> future7 = service.submit(()->agent1.add(future1.get(), future2.get(), future3.get()));
        Future<Float> future8 = service.submit(()->agent2.add(future4.get(), future5.get(), future6.get()));

        System.out.println("Waiting for adding to end");
        future7.get(5, TimeUnit.SECONDS);
        future8.get(5, TimeUnit.SECONDS);
        System.out.println("Finished adding");

        System.out.println("Starting subtraction");
        Future<Float> result = service.submit(()->agent1.sub(future7.get(), future8.get()));

        System.out.println("Waiting for subtraction to end");

        return result.get(5, TimeUnit.SECONDS);
    }
}
