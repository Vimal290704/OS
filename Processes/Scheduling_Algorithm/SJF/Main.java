package Processes.Scheduling_Algorithm.SJF;

import java.lang.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String AT[] = sc.nextLine().split(" ");
        int[] arrival = getArr(AT);
        String BT[] = sc.nextLine().split(" ");
        int burst[] = getArr(BT);
        if (arrival.length != burst.length) {
            System.out.println("Invalid");
            sc.close();
            return;
        }
        int n = burst.length;
        // minIndex from which the process started
        int minIndex = findEarliestArrival(arrival);
        PriorityQueue<Process> queue = new PriorityQueue<>(new SortByBurst());
        // execute first Process
        int currTime = arrival[minIndex];
        int totalWT = 0;
        int totalTAT = 0;
        currTime += burst[minIndex];
        totalTAT += currTime - arrival[minIndex];
        // create boolean array to track the processed processes
        boolean[] processed = new boolean[n];
        processed[minIndex] = true; // minIndex process is already processed
        // if any process is left
        while (true) {
            for (int i = 0; i < n; i++) {
                // if a process is not processed and it has arrived
                if (!processed[i] && arrival[i] <= currTime) {
                    // add it into queue to be processed
                    queue.add(new Process(arrival[i], burst[i]));
                    processed[i] = true;
                }
            }
            // if there is no process by the currTime
            if (queue.isEmpty()) {
                // find the remaining processes that are not processed and have minimum
                // arrivalTime
                int nextProcess = findNextArrival(arrival, processed);
                if (nextProcess == -1)
                    break;
                currTime = arrival[nextProcess];
                queue.add(new Process(arrival[nextProcess], burst[nextProcess]));
                processed[nextProcess] = true;
            }
            Process temp = queue.poll();
            totalWT += currTime - temp.arrivalTime;
            currTime += temp.burstTime;
            totalTAT += currTime - temp.arrivalTime;
            boolean allDone = true;
            // check if all processes are done
            for (boolean p : processed) {
                if (!p) {
                    allDone = false;
                    break;
                }
            }
            if (allDone && queue.isEmpty())
                break;
        }

        System.out.println(totalWT / n);
        System.out.println(totalTAT / n);
        sc.close();
    }

    private static int[] getArr(String[] arr) {
        int ans[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = Integer.parseInt(arr[i]);
        }
        return ans;
    }

    private static class Process {
        public int arrivalTime;
        public int burstTime;

        Process(int arrivalTime, int burstTime) {
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
        }
    }

    private static class SortByBurst implements Comparator<Process> {
        @Override
        public int compare(Process p1, Process p2) {
            if (p1.burstTime == p2.burstTime) {
                return Integer.compare(p1.arrivalTime, p2.arrivalTime);
            }
            return Integer.compare(p1.burstTime, p2.burstTime);
        }
    }

    private static int findEarliestArrival(int[] arrival) {
        int minIndex = 0;
        int minArrival = arrival[0];

        for (int i = 1; i < arrival.length; i++) {
            if (arrival[i] < minArrival) {
                minArrival = arrival[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private static int findNextArrival(int[] arrival, boolean[] processed) {
        int nextIndex = -1;
        int nextArrival = Integer.MAX_VALUE;

        for (int i = 0; i < arrival.length; i++) {
            if (!processed[i] && arrival[i] < nextArrival) {
                nextArrival = arrival[i];
                nextIndex = i;
            }
        }
        return nextIndex;
    }
}