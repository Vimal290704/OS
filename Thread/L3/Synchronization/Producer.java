package L3.Synchronization;

public class Producer extends Thread {
    private final ShirtQueue queue;

    public Producer(ShirtQueue queue) {
        this.queue = queue;
    }

    public void run() {
        for (int i = 0; ; i++) {
            queue.add(i);
        }
    }
}
