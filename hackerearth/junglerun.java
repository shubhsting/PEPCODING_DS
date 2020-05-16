
import java.util.*;

class junglerun {
    public static void main(String args[]) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        char[][] arr = new char[n][n];
        boolean[][] vis = new boolean[n][n];
        int sx = 0;
        int sy = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = scn.next().charAt(0);
                if (arr[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }
            }
        }

        bfs(arr, sx, sy, 0, vis);
        System.out.print(fcount);
    }

    static int[][] dir = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };
    static int fcount = Integer.MAX_VALUE;

    public static void bfs(char[][] grid, int cr, int cc, int count, boolean[][] vis) {
        if (grid[cr][cc] == 'E') {
            if (fcount > count) {
                fcount = count;
            }
            return;
        }
        vis[cr][cc] = true;
        for (int i = 0; i < dir.length; i++) {
            int r = cr + dir[i][0];
            int c = cc + dir[i][1];
            if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] != 'T' && !vis[r][c]) {
                bfs(grid, r, c, count + 1, vis);
            }
        }
        vis[cr][cc] = false;
    }

}
