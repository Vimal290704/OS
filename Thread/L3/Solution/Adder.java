package L3.Solution;

public class Adder implements  Runnable{
    private final Mutex mutex;

    public Adder(Mutex mutex){
        this.mutex = mutex;
    }
    @Override
    public void run() {
        for(int i = 0 ; i < 10000 ;i++){
            mutex.increase();
        }
    }
}
