package Revision.Non_Premptive;

import java.util.*;

public class Priority_Scheduling {
    static class Process {
        int arrivalTime;
        int burstTime;
        int priority;
        int index;

        Process(int arrivalTime, int burstTime, int priority, int index) {
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
            this.priority = priority;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] AT = sc.nextLine().split(" ");
        String[] BT = sc.nextLine().split(" ");
        String[] PT = sc.nextLine().split(" ");
        int[] arrival = getNumArr(AT);
        int[] burst = getNumArr(BT);
        int[] priority = getNumArr(PT);
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
        PriorityQueue<Process> queue = new PriorityQueue<>(new SortByPriority());
        while (true) {
            for (int i = 0; i < n; i++) {
                if (!taken[i] && arrival[i] < currTime) {
                    taken[i] = true;
                    queue.add(new Process(arrival[i], burst[i], priority[i], i));
                }
            }
            if (queue.isEmpty()) {
                int minIndex = findNextProcess(arrival, taken);
                if (minIndex != -1) {
                    taken[minIndex] = true;
                    queue.add(new Process(arrival[minIndex], burst[minIndex], priority[minIndex], minIndex));
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

    private static class SortByPriority implements Comparator<Process> {
        @Override
        public int compare(Process p1, Process p2) {
            if (p1.priority == p2.priority) {
                return Integer.compare(p1.arrivalTime, p2.arrivalTime);
            }
            return Integer.compare(p1.priority, p2.priority);
        }
    }
}
