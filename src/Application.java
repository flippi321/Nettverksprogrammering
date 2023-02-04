import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Application {
    public static void main(String[] args){
        PrimeFinder finder = new PrimeFinder();
        int start = 0;
        int end = 25000;
        int threads = 3;

        //Generate list of Prime Numbers
        finder.setStart(start);
        finder.setEnd(end);
        finder.setNumOfThreads(threads);
        finder.run();

        // Wait for PrimeFinder to finish
        while (finder.isAlive()) {}

        ArrayList<Integer> primeNumbers = finder.getPrimes();

        //Sort List
        Collections.sort(primeNumbers);

        //Cool Headline
        System.out.println("\n\n\n\n\n\n\n\n\n");
        System.out.print("  ______       _        _____                 _ _   \n" +
                " |  ____|     (_)      |  __ \\               | | |  \n" +
                " | |__   _ __  _  ___  | |__) |___  ___ _   _| | |_ \n" +
                " |  __| | '_ \\| |/ __| |  _  // _ \\/ __| | | | | __|\n" +
                " | |____| |_) | | (__  | | \\ \\  __/\\__ \\ |_| | | |_ \n" +
                " |______| .__/|_|\\___| |_|  \\_\\___||___/\\__,_|_|\\__|\n" +
                "        | |                                         \n" +
                "        |_|");

        //Return List of
        System.out.println("\n\nAll prime numbers between " + start + " and " + end +":");
        for (Integer num : primeNumbers) {
            System.out.println(num);
        }
    }
}
