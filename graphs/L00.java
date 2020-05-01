import java.util.ArrayList;
import java.util.LinkedList;

public class L00 {
    public static class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] gp, int u, int v, int w) {
        gp[u].add(new Edge(v, w));
        gp[v].add(new Edge(u, w));
    }

    public static int findEdge(ArrayList<Edge>[] gp, int u, int v) {
        int i = 0;
        for (i = 0; i < gp[u].size(); i++) {
            if (gp[u].get(i).v == v)
                return i;
        }
        return -1;
    }

    public static void removeEdge(ArrayList<Edge>[] gp, int u, int v) {
        int temp = findEdge(gp, u, v);
        int temp_ = findEdge(gp, v, u);
        gp[u].remove(temp);
        gp[v].remove(temp_);
    }

    public static void removeVtx(ArrayList<Edge>[] gp, int vtx) {
        for (int i = gp[vtx].size() - 1; i >= 0; i--) {
            int temp = gp[vtx].get(i).v;
            removeEdge(gp, temp, vtx);
        }
    }

    public static void display(ArrayList<Edge>[] gp) {
        for (int i = 0; i < gp.length; i++) {
            System.out.print(i + "----> ");
            for (Edge e : gp[i])
                System.out.print("(" + e.v + " " + e.w + ") ,");

            System.out.println();
        }
    }

    public static void constructGraph() {
        int n = 7;
        ArrayList<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 50);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 10);
        addEdge(graph, 3, 4, 10);
        addEdge(graph, 4, 5, 10);
        addEdge(graph, 4, 6, 10);
        addEdge(graph, 5, 6, 10);
        // addEdge(graph, 2, 5, 10);
        // removeEdge(graph, 3, 2);
        // removeVtx(graph, 4);
        // display(graph);
        boolean[] vis = new boolean[n];
        // System.out.print(allPath(0, 6, graph, vis,""));
        // hamiltonianPath(2, 2, graph, 0, vis, "");
        // System.out.println(nofCycles(graph, vis));
        // allPath(0,6, graph, vis, "");
        bfs(graph, 1, vis);

    }

    public static boolean hasPath(int src, int dest, ArrayList<Edge>[] gp, boolean[] vis) {
        if (src == dest) {
            return true;
        }
        vis[src] = true;
        boolean res = false;
        for (Edge e : gp[src])
            if (!vis[e.v])
                res = res || hasPath(e.v, dest, gp, vis);
        vis[src] = false;
        return res;
    }

    public static int allPath(int src, int dest, ArrayList<Edge>[] gp, boolean[] vis, String ans) {
        if (src == dest) {
            System.out.println(ans + dest + " ");
            return 1;
        }
        vis[src] = true;
        int count = 0;
        for (Edge e : gp[src])
            if (!vis[e.v])
                count = count + allPath(e.v, dest, gp, vis, ans + src + " ");
        vis[src] = false;
        return count;
    }

    // saari vertices ghum lo without repetion of any edge
    public static void hamiltonianPath(int src, int osrc, ArrayList<Edge>[] gp, int count, boolean[] vis, String ans) {
        if (count == gp.length - 1) {
            int t = findEdge(gp, src, osrc);
            if (t == -1) {
                System.out.println("Path" + ans + src);
            } else {
                System.out.println("Cycle" + ans + src);
            }
            return;
        }
        vis[src] = true;
        for (Edge e : gp[src]) {
            if (!vis[e.v])
                hamiltonianPath(e.v, osrc, gp, count + 1, vis, ans + src + " ");
        }
        vis[src] = false;
    }

    public static void dfs_GCC(ArrayList<Edge>[] gp, boolean[] vis, int src) {
        vis[src] = true;
        for (Edge e : gp[src]) {
            if (!vis[e.v])
                dfs_GCC(gp, vis, e.v);
        }
    }

    // this returns no of parts of a graph
    public static int nofCycles(ArrayList<Edge>[] gp, boolean[] vis) {
        int count = 0;
        for (int i = 0; i < gp.length; i++) {
            if (!vis[i]) {
                count++;
                dfs_GCC(gp, vis, i);
            }

        }
        return count;
    }

    public static class Pair {
        int vtx = 0;
        String ans = "";
        int level = 0;

        Pair(int vtx, String ans, int level) {
            this.vtx = vtx;
            this.ans = ans;
            this.level = level;
        }

        Pair(int vtx, String ans) {
            this.vtx = vtx;
            this.ans = ans;
        }

    }

    public static void bfs(ArrayList<Edge>[] gp, int src, boolean[] vis) {
        LinkedList<Pair> que = new LinkedList<>();
        int dest = 6;
        que.addLast(new Pair(src, src + " "));
        que.addLast(null);
        while (que.size() != 1) {
            if (que.getFirst() == null) {
                que.removeFirst();
                que.addLast(null);
            }
            Pair idxpair = que.removeFirst();
            if (vis[idxpair.vtx]) {
                System.out.println("Cycle: " + idxpair.vtx);
                continue;
            }
            if (idxpair.vtx == dest) {
                System.out.println("Dest: " + idxpair.ans);
            }
            vis[idxpair.vtx] = true;
            for (Edge e : gp[idxpair.vtx]) {
                if (!vis[e.v])
                    que.addLast(new Pair(e.v, idxpair.ans + e.v + " "));
            }

        }
    }

    public static void bfs_02(ArrayList<Edge>[] gp, int src, boolean[] vis) {
        LinkedList<Pair> que = new LinkedList<>();
        int dest = 6;
        que.addLast(new Pair(src, src + " ", 0));
        while (que.size() != 0) {
            Pair rvtx = que.removeFirst();
            if (vis[rvtx.vtx]) {
                System.out.println("Cycle: " + rvtx.ans);
                continue;
            }
            if (rvtx.vtx == dest) {
                System.out.println("Destination : " + rvtx.ans + " ---> " + rvtx.level);
            }
            vis[rvtx.vtx] = true;
            for (Edge e : gp[rvtx.vtx]) {
                if (!vis[e.v])
                    que.addLast(new Pair(e.v, rvtx.ans + e.v + " ", rvtx.level + 1));
            }
        }

    }

    public static void main(String[] args) {
        constructGraph();

    }
}