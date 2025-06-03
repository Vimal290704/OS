package L2;

public class Example2 implements Runnable {

    private int count;

    Example2(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println(count);
    }
}
