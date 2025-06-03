package L2.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Single {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        long curr = System.currentTimeMillis();
        for (int i = 0; i <= 100; i++) {
            NumberPrinter np = new NumberPrinter(i);
            executorService.execute(np);
        }
        executorService.shutdown();
        System.out.println("Time taken: " + (System.currentTimeMillis() - curr)); // took 1 seconds
    }
}
