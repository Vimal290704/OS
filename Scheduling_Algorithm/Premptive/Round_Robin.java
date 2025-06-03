package Scheduling_Algorithm.Premptive;

import java.util.*;

public class Round_Robin {
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

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String[] AT = sc.nextLine().split(" ");
            String[] BT = sc.nextLine().split(" ");
            int quantumTime = sc.nextInt();
            int[] arrival = getNumArr(AT);
            int[] burst = getNumArr(BT);
            int n = arrival.length;
            boolean[] processed = new boolean[n];
            boolean[] taken = new boolean[n];
            int currTime = arrival[findNextProcess(arrival, taken)], totalAroundTime = 0, totalWaitTime = 0;
            Queue<Process> queue = new LinkedList<>();
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
                        continue;
                    }
                }
                Process temp = queue.poll();
                currTime += Math.min(temp.remainingTime, quantumTime);
                int rem = Math.max(temp.remainingTime - quantumTime, 0);
                temp.remainingTime = rem;
                for (int i = 0; i < n; i++) {
                    if (!taken[i] && arrival[i] < currTime) {
                        queue.add(new Process(arrival[i], burst[i], i));
                        taken[i] = true;
                    }
                }
                if (rem > 0) {
                    queue.add(temp);
                } else {
                    totalWaitTime += currTime - temp.arrivalTime - temp.burstTime;
                    totalAroundTime += currTime - temp.arrivalTime;
                    processed[temp.index] = true;
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
