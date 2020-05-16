import java.util.*;

class aryasstunt {
    public static void main(String args[]) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int m = scn.nextInt();
        int[] arr = new int[n + 1];
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        for (int i = 1; i < n + 1; i++) {
            arr[i] = scn.nextInt();
        }

        while (m-- > 0) {
            int p = scn.nextInt();
            int q = scn.nextInt();
            graph[p].add(q);
            graph[q].add(p);

        }
        initialdfs(graph, arr);
    }

    static int maxi = 0;
    static int idx = 0;

    public static void initialdfs(ArrayList<Integer>[] gp, int[] arr) {
        // for(int i=0;i<arr.length;i++){
        // System.out.print(arr[i]+" ");
        // }
        int kill = 0;
        String ans = "";
        ArrayList<Integer> fans = new ArrayList<>();
        boolean[] vis = new boolean[gp.length];
        for (int i = 1; i < gp.length; i++) {
            if (!vis[i]) {
                idx = i;
                maxi = arr[i];
                dfsfinal(gp, i, vis, arr);
                kill += maxi;
                // ans=ans+idx+" ";
                fans.add(idx);
            }
        }
        Collections.sort(fans);
        int up = 0;
        for (int i = 0; i < arr.length; i++) {
            up = up + arr[i];
        }
        System.out.println(kill + " " + (up - kill));
        for (int i = 0; i < fans.size(); i++) {
            System.out.print(fans.get(i) + " ");
        }

    }

    public static void dfsfinal(ArrayList<Integer>[] gp, int curr, boolean[] vis, int[] arr) {
        vis[curr] = true;
        for (Integer e : gp[curr]) {
            if (!vis[e]) {
                if (arr[e] > maxi) {
                    maxi = arr[e];
                    idx = e;
                } else if (arr[e] == maxi) {
                    if (idx > e)
                        idx = e;
                }
                dfsfinal(gp, e, vis, arr);
            }
        }
    }
}
