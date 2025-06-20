package L3.Problem;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Count c = new Count(0);
        Adder add = new Adder(c);
        Subtractor sub = new Subtractor(c);
        add.start();
        sub.start();
        add.join();
        sub.join();
        System.out.println(c.value);
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
