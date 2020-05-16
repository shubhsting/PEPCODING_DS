
import java.util.*;

public class prisonbreak {
    public static void main(String args[]) throws Exception {

        Scanner scn = new Scanner(System.in);
        int tc = scn.nextInt();
        while (tc-- > 0) {
            int n = scn.nextInt();
            int[][] arr = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = scn.nextInt();
                }
            }
            System.out.println(dfs(arr, 0, 0));

        }
    }

    static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };

    public static int dfs(int[][] arr, int cr, int cc) {
        if (cr == arr.length - 1 && cc == arr[0].length - 1) {
            return 1;
        }
        arr[cr][cc] = 2;
        int count = 0;
        for (int i = 0; i < dir.length; i++) {
            int r = cr + dir[i][0];
            int c = cc + dir[i][1];
            if (r >= 0 && r < arr.length && c >= 0 && c < arr[0].length && arr[r][c] == 0)
                count += dfs(arr, r, c);
        }
        arr[cr][cc] = 0;
        return count;
    }

}
