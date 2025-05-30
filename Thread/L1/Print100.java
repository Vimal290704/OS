package L1;

import java.util.*;

public class Print100 {
    public static void main(String[] args) {
        ArrayList<Print> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add(new Print(i));
        }
        Collections.sort(list, new Sort());
    }

    static class Print implements Runnable {
        int count;
        long threadId;

        Print(int count) {
            this.count = count;
            this.threadId = Thread.currentThread().getId();
        }

        @Override
        public void run() {
            System.out.println(this.count + " " + this.threadId);
        }
    }

    static class Sort implements Comparator<Print> {
        public int compare(Print t1, Print t2) {
            return t1.count - t2.count;
        }
    }
}
