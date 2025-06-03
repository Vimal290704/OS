package L2.Executor;

public class NumberPrinter implements Runnable {

    int count;

    public NumberPrinter(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println(count);
    }
}
