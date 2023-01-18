public class Application {
    public static void Main(String[] args){
        PrimeFinder finder = new PrimeFinder();
        System.out.println("List:");
        for (Integer num: finder.findPrimesBetween(0, 15, 2)) {
            System.out.println(num);
        }
    }
}
