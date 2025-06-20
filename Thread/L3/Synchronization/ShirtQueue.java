package L3.Synchronization;

import java.util.*;

public class ShirtQueue {
    private final Queue<Integer> queue;
    private final int capacity;
    private int currSize;

    public ShirtQueue(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
        this.currSize = 0;
    }

    public synchronized void add(int shirt) {
        currSize++;
        queue.add(shirt);
        if (currSize == capacity) {
            System.out.println("Queue is at its limit");
        }
    }

    public synchronized int remove() {
        currSize--;
        if (currSize == 0) {
            System.out.println("Queue is empty");
        }else if(currSize < 0){
            return 1;
        }
        return queue.remove();
    }

}
