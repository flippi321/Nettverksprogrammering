package Java.Task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Worker  {
    List<Thread> threads;
    List<Thread> tasks;
    ReentrantLock lock;
    int currentPos;
    boolean active;

    /**
     * Constructor for Worker Class
     * @param numberOfThreads the amount of threads we want to execute tasks
     */
    public Worker(int numberOfThreads) {
        lock = new ReentrantLock();
        active = true;
        threads = new ArrayList<>(numberOfThreads);
        tasks = new ArrayList<>();

        // Create Threads
        for(int i = 0; i < numberOfThreads; i++){
            threads.add(new Thread());
        }
    }

    public void start() {
        // If we have unfinished tasks
        while (!tasks.isEmpty()){
            // Find free Thread which can execute task
            if ((currentPos = findFreeThread(tasks.get(0))) != -1) {
                // Execute Thread with new task
                threads.get(currentPos).start();
            }
        }
        active = false;
    }

    public void stop(){
        active = false;
    }

    public int findFreeThread(Thread task){
        for(int i = 0; i < threads.size(); i++){
            // If we have a free slot, fill the slot with a new Thread
            if (!threads.get(i).isAlive()){
                threads.set(i, task);
                tasks.remove(0);
                return i;
            }
        }
        return -1;
    }

    public void post(Thread task) {
        tasks.add(task);
        lock.lock();

        try {
            if(!active) {
                active = true;
                start();
            }
        } finally {
            lock.unlock();
        }

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

    public void join() throws InterruptedException {
        active = false;
        for(Thread thread : threads){
            thread.join();
        }
    }
}