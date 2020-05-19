import java.util.*;
////kruskal prims dijestra
public class KPD {
    public static class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) {
        // int[][] arr = { { 0, 1, 10 }, { 1, 3, 15 }, { 0, 3, 5 }, { 0, 2, 6 }, { 2, 3,
        // 4 } };
        // kruskalAlgo(arr);
        constructGraph();
        primsAlgo(3);
    }

    static ArrayList<Edge>[] graph;

    public static void display(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            System.out.print(i + "-> "); // u

            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + ", " + e.w + ")"); // v
            }
            System.out.println();
        }

        System.out.println();
    }

    public static void addEdge(ArrayList<Edge>[] gp, int u, int v, int w) {
        gp[u].add(new Edge(v, w));
        gp[v].add(new Edge(u, w));
    }

    static int[] par;
    static int[] setSize;

    public static int findPar(int vtx) {
        if (par[vtx] == vtx)
            return vtx;
        return findPar(par[vtx]);
    }

    public static void mergeset(int l1, int l2) {
        if (setSize[l2] > setSize[l1]) {
            par[l2] = l1;
            setSize[l1] += l2;
        } else {
            par[l1] = l2;
            setSize[l2] += l1;
        }
    }

    public static void constructGraph() {
        graph = new ArrayList[5];
        for (int i = 0; i < 5; i++) {
            graph[i] = new ArrayList<>();
        }
        addEdge(graph, 0, 1, 2);
        addEdge(graph, 0, 3, 6);
        addEdge(graph, 1, 2, 3);
        addEdge(graph, 1, 3, 8);
        addEdge(graph, 1, 4, 5);
        addEdge(graph, 2, 4, 7);
        addEdge(graph, 3, 4, 9);
        // addEdge(graph, 5, 6, 8);
        // addEdge(graph, 2, 5, 2);

    }

    static int N = 5;

    /// sbse km wali edge sbse phle lagayega
    public static void kruskalAlgo(int[][] arr) {
        par = new int[4];
        setSize = new int[4];
        for (int i = 0; i < 4; i++) {
            par[i] = i;
            setSize[i] = 1;
        }
        ArrayList<Edge>[] KGraph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            KGraph[i] = new ArrayList<Edge>();
        }

        Arrays.sort(arr, (int[] a, int[] b) -> {
            return a[2] - b[2];
        });
        for (int[] ar : arr) {
            int p1 = findPar(ar[0]);
            int p2 = findPar(ar[1]);
            if (p1 != p2) {
                mergeset(p1, p2);
                addEdge(KGraph, ar[0], ar[1], ar[2]);
            }
        }
        display(KGraph);
    }

    static class pair_ {
        int src = 0;
        int par = 0;
        int w = 0;
        int wsf = 0;

        pair_(int src, int par, int w, int wsf) {
            this.src = src;
            this.par = par;
            this.w = w;
            this.wsf = wsf;
        }
    }

    public static void dijikstraAlgo(int src) {
        ArrayList<Edge>[] DGraph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            DGraph[i] = new ArrayList<Edge>();
        }

        PriorityQueue<pair_> pq = new PriorityQueue<>((pair_ a, pair_ b) -> {
            return a.wsf - b.wsf;
        });
        boolean[] vis = new boolean[N];
        pq.add(new pair_(src, -1, 0, 0));
        while (pq.size() != 0) {
            int siz = pq.size();
            while (siz-- > 0) {
                pair_ rvtx = pq.poll();
                if (vis[rvtx.src])
                    continue;
                if (rvtx.par != -1)
                    addEdge(DGraph, rvtx.src, rvtx.par, rvtx.w);
                vis[rvtx.src] = true;
                for (Edge e : graph[rvtx.src]) {
                    if (!vis[e.v])
                        pq.add(new pair_(e.v, rvtx.src, e.w, rvtx.wsf + e.w));
                }
            }
        }
    }

    public static void primsAlgo(int src) {
        ArrayList<Edge>[] PGraph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            PGraph[i] = new ArrayList<Edge>();
        }

        PriorityQueue<pair_> pq = new PriorityQueue<>((pair_ a, pair_ b) -> {
            return a.w - b.w;
        });
        boolean[] vis = new boolean[N];
        pq.add(new pair_(src, -1, 0, 0));
        while (pq.size() != 0) {
            int siz = pq.size();
            while (siz-- > 0) {
                pair_ rvtx = pq.poll();
                if (vis[rvtx.src])
                    continue;
                if (rvtx.par != -1)
                    addEdge(PGraph, rvtx.src, rvtx.par, rvtx.w);
                vis[rvtx.src] = true;
                for (Edge e : graph[rvtx.src]) {
                    if (!vis[e.v])
                        pq.add(new pair_(e.v, rvtx.src, e.w, rvtx.wsf + e.w));
                }
            }
        }
        display(PGraph);
    }
}