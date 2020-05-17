import java.util.*;

class monkandislands {
    public static void main(String args[]) throws Exception {

        Scanner scn = new Scanner(System.in);
        int tc = scn.nextInt();
        StringBuilder sb = new StringBuilder();
        while (tc-- > 0) {
            int n = scn.hasNext() ? scn.nextInt() : 0;
            int m = scn.hasNext() ? scn.nextInt() : 0;
            boolean[] vis = new boolean[n + 1];
            ArrayList<Integer>[] graph = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++) {
                graph[i] = new ArrayList<Integer>();
            }
            while (m-- > 0) {
                int p = scn.hasNext() ? scn.nextInt() : 0;
                int q = scn.hasNext() ? scn.nextInt() : 0;
                graph[p].add(q);
                graph[q].add(p);
            }

            System.out.println(bfs(graph, 1, vis));
        }

    }

    public static int bfs(ArrayList<Integer>[] gp, int src, boolean[] vis) {
        int dest = gp.length - 1;
        int level = 0;
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        while (que.size() != 0) {
            int siz = que.size();
            while (siz-- > 0) {
                int idx = que.removeFirst();
                if (vis[idx])
                    continue;

                if (idx == dest)
                    return level;
                vis[idx] = true;
                for (Integer e : gp[idx]) {
                    if (!vis[e])
                        que.addLast(e);
                }
            }
            level++;
        }
        return 0;
    }
}