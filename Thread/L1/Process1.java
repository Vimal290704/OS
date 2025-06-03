package L1;

public class Process1 implements Runnable {

    @Override
    public void run() {
        System.out.println("Process1 started , Thread: " + Thread.currentThread().getId());
    }

    public void dosomething() {
        System.out.println("Dosomething function executed, Thread: " + Thread.currentThread().getId());
    }

}
