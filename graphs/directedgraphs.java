import java.util.*;

public class directedgraphs {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        constructGraph();
        // topologicalsort();
        // KahnsAlgo();
        // topologicalsortcycle();
        SCC();
    }

    public static int N = 8;
    public static ArrayList<Integer>[] graph;

    public static void display() {
        for (int i = 0; i < graph.length; i++) {
            System.out.print(i + "-> "); // u

            for (Integer e : graph[i]) {
                System.out.print(e + ", "); // v
            }
            System.out.println();
        }

        System.out.println();
    }

    public static void constructGraph() {
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        graph[1].add(2);
        graph[2].add(3);
        graph[3].add(4);
        graph[4].add(5);
        graph[4].add(2);
        // graph[6].add(4);
        // graph[6].add(3);
        // graph[2].add(1);
        // graph[3].add(1);
        // graph[1].add(0);

        // graph[0].add(1);
        // graph[1].add(2);
        // graph[2].add(3);
        // graph[3].add(0);
        // graph[2].add(4);
        // graph[4].add(5);
        // graph[5].add(6);
        // graph[6].add(4);

        // display();
    }

    public static void topologicalsort_(boolean[] vis, int src, ArrayList<Integer> ans) {
        vis[src] = true;
        for (Integer e : graph[src]) {
            if (!vis[e])
                topologicalsort_(vis, e, ans);
        }
        ans.add(src);
    }

    public static void topologicalsort() {
        boolean[] vis = new boolean[N];
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = N - 1; i >= 0; i--) {
            if (!vis[i])
                topologicalsort_(vis, i, ans);
        }

        for (int i = ans.size() - 1; i >= 0; i--)
            System.out.print(ans.get(i) + " ");

        System.out.println();
    }

    public static void KahnsAlgo() {
        int[] indegree = new int[N];
        for (int i = 0; i < N; i++) {
            for (Integer e : graph[i])
                indegree[e]++;
        }
        LinkedList<Integer> que = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                que.addLast(i);
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        while (que.size() != 0) {
            int siz = que.size();
            while (siz-- > 0) {
                int vtx = que.removeFirst();
                ans.add(vtx);
                for (int e : graph[vtx]) {
                    if (--indegree[e] == 0)
                        que.addLast(e);

                }
            }
        }
        if (ans.size() != N)
            System.out.println("Cycle");
        else
            System.out.println(ans);
    }

    public static boolean topologicalsortcycle_(int[] vis, int src, ArrayList<Integer> ans) {
        if (vis[src] == 1) // current path mein use kr rhe hai iss vertex ko isiliye cycle aa gyi hai
            return true;
        if (vis[src] == 2) ///// already visited vertex
            return false;
        vis[src] = 1;
        boolean res = false;
        for (Integer e : graph[src]) {
            res = res || topologicalsortcycle_(vis, e, ans);
        }
        vis[src] = 2;
        ans.add(src);
        return res;
    }

    public static void topologicalsortcycle() {
        int[] vis = new int[N];
        ArrayList<Integer> ans = new ArrayList<>();
        boolean fres = false;
        for (int i = N - 1; i >= 0; i--) {
            if (vis[i] == 0)
                fres = fres || topologicalsortcycle_(vis, i, ans);
        }
        if (!fres) {
            for (int i = ans.size() - 1; i >= 0; i--)
                System.out.print(ans.get(i) + " ");
        } else {
            System.out.println("Cycle");
        }
    }

    public static int DFS_SCC(int src, ArrayList<Integer>[] ngraph, boolean[] vis, ArrayList<Integer> ans) {
        vis[src] = true;
        int count = 0;
        for (Integer e : ngraph[src]) {
            if (!vis[e])
                count += DFS_SCC(e, ngraph, vis, ans);
        }
        ans.add(src);
        return count + 1;
    }

    // ye function ye btayega ki kitne independent components hai graph mein mtlb
    // kitne components ko merge krke hm ek bna skte hai indirectly number of cycles
    // hi puch rha hai
    // graph ulta krne pr bhi cycle cycle hi rhegi baaki sb change ho jayega
    public static void SCC() {
        boolean[] vis = new boolean[N];
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < N; i++) {

            if (!vis[i])
                topologicalsort_(vis, i, ans);
        }
        // ye graph hmne ulta bnaya hai
        ArrayList<Integer>[] ngraph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            ngraph[i] = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int ele : graph[i]) {
                ngraph[ele].add(i);
            }
        }
        vis = new boolean[N];
        for (int i = ans.size() - 1; i >= 0; i--) {
            ArrayList<Integer> ans_ = new ArrayList<>();
            if (!vis[ans.get(i)]) {
                System.out.println(DFS_SCC(ans.get(i), ngraph, vis, ans_));
                System.out.println(ans_);
            }

        }
    }
}