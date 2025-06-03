package L2.Callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask[] futureTasks = new FutureTask[6];
        for (int i = 0; i <= 5; i++) {
            NumberPrinter np = new NumberPrinter(i);
            futureTasks[i] = new FutureTask(np);
            Thread t1 = new Thread(futureTasks[i]);
            t1.start();
        }
        for (int i = 0; i <= 5; i++) {
            System.out.println(futureTasks[i].get());
        }
    }
}
