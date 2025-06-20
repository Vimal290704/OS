package L3.Synchronization;

public class Consumer extends Thread {
    private final ShirtQueue queue;

    public Consumer(ShirtQueue queue) {
        this.queue = queue;
    }

    public void run() {
        for (int i = 0; ; i++) {
            queue.remove();
        }
    }
}
