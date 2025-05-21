package Processes.Scheduling_Algorithm.Round_Robin;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String[] AT = sc.nextLine().split(" ");
            String[] BT = sc.nextLine().split(" ");
            int quantumTime = sc.nextInt();
            int[] arrival = getNumArr(AT);
            int[] burst = getNumArr(BT);
            int totalWaitTime = 0, totalTurnAroundTime = 0;
            int n = arrival.length;
            if (n != burst.length) {
                System.out.println("Invalid Input");
                return;
            }
            boolean[] taken = new boolean[n];
            boolean[] processed = new boolean[n];
            int currTime = arrival[findnextProcess(arrival, taken)];
            Queue<Process> queue = new LinkedList<>();
            while (!isallProcessed(processed)) {
                for (int i = 0; i < n; i++) {
                    if (!taken[i] && arrival[i] <= currTime) {
                        taken[i] = true;
                        queue.add(new Process(arrival[i], burst[i], i));
                    }
                }
                if (queue.isEmpty()) {
                    int index = findnextProcess(arrival, taken);
                    if (index != -1) {
                        currTime = Math.max(currTime, arrival[index]);
                    }
                }
                Process temp = queue.poll();
                int executionTime = Math.min(quantumTime, temp.remainingTime);
                currTime += executionTime;
                temp.remainingTime -= executionTime;
                for (int i = 0; i < n; i++) {
                    if (!taken[i] && arrival[i] <= currTime) {
                        taken[i] = true;
                        queue.add(new Process(arrival[i], burst[i], i));
                    }
                }
                if (temp.remainingTime == 0) {
                    totalTurnAroundTime += (currTime - temp.arrivalTime);
                    totalWaitTime += (currTime - temp.arrivalTime - temp.burstTime);
                    processed[temp.index] = true;
                } else {
                    queue.add(temp);
                }
            }
            System.out.println(totalWaitTime / n);
            System.out.println(totalTurnAroundTime / n);
        }
    }

    static class Process {
        int arrivalTime;
        int burstTime;
        int remainingTime;
        int index;

        Process(int arrivalTime, int burstTime, int index) {
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
            this.index = index;
            this.remainingTime = burstTime;
        }
    }

    private static boolean isallProcessed(boolean[] arr) {
        for (boolean t : arr) {
            if (!t) {
                return false;
            }
        }
        return true;
    }

    private static int[] getNumArr(String[] str) {
        int[] temp = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            temp[i] = Integer.parseInt(str[i]);
        }
        return temp;
    }

    private static int findnextProcess(int[] arrival, boolean[] taken) {
        int n = arrival.length, minArrival = Integer.MAX_VALUE, index = -1;
        for (int i = 0; i < n; i++) {
            if (!taken[i]) {
                if (minArrival > arrival[i]) {
                    minArrival = arrival[i];
                    index = i;
                }
            }
        }
        return index;
    }
}