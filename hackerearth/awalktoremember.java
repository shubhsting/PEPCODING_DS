import java.util.*;

class awalktoremember {
    public static void main(String args[]) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        ArrayList<Integer>[] ngraph = new ArrayList[n + 1];
        for (int i = 1; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
            ngraph[i] = new ArrayList<>();
        }
        while (m-- > 0) {
            int p = scn.nextInt();
            int q = scn.nextInt();
            graph[p].add(q);
            ngraph[q].add(p);
        }
        boolean[] vis = new boolean[n + 1];
        ArrayList<Integer> top = new ArrayList<>();
        for (int i = 1; i < n + 1; i++) {
            if (!vis[i])
                top_sort(graph, vis, i, top);
        }
        vis = new boolean[n + 1];
        int[] arr = new int[n + 1];
        for (int i = top.size() - 1; i >= 0; i--) {
            ArrayList<Integer> ans = new ArrayList<>();
            if (!vis[top.get(i)])
                dfs(ngraph, top.get(i), vis, ans);
            if (ans.size() > 1) {
                for (int ip = 0; ip < ans.size(); ip++)
                    arr[ans.get(ip)] = 1;
            }
        }
        for (int i = 1; i < n + 1; i++)
            System.out.print(arr[i] + " ");
    }

    public static void dfs(ArrayList<Integer>[] ngraph, int src, boolean[] vis, ArrayList<Integer> ans) {
        vis[src] = true;
        for (Integer e : ngraph[src]) {
            if (!vis[e])
                dfs(ngraph, e, vis, ans);
        }
        ans.add(src);
    }

    public static void top_sort(ArrayList<Integer>[] graph, boolean[] vis, int src, ArrayList<Integer> as) {
        vis[src] = true;
        for (Integer e : graph[src]) {
            if (!vis[e])
                top_sort(graph, vis, e, as);
        }
        as.add(src);
    }
}
