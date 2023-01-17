package Task_1;

class MathThread extends Thread {
    /**
     * Method to check if a given number is prime
     * We know the number is not divisible by 2, since all even numbers are removed before calculation
     * @param num the number we want to check for prime
     * @return true if the number is prime, false if the number is divisible by another number
     */
    public boolean checkPrime(int num){
        for (int i = 3; i < num; i++){
            if (num%i == 0){
                return false;
            }
        }
        return true;
    }
}