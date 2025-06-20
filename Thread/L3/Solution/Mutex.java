package L3.Solution;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Getter
@Setter

public class Mutex {

    private int value;
    private Lock lock;

    void increase() {
        System.out.println(Thread.currentThread().getName() + " is locked");
        lock.lock();
        try {
            value += 1;
        } finally {
            lock.unlock();
        }
    }

     void decrease() {
        System.out.println(Thread.currentThread().getName() + " is locked");
        lock.lock();
        try {
            value += 1;
        } finally {
            lock.unlock();
        }
    }

    public Mutex(int value) {
        this.value = 0;
        this.lock = new ReentrantLock();
    }

}
