package L2.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Fixed {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        long curr = System.currentTimeMillis();
        for(int i = 1 ; i <= 100 ; i++){
            NumberPrinter np = new NumberPrinter(i);
            executorService.execute(np);
        }
        executorService.shutdown();
        System.out.println("Time taken: " + (System.currentTimeMillis() - curr)); // took 9 seconds
    }
}
