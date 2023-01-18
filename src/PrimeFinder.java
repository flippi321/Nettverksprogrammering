import java.util.ArrayList;
import java.util.List;

public class PrimeFinder {
    List<Integer> primes;
    List<MathThread> threads;
    MathThread currentThread;

    public List<Integer> findPrimesBetween(int number1, int number2, int numOfThreads){
        primes = new ArrayList<>();
        threads = new ArrayList<>();

        // Make sure the second number is larger than the first
        if(number1 >= number2 + 1) {
            throw new IllegalArgumentException("The first number must be lower than the second");
        }

        // Add the number of Threads specified
        for(int i = 0; i < numOfThreads; i++){
            threads.add(new MathThread());
        }

        // If first number is divisible by 2, it's not prime, so we skip it
        if (number1%2 == 0){
            number1 += 1;
        }

        // Go through all odd numbers between the numbers
        for (int i = number1; i < number2; i+=2){
            do{
                System.out.println("Finding Free Thread...");
                currentThread = findFreeThread();
            } while(currentThread == null);

            System.out.println("Thread: " + currentThread.getId() + " is Calculating " + i);

            // Run the thread
            currentThread.start();
            if (currentThread.isPrime()){
                primes.add(currentThread.getNumber());
            }

            // Reset thread
            currentThread = null;
        }

        // Return our list of Primes
        return primes;
    }

    private MathThread findFreeThread(){
        for (MathThread thread : threads) {
            if(!thread.isAlive()){
                return thread;
            }
        }
        return null;
    }
}
