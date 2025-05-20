// package Processes.Scheduling_Algorithm.Round_Robin;

// import java.util.*;

// public class Main {
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         String[] AT = sc.nextLine().split(" ");
//         int[] arrival = getArr(AT);
//         String[] BT = sc.nextLine().split(" ");
//         int[] burst = getArr(BT);
//         int quantum = sc.nextInt();
//         boolean[] taken = new boolean[burst.length];
//         int WT = 0, TAT = 0;
//         Queue<Process> queue = new LinkedList<>();
//         int currTime = arrival[getFirst(arrival, taken)];
//         while (true) {
//             if (queue.isEmpty()) {
//                 for (int i = 0; i < arrival.length; i++) {
//                     if (!taken[i] && currTime <= arrival[i]) {
//                         taken[i] = true;
//                         queue.add(new Process(arrival[i], burst[i]));
//                     }
//                 }
//             }
//             if (queue.isEmpty()) {
//                 int index = getFirst(arrival, taken);
//                 currTime = 
//             }
//             Process temp = queue.poll();
//             int rem = temp.remainingTime - quantum;
//             if (rem >= 0) {
//                 temp.remainingTime -= quantum;
//                 queue.add(temp);
//             } else {
//                 currTime += temp.remainingTime;
//             }
//         }

//     }

//     private static int getFirst(int[] AT, boolean[] taken) {
//         int minIndex = 0, minArrival = Integer.MAX_VALUE;
//         for (int i = 0; i < AT.length; i++) {
//             if (!taken[i]) {
//                 if (minArrival > AT[i]) {
//                     minIndex = i;
//                     minArrival = AT[i];
//                 }
//             }
//         }
//         return minIndex;
//     }

//     static class Process {
//         int arrivalTime;
//         int burstTime;
//         int remainingTime;

//         Process(int arrivalTime, int burstTime) {
//             this.arrivalTime = arrivalTime;
//             this.burstTime = burstTime;
//             this.remainingTime = burstTime;
//         }
//     }

//     private static int[] getArr(String[] arr) {
//         int temp[] = new int[arr.length];
//         for (int i = 0; i < arr.length; i++) {
//             temp[i] = Integer.parseInt(arr[i]);
//         }
//         return temp;
//     }
// }
