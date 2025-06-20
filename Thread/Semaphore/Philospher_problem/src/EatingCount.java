package Semaphore.Philospher_problem.src;

import lombok.*;

@Getter
@Setter

public class EatingCount {

    private int value;

    public EatingCount() {
        this.value = 0;
    }

    public void increment() {
        synchronized (this) {
            this.value++;
        }
    }

    public void decrement() {
        synchronized (this) {
            this.value--;
        }
    }
}
