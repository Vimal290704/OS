package L1;

public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello from Vimal : " + Thread.currentThread().getName());
        Process1 p1 = new Process1();
        Thread t1 = new Thread(p1);
        t1.start();
        p1.dosomething();
        while (true) {
            break;
        }
        dos();
    }

    public static void dos() {
        System.out.println("Hello from parent, Thread: " + Thread.currentThread().getId());
    }
}
