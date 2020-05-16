
/////very very good question in dfs///////
import java.util.*;

class happyvertices {
    public static void main(String args[]) throws Exception {

        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        ArrayList<Integer>[] fgraph = new ArrayList[n + 1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Integer>();
            fgraph[i] = new ArrayList<Integer>();
        }
        int m = scn.nextInt();
        while (m > 0) {
            int a = scn.nextInt();
            int b = scn.nextInt();
            graph[a].add(b);
            graph[b].add(a);
            m--;
        }
        boolean[] vis = new boolean[n + 1];
        int count = 0;
        for (int i = 0; i < graph.length; i++) {
            if (!vis[i])
                dfs(graph, i, vis, fgraph);
        }
        for (int i = 1; i < fgraph.length; i++) {
            int temp = fgraph[i].size();
            for (Integer e : fgraph[i]) {
                if (fgraph[e].size() > temp)
                    count++;
            }
        }

        System.out.println(count);

    }

    public static void dfs(ArrayList<Integer>[] graph, int src, boolean[] vis, ArrayList<Integer>[] fgraph) {
        vis[src] = true;

        for (Integer e : graph[src]) {
            if (!vis[e]) {
                fgraph[src].add(e);
                dfs(graph, e, vis, fgraph);
            }
        }

    }

}
