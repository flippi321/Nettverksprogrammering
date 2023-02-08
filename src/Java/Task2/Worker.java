package Java.Task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Worker extends Thread {
    List<Thread> threads;

    /**
     * Constructor for Worker Class
     * @param numberOfThreads the amount of threads we want to execute tasks
     */
    public Worker(int numberOfThreads) {
        threads = new ArrayList<>();
        for(int i = 0; i < numberOfThreads; i++){
            threads.add(null);
        }
    }

    public int findFreeThread(Thread task){
        for(int i = 0; i < threads.size(); i++){
            // If we have a free slot, fill the slot with a new Thread
            if (threads.get(i)==null) {
                threads.add(i, task);
                return i;
            } else if (!threads.get(i).isAlive()){
                threads.add(i, task);
                return i;
            }
        }
        return -1;
    }

    public void post(Thread task) {
        int pos;
        //Busy Wait
        while((pos = findFreeThread(task)) == -1) { }
        threads.get(pos).start();
    }

    public void joinWorkers() {
        while(!threads.isEmpty()){
            for(Thread thread : threads){
                if (thread.isAlive()){
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}