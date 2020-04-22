import java.util.ArrayList;

public class L00 {
    public static class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static int n = 7;
    public static ArrayList<Edge>[] graph = new ArrayList[n];

    public static void addEdge(ArrayList<Edge>[] gp, int u, int v, int w) {
        gp[u].add(new Edge(v, w));
        gp[v].add(new Edge(u, w));

    }

    public static void display(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            System.out.print(i + "--> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + "," + e.w + ")");
            }
            System.out.println();
        }
    }

    public static void constructGraph() {

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<Edge>();
        }
        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 4, 6, 3);
        addEdge(graph, 5, 6, 8);
        display(graph);
    }

    public static void main(String[] args) {
        constructGraph();
    }
}