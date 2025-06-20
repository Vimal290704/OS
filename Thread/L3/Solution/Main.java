package L3.Solution;

public class Main {
    public static void main(String[] args) {
        Mutex mutex = new Mutex(0);
        Adder add = new Adder(mutex);
        Subtractor subtractor = new Subtractor(mutex);
        Thread t1 = new Thread(add);
        Thread t2 = new Thread(subtractor);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        System.out.println("Both threads completed");
    }
}
