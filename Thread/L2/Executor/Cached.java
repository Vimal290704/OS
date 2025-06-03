package L2.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Cached {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        long curr = System.currentTimeMillis();
        for (int i = 0; i <= 100; i++) {
            executorService.execute(new NumberPrinter(i));
        }
        executorService.shutdown();
        System.out.println("Time taken: " + (System.currentTimeMillis() - curr)); // took 6 seconds
    }
}
