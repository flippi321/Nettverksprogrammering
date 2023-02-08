package Java.Task2;

import java.util.ArrayList;
import java.util.List;

public class Worker extends Thread {
    List<Thread> threads;

    /**
     * Constructor for Worker Class
     * @param numberOfThreads the amount of threads we want to execute tasks
     */
    public Worker(int numberOfThreads) {
        threads = new ArrayList<>(numberOfThreads);
        for(int i = 0; i < numberOfThreads; i++){
            threads.add(new Thread());
        }
    }

    public int findFreeThread(Thread task){
        for(int i = 0; i < threads.size(); i++){
            // If we have a free slot, fill the slot with a new Thread
            if (!threads.get(i).isAlive()){
                threads.set(i, task);
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

    public int size(){
        return threads.size();
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