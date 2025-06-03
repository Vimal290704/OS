package Revision.Premptive;

import java.util.*;

public class SRTF {
    static class Process {
        int arrivalTime;
        int burstTime;
        int index;
        int remainingTime;

        Process(int arrivalTime, int burstTime, int index) {
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
            this.index = index;
            this.remainingTime = burstTime;
        }
    }

    private static class SortByRemainingTime implements Comparator<Process> {
        @Override
        public int compare(Process p1, Process p2) {
            if (p1.remainingTime == p2.remainingTime) {
                return Integer.compare(p1.arrivalTime, p2.arrivalTime);
            }
            return Integer.compare(p1.remainingTime, p2.remainingTime);
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String[] AT = sc.nextLine().split(" ");
            String[] BT = sc.nextLine().split(" ");
            int[] arrival = getNumArr(AT);
            int[] burst = getNumArr(BT);
            int n = arrival.length;
            boolean processed[] = new boolean[n];
            boolean[] taken = new boolean[n];
            int currTime = arrival[findNextProcess(arrival, taken)];
            PriorityQueue<Process> queue = new PriorityQueue<>(new SortByRemainingTime());
            int totalAroundTime = 0, totalWaitTime = 0;
            while (true) {
                for (int i = 0; i < n; i++) {
                    if (!taken[i] && arrival[i] <= currTime) {
                        queue.add(new Process(arrival[i], burst[i], i));
                        taken[i] = true;
                    }
                }
                if (queue.isEmpty()) {
                    int index = findNextProcess(arrival, processed);
                    if (index != -1) {
                        currTime = arrival[index];
                    }
                    continue;
                }
                Process temp = queue.poll();
                int remainingTime = temp.remainingTime;
                if (remainingTime <= 0) {
                    totalAroundTime += currTime - temp.arrivalTime;
                    totalWaitTime += currTime - temp.arrivalTime - temp.burstTime;
                    processed[temp.index] = true;
                } else {
                    currTime++;
                    remainingTime--;
                    temp.remainingTime = remainingTime;
                    if (remainingTime == 0) {
                        totalAroundTime += currTime - temp.arrivalTime;
                        totalWaitTime += currTime - temp.arrivalTime - temp.burstTime;
                        processed[temp.index] = true;
                    } else {
                        queue.add(temp);
                    }
                }
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
            System.out.println(totalWaitTime / n);
            System.out.println(totalAroundTime / n);
        }
    }

    private static int[] getNumArr(String[] arr) {
        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = Integer.parseInt(arr[i]);
        }
        return temp;
    }

    private static int findNextProcess(int[] arrival, boolean[] taken) {
        int index = -1, minArrival = Integer.MAX_VALUE;
        for (int i = 0; i < arrival.length; i++) {
            if (!taken[i] && arrival[i] < minArrival) {
                index = i;
                minArrival = arrival[i];
            }
        }
        return index;
    }
}
