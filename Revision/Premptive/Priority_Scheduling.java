package Revision.Premptive;

import java.util.*;

public class Priority_Scheduling {
    static class Process {
        int arrivalTime;
        int burstTime;
        int priority;
        int remainingTime;
        int index;

        Process(int arrivalTime, int burstTime, int priority, int index) {
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
            this.priority = priority;
            this.index = index;
            this.remainingTime = burstTime;
        }

        @Override
        public String toString() {
            return "Process{" +
                    "arrivalTime = " + arrivalTime +
                    ", burstTime = " + burstTime +
                    ", Priority = " + priority +
                    ", remainingTime = " + remainingTime +
                    ", index= " + (index + 1) +
                    '}';
        }
    }

    private static class SortByPriority implements Comparator<Process> {
        public int compare(Process p1, Process p2) {
            if (p1.priority == p2.priority) {
                return Integer.compare(p1.arrivalTime, p2.arrivalTime);
            }
            return Integer.compare(p2.priority, p1.priority);
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int[] arrival = getNumArr(sc.nextLine().split(" "));
            int[] burst = getNumArr(sc.nextLine().split(" "));
            int[] priority = getNumArr(sc.nextLine().split(" "));
            int n = arrival.length;

            boolean[] processed = new boolean[n];
            boolean[] taken = new boolean[n];

            int currTime = arrival[findNextProcess(arrival, taken)];
            int totalAroundTime = 0, totalWaitTime = 0;

            PriorityQueue<Process> queue = new PriorityQueue<>(new SortByPriority());

            while (true) {
                for (int i = 0; i < n; i++) {
                    if (!taken[i] && arrival[i] <= currTime) {
                        queue.add(new Process(arrival[i], burst[i], priority[i], i));
                        taken[i] = true;
                    }
                }

                if (queue.isEmpty()) {
                    int minIndex = findNextProcess(arrival, taken);
                    if (minIndex != -1) {
                        currTime = arrival[minIndex];
                    }
                    continue;
                }

                System.out.println(currTime);
                System.out.println("Before execution");
                getPriorityDetails(queue);

                Process temp = queue.poll();
                temp.remainingTime--;
                currTime++;

                if (temp.remainingTime == 0) {
                    totalWaitTime += currTime - temp.arrivalTime - temp.burstTime;
                    totalAroundTime += currTime - temp.arrivalTime;
                    processed[temp.index] = true;
                } else {
                    queue.add(temp);
                }

                System.out.println("After execution");
                getPriorityDetails(queue);
                System.out.println();

                boolean allDone = true;
                for (boolean p : processed) {
                    if (!p) {
                        allDone = false;
                        break;
                    }
                }

                if (allDone)
                    break;
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

    @SuppressWarnings("rawtypes")
    private static void getPriorityDetails(PriorityQueue<Process> queue) {
        Iterator temp = queue.iterator();
        while (temp.hasNext()) {
            System.out.println(temp.next());
        }
    }
}
