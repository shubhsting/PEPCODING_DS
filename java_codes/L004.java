public class L004 {
    public static void main(String[] args) {
        // int[][] arr=new int[4][4];
        int[] money = { 1, 2, 3 };
        int[] arr = new int[5];
        boolean[] arr1 = new boolean[5];
        System.out.println(qpermutation(arr, "", 0, arr1));
        // System.out.print(coinchangeP(money,0,3,""));
        // pair p=floodfill_Min(0,0,3,3,arr);
        // System.out.print(p.len+" "+p.str);
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

    public static void qcombination(int[] arr, String ans, int psf) {

    }

}