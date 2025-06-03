package Scheduling_Algorithm.Non_Premptive;

import java.util.*;

public class SJF {
    static class Process {
        int index;
        int arrivalTime;
        int burstTime;

        Process(int index, int arrivalTime, int burstTime) {
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String[] AT = sc.nextLine().split(" ");
            String[] BT = sc.nextLine().split(" ");
            int[] arrival = getNumArr(AT);
            int[] burst = getNumArr(BT);
            int n = arrival.length;
            boolean[] processed = new boolean[n];
            boolean[] taken = new boolean[n];
            int currTime = 0, totalAroundTime = 0, totalWaitTime = 0;
            PriorityQueue<Process> queue = new PriorityQueue<>(new SortBySJF());
            while (true) {
                for (int i = 0; i < n; i++) {
                    if (!taken[i] && arrival[i] <= currTime) {
                        queue.add(new Process(i, arrival[i], burst[i]));
                        taken[i] = true;
                    }
                }
                if (queue.isEmpty()) {
                    int index = findnextProcess(arrival, processed);
                    if (index != -1) {
                        currTime = arrival[index];
                        queue.add(new Process(index, arrival[index], burst[index]));
                        taken[index] = true;
                    } else {
                        break;
                    }
                }
                if (!queue.isEmpty()) {
                    Process temp = queue.poll();
                    totalWaitTime += currTime - temp.arrivalTime;
                    currTime += temp.burstTime;
                    totalAroundTime += (currTime - temp.arrivalTime);
                    processed[temp.index] = true;
                }
                boolean allDone = true;
                for (int i = 0; i < n; i++) {
                    if (!processed[i]) {
                        allDone = false;
                        break;
                    }
                }
                if (allDone) {
                    break;
                }
            }
            System.out.println(totalAroundTime / n);
            System.out.println(totalWaitTime / n);
        }
    }

    private static int[] getNumArr(String[] arr) {
        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = Integer.parseInt(arr[i]);
        }
        return temp;
    }

    private static class SortBySJF implements Comparator<Process> {
        @Override
        public int compare(Process p1, Process p2) {
            if (p1.burstTime == p2.burstTime) {
                return Integer.compare(p1.arrivalTime, p2.arrivalTime);
            }
            return Integer.compare(p1.burstTime, p2.burstTime);
        }
    }

    private static int findnextProcess(int[] arrival, boolean[] processed) {
        int index = -1, minArrival = Integer.MAX_VALUE;
        for (int i = 0; i < arrival.length; i++) {
            if (!processed[i] && arrival[i] < minArrival) {
                index = i;
                minArrival = arrival[i];
            }
        }
        return index;
    }
}
