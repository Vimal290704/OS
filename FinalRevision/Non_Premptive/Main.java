import java.lang.*;
import java.util.*;

public class Main {
    static class Process {
        int arrivalTime;
        int burstTime;
        int index;

        Process(int arrivalTime, int burstTime, int index) {
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
            this.index = index;
        }
    }

    static class SortBySJF implements Comparator<Process> {
        public int compare(Process p1, Process p2) {
            if (p1.burstTime == p2.burstTime) {
                return Integer.compare(p1.arrivalTime, p2.arrivalTime);
            }
            return Integer.compare(p1.burstTime, p2.burstTime);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] AT = sc.nextLine().split(" ");
        String[] BT = sc.nextLine().split(" ");
        int[] arrival = getNum(AT);
        int[] burst = getNum(BT);
        int currTime = 0, WT = 0, TAT = 0;
        int n = arrival.length;
        boolean[] taken = new boolean[n];
        boolean[] processed = new boolean[n];
        PriorityQueue<Process> queue = new PriorityQueue<>(new SortBySJF());
        while (true) {
            for (int i = 0; i < n; i++) {
                if (!taken[i] && arrival[i] <= currTime) {
                    queue.add(new Process(arrival[i], burst[i], i));
                    taken[i] = true;
                }
            }
            if (queue.isEmpty()) {
                int index = findNextProcess(arrival, taken);
                if (index != -1) {
                    currTime = arrival[index];
                    queue.add(new Process(arrival[index], burst[index], index));
                    taken[index] = true;
                }
            }
            Process temp = queue.poll();
            WT += currTime - temp.arrivalTime;
            currTime += temp.burstTime;
            TAT += currTime - temp.arrivalTime;
            boolean allDone = true;
            for (boolean p : processed) {
                if (!p) {
                    allDone = false;
                    break;
                }
            }
            if (allDone) {
                break;
            }
        }
        System.out.println(WT / n);
        System.out.println(TAT / n);
    }

    private static int[] getNum(String[] arr) {
        int temp[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = Integer.parseInt(arr[i]);
        }
        return temp;
    }

    private static int findNextProcess(int[] arrival, boolean[] taken) {
        int index = -1, minArrival = Integer.MAX_VALUE;
        for (int i = 0; i < arrival.length; i++) {
            if (!taken[i] && minArrival > arrival[i]) {
                index = i;
                minArrival = arrival[i];
            }
        }
        return index;
    }
}