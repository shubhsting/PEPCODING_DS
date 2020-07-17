public class HeapSort {
    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13 };
        heapsort(arr);
        for (int ele : arr)
            System.out.print(ele + " ");
    }

    public static void downHeapify(int[] arr, int pi, int n) {
        int lftidx = 2 * pi + 1;
        int rtidx = 2 * pi + 2;
        int max = pi;
        if (lftidx < n && arr[lftidx] > arr[max])
            max = lftidx;
        if (lftidx < n && arr[rtidx] > arr[max])
            max = rtidx;
        if (pi != max) {
            swap(arr, pi, max);
            downHeapify(arr, max, n);
        }
    }

    public static void swap(int[] arr, int idx1, int idx2) {
        int ele1 = arr[idx1];
        int ele2 = arr[idx2];
        arr[idx1] = ele2;
        arr[idx2] = ele1;
    }

    public static void heapsort(int[] arr) {
        int n = arr.length - 1;
        for (int i = n; i >= 0; i--)
            downHeapify(arr, i, n);
        while (n != 0) {
            swap(arr, 0, n--);

            downHeapify(arr, 0, n);
        }
    }
    
}