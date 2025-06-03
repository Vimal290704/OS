package Scheduling_Algorithm.Non_Premptive;
// First Come First Serve
import java.util.*;

public class FCFS {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String[] AT = sc.nextLine().split(" ");
            String[] BT = sc.nextLine().split(" ");
            int n = AT.length;
            if (n != BT.length) {
                System.out.println("Invalid Input");
                return;
            }
            int[] arrival = getNumArr(AT);
            int[] burst = getNumArr(BT);
            PriorityQueue<Process> queue = new PriorityQueue<>(new SortByArrival());
            for (int i = 0; i < n; i++) {
                queue.add(new Process(arrival[i], burst[i]));
            }
            int TAT = 0, WT = 0, currTime = 0;
            while (!queue.isEmpty()) {
                Process temp = queue.poll();
                if (currTime < temp.arrivalTime) {
                    currTime = temp.arrivalTime;
                }
                WT += (currTime - temp.arrivalTime);
                currTime += temp.burstTime;
                TAT += (currTime - temp.arrivalTime);
            }
            System.out.println(WT / n);
            System.out.println(TAT / n);
        }
    }

    static class Process {
        int arrivalTime;
        int burstTime;

        Process(int arrivalTime, int burstTime) {
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
        }
    }

    static class SortByArrival implements Comparator<Process> {
        @Override
        public int compare(Process p1, Process p2) {
            return Integer.compare(p1.arrivalTime, p2.arrivalTime);
        }
    }

    private static int[] getNumArr(String[] arr) {
        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = Integer.parseInt(arr[i]);
        }
        return temp;
    }
}
