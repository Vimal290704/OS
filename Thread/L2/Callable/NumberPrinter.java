package L2.Callable;

import java.util.concurrent.Callable;

public class NumberPrinter implements Callable {
    int count;

    NumberPrinter(int count) {
        this.count = count;
    }

    @Override
    public Object call() throws Exception {
        return (Object) "Vimal";
    }

}
