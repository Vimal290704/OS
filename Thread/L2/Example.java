package L2;

public class Example extends Thread {

    private int count;

    public Example(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println(count + " " + Thread.currentThread().getName());
    }

}
