
import java.util.*;

class matsgraphbook {
    public static void main(String args[]) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        boolean[] vis = new boolean[n];
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int p = 0; p < m; p++) {
            int po = scn.nextInt();
            int qo = scn.nextInt();
            graph[po].add(qo);
            graph[qo].add(po);

        }
        int node = scn.nextInt();
        for (int p = graph[node].size() - 1; p >= 0; p--) {
            int temp = graph[node].get(p);
            int idx = graph[temp].indexOf(node);
            graph[node].remove(p);
            graph[temp].remove(idx);
        }
        vis[node] = true;
        int count = 1;
        dfs(graph, vis, 0);

        for (int i = 0; i < graph.length; i++) {
            if (!vis[i]) {
                count = 2;
                break;
            }

        }
        if (count == 1) {
            System.out.println("Connected");
        } else {
            System.out.println("Not Connected");
        }

    }

    public static void dfs(ArrayList<Integer>[] gp, boolean[] vis, int src) {
        vis[src] = true;
        for (Integer e : gp[src]) {
            if (!vis[e]) {
                dfs(gp, vis, e);
            }
        }
    }
}
