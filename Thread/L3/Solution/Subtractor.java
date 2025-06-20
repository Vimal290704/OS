package L3.Solution;

public class Subtractor implements Runnable {
    private final Mutex mutex;

    public Subtractor(Mutex mutex) {
        this.mutex = mutex;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            mutex.decrease();
        }
    }
}
