class MathThread extends Thread {
    private int number;
    boolean prime;
    boolean done = true;

    @Override
    public void run() {
        done = false;
        prime = checkPrime(number);
        done = true;
    }

    /**
     * Method to check if a given number is prime
     * We know the number is not divisible by 2, since all even numbers are removed before calculation
     * @param num the number we want to check for prime
     * @return true if the number is prime, false if the number is divisible by another number
     */
    public boolean checkPrime(int num){
        // The number 2 is divisible by 2, but still a prime number
        if(num == 2) {
            return true;
        }

        // If the number is either 0, 1 or divisible by 2, it's not prime
        if(num <= 1 | num % 2 == 0) {
            return false;
        }

        // If none of the above, we must go through lower numbers manually
        for (int i = 3; i < num; i++){
            if (num % i == 0){
                return false;
            }
        }
        //System.out.println("Number " + num + " is prime");
        return true;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isPrime() {
        return prime;
    }

    public boolean isDone() {
        return done;
    }
}