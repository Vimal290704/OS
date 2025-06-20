package L3.Problem;

public class Count {
    public int value;

    public Count(int val) {
        this.value = val;
    }

    public synchronized void increase(){
        this.value += 1;
    }
    public synchronized void decrease(){
        this.value -= 1;
    }
}
