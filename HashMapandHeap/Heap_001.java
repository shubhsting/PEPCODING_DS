import java.util.ArrayList;

public class Heap_001 {
    public static int height(int[] arr, int idx) {
        if (idx >= arr.length)
            return -1;
        return Math.max(height(arr, 2 * idx + 1), height(arr, 2 * idx + 2)) + 1;
    }

    public static int size(int[] arr, int idx) {
        if (idx >= arr.length)
            return 0;
        return (size(arr, 2 * idx + 1) + size(arr, 2 * idx + 2)) + 1;
    }

    public static class Heap {
        ArrayList<Integer> arr = new ArrayList<>();
        boolean isMaxHeap = true;

        public Heap(int[] list) {
            for (int ele : list)
                this.arr.add(ele);
            int n = this.arr.size();
            for (int i = n - 1; i >= 0; i--)
                downHeapify(i, n);
        }

        public void swap(int idx1, int idx2) {
            int ele1 = this.arr.get(idx1);
            int ele2 = this.arr.get(idx2);
            this.arr.set(idx1, ele2);
            this.arr.set(idx2, ele1);
        }

        public void downHeapify(int pi, int n) {
            int lftidx = 2 * pi + 1;
            int rtidx = 2 * pi + 2;
            int max = pi;
            // if (lftidx < n && this.arr.get(lftidx) > this.arr.get(max))
            if (lftidx < n && compareTo(lftidx, max) > 0)
                max = lftidx;
            if (rtidx < n && compareTo(rtidx, max) > 0)
                max = rtidx;
            if (pi != max) {
                swap(pi, max);
                downHeapify(max, n);
            }
        }

        public int compareTo(int x, int y) {
            if (isMaxHeap)
                return arr.get(x) - arr.get(y);
            return arr.get(y) - arr.get(x);
        }

        public void upheapify(int ci) {
            int pi = (ci - 1) / 2;
            int min = ci;
            if (pi >= 0 && compareTo(min, pi) > 0)
                min = pi;
            if (min != ci) {
                swap(min, pi);
                upheapify(min);
            }
        }

        // ========================user functions=================
        public boolean isEmpty() {
            return this.arr.size() == 0;
        }

        public int size() {
            return this.arr.size();
        }

        public int top() {
            if (arr.size() == 0)
                return -1;
            return arr.get(0);
        }

        public int pop() {
            if (arr.size() == 0)
                return -1;
            int temp = arr.get(0);
            swap(0, arr.size() - 1);
            arr.remove(arr.size() - 1);
            downHeapify(0, arr.size());
            return temp;
        }

        public void add(int val) {
            arr.add(val);
            upheapify(arr.size() - 1);
        }
    }

    public static void main(String[] args) {

        int[] arr2 = { 100, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13 };
        Heap hp = new Heap(arr2);
        while (hp.size() != 0)
            System.out.print(hp.pop() + " ");
    }
}