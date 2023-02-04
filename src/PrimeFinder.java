import java.util.ArrayList;
import java.util.List;

public class PrimeFinder extends Thread {
    ArrayList<Integer> sequence;
    ArrayList<Integer> primes;
    ArrayList<MathThread> threads;
    MathThread currentThread;
    int start;
    int end;
    int numOfThreads;

    @Override
    public void run() {
        findPrimesBetween(start, end, numOfThreads);
    }

    private void findPrimesBetween(int number1, int number2, int numOfThreads){
        sequence = new ArrayList<>();
        primes = new ArrayList<>();
        threads = new ArrayList<>();

        // Make sure the second number is larger than the first
        if(number1 >= number2 + 1) {
            throw new IllegalArgumentException("The first number must be lower than the second");
        }

        // If first number is divisible by 2, it's not prime, so we skip it
        if (number1%2 == 0){
            number1 += 1;
        }

        // Add all numbers to the sequence list
        for (int i = number1; i < number2; i+=1){
            sequence.add(i);
        }

        // Add the number of Threads specified
        for(int i = 0; i < numOfThreads; i++){
            threads.add(new MathThread());
        }

        // Go through all odd numbers between the numbers
        while(!sequence.isEmpty()){
            do{
                currentThread = findFreeThread();
            } while(currentThread == null);

            // Run the thread
            currentThread.start();
            //System.out.println("Thread: " + currentThread.getId() + " is Calculating " + sequence.get(0));
            currentThread.setNumber(sequence.get(0));
            sequence.remove(0);
            currentThread.run();
            if (currentThread.isPrime()){
                primes.add(currentThread.getNumber());
            }

            // Reset thread
            currentThread = null;
        }
    }

    private MathThread findFreeThread(){
        for (MathThread thread : threads) {
            if(thread.isDone()){
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //Remove old thread
                threads.remove(thread);

                MathThread newThread = new MathThread();
                threads.add(newThread);
                return newThread;
            }
        }
        return null;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setNumOfThreads(int numOfThreads) {
        this.numOfThreads = numOfThreads;
    }

    public ArrayList<Integer> getPrimes() {
        return primes;
    }
}
