package Semaphore.Philospher_problem.src;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Table table = new Table(5);
        table.start();
    }
}
