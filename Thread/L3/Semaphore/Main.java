package L3.Semaphore;

import lombok.*;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        List<MyThread> list = new ArrayList<>();
        for (int i = 0; i < 0 ; i++) {
            list.add(new MyThread("Thread " + i, counter));
            list.get(i).start();
        }
    }
}

class MyThread extends Thread {


    private final Counter counter;

    public MyThread(String name, Counter counter) {
        super(name);
        this.counter = counter;
    }

    @Override
    public void run() {
        try {
            counter.increment();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

@Getter
@Setter
class Counter {

    private static Semaphore semaphore = new Semaphore(Integer.MAX_VALUE, true);
    private int count;

    public Counter() {
        this.count = 0;
    }

    public void increment() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is trying to access the Critical section....");
        if (semaphore.tryAcquire()) {
            Thread.sleep(1000);
            synchronized (this) {
                increase();
            }
            semaphore.release();
            System.out.println(count);
        } else {
            System.out.println("Critical section is not empty to be acquired");
        }
    }

    private void increase() {
        this.count++;
    }
}