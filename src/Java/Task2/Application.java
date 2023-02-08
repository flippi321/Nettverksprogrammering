package Java.Task2;

public class Application {
    public static void main(String[] args) {
        Worker workerThreads = new Worker(4);
        Worker eventLoop = new Worker(1);

        workerThreads.start();
        eventLoop.start();

        // Task A
        workerThreads.post(new Thread() {
            @Override
            public void run() {
                System.out.println("This is Task A");
            }
        });

        // Task B
        workerThreads.post(new Thread() {
            @Override
            public void run() {
                System.out.println("This is task B, 1+1 = " + (1+1));
            }
        });

        // Task C
        eventLoop.post(new Thread() {
            @Override
            public void run() {
                System.out.println("This is task C, 5 * 5 = " + (5*5));
            }
        });

        // Task D
        eventLoop.post(new Thread() {
            @Override
            public void run() {
                System.out.println("This is task D");
            }
        });

        // Join
        try{
            workerThreads.join();
            eventLoop.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
