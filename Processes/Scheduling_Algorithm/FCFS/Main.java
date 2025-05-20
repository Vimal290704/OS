package Processes.Scheduling_Algorithm.FCFS;

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
        }
        int n = burst.length;
        PriorityQueue<Process> queue = new PriorityQueue<>(new SortByArrival());
        for (int i = 0; i < n; i++) {
            Process temp = new Process(arrival[i], burst[i]);
            queue.add(temp);
        }
        int WT = 0, TAT = 0, currTime = 0;
        while (!queue.isEmpty()) {
            Process temp = queue.poll();
            if (currTime < temp.arrivalTime) {
                currTime = temp.arrivalTime;
            }
            WT += currTime - temp.arrivalTime;
            currTime += temp.burstTime;
            TAT += currTime - temp.arrivalTime;
        }
        System.out.println(WT / n);
        System.out.println(TAT / n);
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

    private static class SortByArrival implements Comparator<Process> {
        @Override
        public int compare(Process p1, Process p2) {
            if (p1.arrivalTime == p2.arrivalTime) {
                return Integer.compare(p1.burstTime, p2.burstTime);
            }
            return Integer.compare(p1.arrivalTime, p2.arrivalTime);
        }
    }
}