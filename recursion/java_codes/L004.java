// coin change ke saare question hai aur floodfill ke bhi hai 
public class L004 {
    public static void main(String[] args) {
        // int[][] arr=new int[4][4];
        // int[] money = { 1, 2, 3 };
        // int[] arr = new int[5];
        // boolean[] arr1 = new boolean[5];
        // System.out.println(qpermutation(arr, "", 0, arr1));
        // System.out.print(coinchangeP(money,0,3,""));
        // pair p=floodfill_Min(0,0,3,3,arr);
        // System.out.print(p.len+" "+p.str);
        crossword(0);
        for (char[] ar : board) {
            for (char ch : ar)
                System.out.print(ch + " ");
            System.out.println();
        }
    }

    static int[][] dir = { { 0, 1 }, { -1, 1 }, { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
    static String[] dirN = { "R", "E", "U", "N", "L", "W", "D", "S" };

    public static int floodfill_Height(int cr, int cc, int er, int ec, int[][] board) {
        if (cr == er && cc == ec) {
            return 0;
        }
        board[cr][cc] = 2;
        int height = 0;
        for (int i = 0; i < dir.length; i++) {
            int r = cr + dir[i][0];
            int c = cc + dir[i][1];
            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length && board[r][c] != 2 && board[r][c] == 0) {
                int newheight = floodfill_Height(r, c, er, ec, board);
                height = Math.max(height, newheight);
            }
        }
        board[cr][cc] = 0;
        return height + 1;
    }

    static class pair {
        int len = 0;
        String str = "";

        pair(int length, String string) {
            this.len = length;
            this.str = string;
        }
    }

    public static pair floodfill_Max(int cr, int cc, int er, int ec, int[][] board) {
        if (cr == er && cc == ec) {
            return new pair(0, "");
        }
        board[cr][cc] = 2;
        pair mypair = new pair(0, "");
        mypair.len = 0;
        mypair.str = "";
        for (int i = 0; i < dir.length; i++) {
            int r = cr + dir[i][0];
            int c = cc + dir[i][1];
            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length && board[r][c] != 2 && board[r][c] == 0) {
                pair recpair = floodfill_Max(r, c, er, ec, board);
                if (recpair.len + 1 > mypair.len) {
                    mypair.len = recpair.len + 1;
                    mypair.str = recpair.str + dirN[i];
                }
            }
        }
        board[cr][cc] = 0;
        return mypair;
    }

    public static pair floodfill_Min(int cr, int cc, int er, int ec, int[][] board) {
        if (cr == er && cc == ec) {
            return new pair(0, "");
        }
        board[cr][cc] = 2;
        pair mypair = new pair(0, "");
        mypair.len = Integer.MAX_VALUE;
        mypair.str = "";
        for (int i = 0; i < dir.length; i++) {
            int r = cr + dir[i][0];
            int c = cc + dir[i][1];
            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length && board[r][c] != 2 && board[r][c] == 0) {
                pair recpair = floodfill_Min(r, c, er, ec, board);
                if (recpair.len != Integer.MAX_VALUE && recpair.len + 1 < mypair.len) {
                    mypair.len = recpair.len + 1;
                    mypair.str = recpair.str + dirN[i];
                }
            }
        }
        board[cr][cc] = 0;
        return mypair;
    }

    public static int coinchangeP(int[] coins, int money, int total, String ans) {
        if (money == total) {
            System.out.println(ans);
            return 1;
        }
        if (money > total) {
            return 0;
        }
        int ftotal = 0;
        for (int i = 0; i < coins.length; i++) {
            ftotal += coinchangeP(coins, money + coins[i], total, ans + coins[i]);
        }
        return ftotal;
    }

    public static int qpermutation(int[] arr, String ans, int psf, boolean[] arr1) {
        if (psf == 3) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!arr1[i]) {
                arr1[i] = true;
                count += qpermutation(arr, ans + "b" + i + "q" + psf + " ", psf + 1, arr1);
                arr1[i] = false;
            }
        }
        return count;
    }

    // ===========================================coin
    // change========================================
    public static int coinchangePermuInfi_01(int[] arr, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (tar - arr[i] >= 0)
                count += coinchangePermuInfi_01(arr, tar - arr[i], ans + arr[i]);
        }
        return count;
    }

    public static int coinchangePermu_01(int[] arr, int tar, String ans, boolean[] vis) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!vis[i] && tar - arr[i] >= 0) {
                vis[i] = true;
                count += coinchangePermu_01(arr, tar - arr[i], ans + arr[i], vis);
                vis[i] = false;
            }
        }
        return count;
    }

    public static int coinchangeCombi_INFI_01(int[] arr, int idx, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0)
                count += coinchangeCombi_INFI_01(arr, i, tar - arr[i], ans + arr[i]);
        }
        return count;
    }

    public static int coinchangeCombi_01(int[] arr, int idx, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0)
                count += coinchangeCombi_01(arr, i + 1, tar - arr[i], ans + arr[i]);
        }
        return count;
    }

    // 2 tree ki help se combination nikalenge ek mein include krenge aur doosri
    // mein nhi krenge
    public static int coinchangeCombi_02(int[] arr, int idx, int tar, String ans) {
        if (idx == arr.length || tar == 0) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (tar - arr[idx] >= 0)
            count += coinchangeCombi_02(arr, idx + 1, tar - arr[idx], ans + arr[idx]);
        count += coinchangeCombi_02(arr, idx + 1, tar, ans);
        return count;
    }

    public static int coinchangeCombiINFI_02(int[] arr, int idx, int tar, String ans) {
        if (idx == arr.length || tar == 0) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (tar - arr[idx] >= 0)
            count += coinchangeCombiINFI_02(arr, idx, tar - arr[idx], ans + arr[idx]);
        count += coinchangeCombiINFI_02(arr, idx + 1, tar, ans);
        return count;
    }

    public static int coinChangePermuINFI_02(int[] arr, int idx, int tar, String ans) {
        if (idx == arr.length || tar == 0) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }

            return 0;
        }

        int count = 0;
        if (tar - arr[idx] >= 0)
            count += coinChangePermuINFI_02(arr, 0, tar - arr[idx], ans + arr[idx] + " ");
        count += coinChangePermuINFI_02(arr, idx + 1, tar, ans);

        return count;
    }

    public static int coinChangePermu_02(int[] arr, boolean[] vis, int idx, int tar, String ans) {
        if (idx == arr.length || tar == 0) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }

            return 0;
        }

        int count = 0;
        if (tar - arr[idx] >= 0 && !vis[idx]) {
            vis[idx] = true;
            count += coinChangePermu_02(arr, vis, 0, tar - arr[idx], ans + arr[idx] + " ");
            vis[idx] = false;
        }

        count += coinChangePermu_02(arr, vis, idx + 1, tar, ans);

        return count;
    }

    static char[][] board = { { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
            { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' }, { '+', '-', '-', '-', '-', '-', '-', '-', '+', '+' },
            { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' }, { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
            { '+', '-', '-', '-', '-', '-', '-', '+', '+', '+' }, { '+', '-', '+', '+', '+', '-', '+', '+', '+', '+' },
            { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' }, { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
            { '+', '+', '+', '+', '+', '+', '+', '+', '+', '+' } };
    static String[] words = { "agra", "norway", "england", "gwalior" };

}