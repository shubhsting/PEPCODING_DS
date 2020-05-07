import java.util.ArrayList;

public class unionFind {

    public class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    static int N = 7;
    static ArrayList<Edge>[] graph;

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
    public static void constructGraph()
    {
        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 4, 6, 3);
        addEdge(graph, 5, 6, 8);
    
        // addEdge(graph, 2, 5, 2);
    
    
    }
    
    void solve()
    {
        constructGraph();
    }
    
    public static void main(String[] args) {

    }
}