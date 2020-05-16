
import java.util.*;

class comrades1 {
    public static void main(String args[]) throws Exception {
        Scanner scn = new Scanner(System.in);
        int tc = scn.nextInt();
        while (tc-- > 0) {
            int n = scn.nextInt();
            ArrayList<Integer>[] graph = new ArrayList[n + 1];
            for (int m = 1; m < n + 1; m++) {
                graph[m] = new ArrayList<Integer>();
            }
            boolean[] vis = new boolean[n + 1];
            int[] arr = new int[n + 1];
            for (int i = 1; i < n + 1; i++) {
                arr[i] = scn.nextInt();
                if (arr[i] != 0) {
                    graph[i].add(arr[i]);
                }
            }
            int m = scn.nextInt();
            while (m-- > 0) {
                int p = scn.nextInt();
                int q = scn.nextInt();
                tp = false;
                Arrays.fill(vis, false);
                dfs(graph, p, q, 0, vis);
                if (tp)
                    System.out.println(fc);
                else {
                    System.out.println("-1");
                }
            }

        }

    }

    static int fc = 0;
    static boolean tp = false;

    public static void dfs(ArrayList<Integer>[] gp, int curr, int end, int count, boolean[] vis) {
        if (curr == end) {
            fc = count;
            tp = true;
            return;
        }
        vis[curr] = true;
        for (Integer e : gp[curr])
            if (!vis[e]) {
                if (e != end)
                    dfs(gp, e, end, count + 1, vis);
                else
                    dfs(gp, e, end, count, vis);
            }
    }
}
