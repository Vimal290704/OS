package Semaphore.Philospher_problem.src;

import java.util.concurrent.Semaphore;

public class Table extends Thread {

    private final int numberOfPeople;

    private final Semaphore[] ChopstickSemaphore;

    private final Chopstick[] chopsticks;

    private final Philosopher[] philosopher;

    private final EatingCount eatingCount;

    public Table(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
        this.ChopstickSemaphore = new Semaphore[numberOfPeople];
        this.chopsticks = new Chopstick[numberOfPeople];
        this.philosopher = new Philosopher[numberOfPeople];
        this.eatingCount = new EatingCount();
        initializeChopsticks();
        initializePhilosophers();
    }

    private void initializePhilosophers() {
        for (int i = 0; i < numberOfPeople; i++) {
            Chopstick left = chopsticks[(i - 1 + numberOfPeople) % numberOfPeople];
            Chopstick right = chopsticks[(i + 1 + numberOfPeople) % numberOfPeople];
            philosopher[i] = new Philosopher("Philosopher " + i, left, right, eatingCount, numberOfPeople, ChopstickSemaphore);
        }
    }

    private void initializeChopsticks() {
        for (int i = 0; i < numberOfPeople; i++) {
            chopsticks[i] = new Chopstick(i);
            ChopstickSemaphore[i] = new Semaphore(1);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfPeople; i++) {
            philosopher[i].start();
        }
        try {
            Thread.sleep(2000);
            for (int i = 0; i < numberOfPeople; i++) {
                philosopher[i].interrupt();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

}
