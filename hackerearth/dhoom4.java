import java.util.*;

class dhoom4 {
    public static void main(String args[]) throws Exception {
        Scanner scn = new Scanner(System.in);
        int src = scn.nextInt();
        int tar = scn.nextInt();
        int n = scn.nextInt();
        int[] arr = new int[n];
        boolean[] vis = new boolean[100000 + 1];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        System.out.println(bfs(src, tar, arr, vis));
    }

    public static int bfs(int src, int tar, int[] arr, boolean[] vis) {
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        vis[src] = true;
        int level = 0;
        while (que.size() != 0) {
            int si = que.size();
            while (si-- > 0) {
                int front = que.removeFirst();
                for (int i = 0; i < arr.length; i++) {
                    long temp = (long) ((long) front * (long) arr[i]);
                    long ip = temp % 100000;
                    int op = (int) ip;
                    if (op == tar)
                        return level + 1;
                    if (!vis[op]) {
                        que.addLast(op);
                        vis[op] = true;
                    }
                }
            }
            level++;
        }
        return -1;
    }
}