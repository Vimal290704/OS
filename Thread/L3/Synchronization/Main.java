package L3.Synchronization;

public class Main {
    public static void main(String[] args) {
        ShirtQueue queue = new ShirtQueue(6);
        Consumer consumer = new Consumer(queue);
        Producer producer = new Producer(queue);
        consumer.start();
        producer.start();
    }
}
