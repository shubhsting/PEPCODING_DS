import java.util.PriorityQueue;

import java.util.*;

public class HeapDefault {
    public static void main(String[] args) {
        int[] arr = { 2, 3, 11, 5, -1, 1, 7, 23, 6, 2, 237 };
        // PriorityQueue<Integer> pq = new PriorityQueue<>(); // min pq
        // PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        // // max pq
        boolean isMax = false;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            if (!isMax)
                return a - b; // min pq
            else
                return b - a; // max pq
        });
        for (int ele : arr) // nlogn
            pq.add(ele);

        while (pq.size() != 0) {
            System.out.print(pq.remove() + " ");
        }
    }
}