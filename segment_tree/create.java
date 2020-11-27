import java.util.*;

public class create {
    public static void main(String[] args) {
        int[] arr = { -1, 2, 4, 0 };
        int[] sTree = new int[7];
        createSegmentTree(arr, sTree, 0, 3, 0);

        System.out.println(minrange(sTree, 0, 2, 0, 3, 0));

        update(sTree, 0, 0, 0, 3, 0, 4);

        System.out.println(minrange(sTree, 0, 2, 0, 3, 0));
        // create segment tree to find min of ranges
        // createSegmentTree();
    }

    public static void createSegmentTree(int[] arr, int[] sTree, int low, int hi, int sTreecurr) {
        if (low == hi) {
            sTree[sTreecurr] = arr[low];
            return;
        }
        int mid = low + (hi - low) / 2;
        createSegmentTree(arr, sTree, low, mid, 2 * sTreecurr + 1);
        createSegmentTree(arr, sTree, mid + 1, hi, 2 * sTreecurr + 2);
        sTree[sTreecurr] = Math.min(sTree[2 * sTreecurr + 1], sTree[2 * sTreecurr + 2]);
    }

    public static int minrange(int[] sTree, int qlow, int qhigh, int arrlow, int arrhigh, int sTreecurr) {
        // total overlap
        if (qlow <= arrlow && qhigh >= arrhigh)
            return sTree[sTreecurr];

        // no overlap
        if (qlow > arrhigh || qhigh < arrlow)
            return Integer.MAX_VALUE;// varies according to query

        int mid = arrlow + (arrhigh - arrlow) / 2;
        int a = minrange(sTree, qlow, qhigh, arrlow, mid, 2 * sTreecurr + 1);
        int b = minrange(sTree, qlow, qhigh, mid + 1, arrhigh, 2 * sTreecurr + 2);
        return Math.min(a, b);
    }

    public static void update(int[] sTree, int qlow, int qhigh, int arrlow, int arrhigh, int sTreecurr, int change) {
        // total overlap
        if (qlow <= arrlow && qhigh >= arrhigh) {
            sTree[sTreecurr] += change;
            return;
        }
        // no overlap
        if (qlow > arrhigh || qhigh < arrlow)
            return;// varies according to query

        int mid = arrlow + (arrhigh - arrlow) / 2;
        update(sTree, qlow, qhigh, arrlow, mid, 2 * sTreecurr + 1, change);
        update(sTree, qlow, qhigh, mid + 1, arrhigh, 2 * sTreecurr + 2, change);
        sTree[sTreecurr] = Math.min(sTree[2 * sTreecurr + 1], sTree[2 * sTreecurr + 2]);
    }

}