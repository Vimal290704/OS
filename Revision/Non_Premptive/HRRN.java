package Revision.Non_Premptive;

import java.util.*;

public class HRRN {
    static class Process {
        int index;
        int arrivalTime;
        int burstTime;

        Process(int arrivalTime, int burstTime, int index) {
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
            this.index = index;
        }

        public double getResponseRatio(int currentTime) {
            return (double) (1) + (double) ((currentTime - this.arrivalTime) / (this.burstTime));
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
            boolean taken[] = new boolean[n];
            int index = findFirstProcess(arrival);
            int currTime = arrival[index];
            int totalAroundTime = 0, totalWaitTime = 0;
            currTime += burst[index];
            processed[index] = true;
            taken[index] = true;
            totalAroundTime += currTime - arrival[index];
            PriorityQueue<Process> queue = new PriorityQueue<>(new SortByResponseRatio(currTime));
            while (true) {
                for (int i = 0; i < n; i++) {
                    if (!taken[i] && arrival[i] <= currTime) {
                        queue.add(new Process(arrival[i], burst[i], i));
                        taken[i] = true;
                    }
                }
                if (queue.isEmpty()) {
                    int minIndex = findNextProcess(arrival, processed);
                    if (index != -1) {
                        currTime = arrival[minIndex];
                        queue.add(new Process(arrival[index], burst[index], minIndex));
                        taken[index] = true;
                    } else {
                        break;
                    }
                }
                if (!queue.isEmpty()) {
                    Process temp = queue.poll();
                    totalWaitTime += currTime - temp.arrivalTime;
                    currTime += temp.burstTime;
                    totalAroundTime += currTime - temp.arrivalTime;
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

    private static int findFirstProcess(int[] arrival) {
        int index = 0, minArrival = arrival[0];
        for (int i = 1; i < arrival.length; i++) {
            if (minArrival > arrival[i]) {
                index = i;
                minArrival = arrival[i];
            }
        }
        return index;
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

    private static class SortByResponseRatio implements Comparator<Process> {
        int currentTime;

        public SortByResponseRatio(int currentTime) {
            this.currentTime = currentTime;
        }

        @Override
        public int compare(Process p1, Process p2) {
            double rr1 = p1.getResponseRatio(currentTime);
            double rr2 = p2.getResponseRatio(currentTime);
            if (rr1 == rr2) {
                return Integer.compare(p1.arrivalTime, p2.arrivalTime);
            }
            return Double.compare(rr2, rr1);
        }
    }
}
