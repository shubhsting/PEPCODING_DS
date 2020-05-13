public class hackerearth {
    public static void main(String[] args) {

    }

    private static ArrayList<Integer>[] g;
    private static int time = 0;
    private static int[] discoverTime;
    private static int[] processedTime;
    private static boolean[] visited;

    public static void solve() throws Exception {
        Scanner scan = new Scanner(Syatem.in);
        int n = scan.nextInt();

        g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < n - 1; i++) {
            int a = scan.nextInt() - 1;
            int b = scan.nextInt() - 1;

            g[a].add(b);
            g[b].add(a);
        }

        discoverTime = new int[n];
        processedTime = new int[n];
        visited = new boolean[n];

        dfs(0);

        int q = scan.nextInt();
        for (int i = 0; i < q; i++) {
            int towards = scan.nextInt();
            int end = scan.nextInt() - 1;
            int start = scan.nextInt() - 1;

            if (towards == 0) {
                if (discoverTime[end] <= discoverTime[start] && processedTime[start] <= processedTime[end]) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            } else {
                if (discoverTime[start] <= discoverTime[end] && processedTime[end] <= processedTime[start]) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }

    
    }

    private static void dfs(int u) {
        discoverTime[u] = time++;
        visited[u] = true;
        for (int v : g[u]) {
            if (visited[v])
                continue;
            dfs(v);
        }
        processedTime[u] = time++;
    }
}
