package Java.Task2;

public class Application {
    public static void main(String[] args) {
        Worker workerThreads = new Worker(4);
        Worker eventLoop = new Worker(1);

        // Task A
        workerThreads.post(new Runnable() {
            @Override
            public void run() {
                System.out.println("This is Task A");
            }
        });

        // Task B
        workerThreads.post(new Runnable() {
            @Override
            public void run() {
                System.out.println("This is task B, 1+1 = " + (1+1));
            }
        });

        // Task C
        eventLoop.post(new Runnable() {
            @Override
            public void run() {
                System.out.println("This is task C, 5 * 5 = " + (5*5));
            }
        });

        // Task D
        eventLoop.post(new Runnable() {
            @Override
            public void run() {
                System.out.println("This is task D");
            }
        });

        workerThreads.join();
        eventLoop.join();
    }
}
