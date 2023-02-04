package Java.Task2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Worker {
    private final ExecutorService executor;

    public Worker(int numberOfThreads) {
        executor = Executors.newFixedThreadPool(numberOfThreads);
    }

    public void post(Runnable task) {
        // Execute code
        executor.execute(task);
    }

    public void join() {
        executor.shutdown();
    }
}