package L2;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        /*
        for (int i = 1; i <= 100; i++) {
            Example t1 = new Example(i);
            t1.start();
            t1.join();
        }
        */

        Thread t1;
        for(int i = 1 ; i <= 100 ; i++){
            Example2 ex = new Example2(i);
            t1 = new Thread(ex);
            t1.start();
            t1.join();
        }
    }
}
