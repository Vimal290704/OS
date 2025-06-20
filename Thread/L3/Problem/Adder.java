package L3.Problem;

public class Adder extends Thread {

    public Count countB;

    public Adder(Count countB) {
        this.countB = countB;
    }

    public void run() {
        for (int i = 1; i <= 50000; i++) {
            //System.out.println(this.countB.value + " current value of count in Adder class");
            countB.increase();
        }
    }
}
