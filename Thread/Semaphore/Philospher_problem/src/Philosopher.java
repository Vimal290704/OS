package Semaphore.Philospher_problem.src;

import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {

    private final Chopstick leftChopStick;

    private final Chopstick rightChopStick;

    private final EatingCount count;

    private final int numberOfPeople;

    private final Semaphore[] ChopstickSemaphore;

    public Philosopher(String name, Chopstick leftChopStick, Chopstick rightChopStick, EatingCount count, int numberOfPeople, Semaphore[] ChopstickSemaphore) {
        super(name);
        this.leftChopStick = leftChopStick;
        this.rightChopStick = rightChopStick;
        this.count = count;
        this.numberOfPeople = numberOfPeople;
        this.ChopstickSemaphore = ChopstickSemaphore;
    }

    @Override
    public void run() {
        while (count.getValue() < numberOfPeople) {
            try {
                think(); // Philosopher is thinking currently

                count.increment();

                this.ChopstickSemaphore[leftChopStick.getId()].acquire(); // acquiring the left chopstick

                this.ChopstickSemaphore[rightChopStick.getId()].acquire(); // acquiring the right chopstick

                eat(); // Philosopher is eating currently

                this.ChopstickSemaphore[leftChopStick.getId()].release();

                this.ChopstickSemaphore[rightChopStick.getId()].release();

                count.decrement();

            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " got interrupted");
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    private void think() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is thinking currently");
        Thread.sleep((long) (Math.random() * 1000));
        System.out.println(Thread.currentThread().getName() + " has now completed his thinking");
    }

    private void eat() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is now eating...");
        Thread.sleep((long) (Math.random() * 1000));
        System.out.println(Thread.currentThread().getName() + " has now completed his eating...");
    }
}
