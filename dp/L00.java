import java.util.*;

public class L00 {
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        int[] arr = new int[9];
        int[] dice = { 1, 2, 3, 4, 5, 6 };
        // display1D(arr);
        // int[][] arr = new int[4][4];
        // System.out.println(fibonacciDP(5, arr));
        // System.out.println(boardpath(0, 8, arr));
        System.out.println(boardpathDiceArray(0, 7, arr, dice));
        // display1D(arr);
    }

    public static void display1D(int[] arr) {
        for (int ele : arr)
            System.out.print(ele + " ");
    }

    public static void display2D(int[][] arr) {
        for (int[] ar : arr) {
            display1D(ar);
            System.out.println();
        }
    }

    public static int fibonacci(int n, int[] dp) {
        if (n <= 1)
            return dp[n] = n;
        if (dp[n] != 0)
            return dp[n];
        int num = fibonacci(n - 1, dp) + fibonacci(n - 2, dp);
        return dp[n] = num;
    }

    public static int fibonacciDP(int n, int[] dp) {
        for (int i = 0; i <= n; i++) {
            if (i <= 1) {
                dp[i] = i;
                continue;
            }

            int num = dp[i - 1] + dp[i - 2];// fibonacci(n - 1, dp) + fibonacci(n - 2, dp);
            dp[i] = num;
        }
        return dp[n];
    }

    public static int fibonacci_btr(int num) {
        int a = 0;
        int b = 1;
        int sum = 1;
        for (int i = 2; i <= num; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return sum;
    }

    // memoized solution
    public static int mazepath_HV(int sr, int sc, int er, int ec, int[][] dp) {
        if (sr == er && sc == ec)
            return dp[sr][sc] = 1;
        if (dp[sr][sc] != 0)
            return dp[sr][sc];
        int count = 0;
        if (sr + 1 <= er)
            count += mazepath_HV(sr + 1, sc, er, ec, dp);
        if (sc + 1 <= ec)
            count += mazepath_HV(sr, sc + 1, er, ec, dp);
        if (sr + 1 <= er && sc + 1 <= ec)
            count += mazepath_HV(sr + 1, sc + 1, er, ec, dp);
        return dp[sr][sc] = count;
    }

    public static int mazepath_HV_DP(int sr, int sc, int er, int ec, int[][] dp) {
        for (sr = er; sr >= 0; sr--) {
            for (sc = ec; sc >= 0; sc--) {
                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                if (sr + 1 <= er)
                    count += mazepath_HV(sr + 1, sc, er, ec, dp);
                if (sc + 1 <= ec)
                    count += mazepath_HV(sr, sc + 1, er, ec, dp);
                if (sr + 1 <= er && sc + 1 <= ec)
                    count += mazepath_HV(sr + 1, sc + 1, er, ec, dp);
                dp[sr][sc] = count;
            }
        }
        return dp[0][0];
    }

    public static int mazepath_HV_multi(int sr, int sc, int er, int ec, int[][] dp) {
        if (sr == er && sc == ec)
            return dp[sr][sc] = 1;
        if (dp[sr][sc] != 0)
            return dp[sr][sc];
        int count = 0;
        for (int jump = 1; jump + sr <= er; jump++)
            count += mazepath_HV_multi(sr + jump, sc, er, ec, dp);

        for (int jump = 1; jump + sc <= ec; jump++)
            count += mazepath_HV_multi(sr, sc + jump, er, ec, dp);

        for (int jump = 1; jump + sr <= er && jump + sc <= ec; jump++)
            count += mazepath_HV_multi(sr + jump, sc + jump, er, ec, dp);

        return dp[sr][sc] = count;
    }

    public static int mazepath_HV_multiDP(int sr, int sc, int er, int ec, int[][] dp) {
        for (sr = er; sr >= 0; sr--) {
            for (sc = ec; sc >= 0; sc--) {
                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }
                int count = 0;
                for (int jump = 1; jump + sr <= er; jump++)
                    count += mazepath_HV_multi(sr + jump, sc, er, ec, dp);

                for (int jump = 1; jump + sc <= ec; jump++)
                    count += mazepath_HV_multi(sr, sc + jump, er, ec, dp);

                for (int jump = 1; jump + sr <= er && jump + sc <= ec; jump++)
                    count += mazepath_HV_multi(sr + jump, sc + jump, er, ec, dp);

                dp[sr][sc] = count;
            }
        }
        return dp[0][0];
    }

    // dice can be from 1-6
    public static int boardpath(int sp, int ep, int[] dp) {
        if (sp == ep)
            return dp[sp] = 1;
        if (dp[sp] != 0)
            return dp[sp];
        int count = 0;
        for (int dice = 1; dice <= 6 && sp + dice <= ep; dice++) {
            count += boardpath(sp + dice, ep, dp);
        }
        return dp[sp] = count;
    }

    public static int boardpathDP(int sp, int ep, int[] dp) {
        for (sp = ep; sp >= 0; sp--) {
            if (sp == ep) {
                dp[sp] = 1;
                continue;
            }
            int count = 0;
            for (int dice = 1; dice <= 6 && sp + dice <= ep; dice++) {
                count += boardpath(sp + dice, ep, dp);
            }
            dp[sp] = count;
        }
        return dp[0];
    }

    public static int boardpath_best(int sp, int ep) {
        LinkedList<Integer> list = new LinkedList<>();
        for (sp = ep; sp >= 0; sp--) {
            if (sp > ep - 2) {
                list.addFirst(1);
                continue;
            }
            if (list.size() <= 6)
                list.addFirst(2 * list.getFirst());
            else {
                int num = (2 * list.getFirst()) - list.getLast();
                list.addFirst(num);
                list.removeLast();
            }
        }
        return list.getFirst();
    }

    public static int boardpathDiceArray(int sp, int ep, int[] dp, int[] diceArray) {
        if (sp == ep)
            return dp[sp] = 1;
        if (dp[sp] != 0)
            return dp[sp];
        int count = 0;
        for (int dice = 0; dice < diceArray.length && sp + diceArray[dice] <= ep; dice++) {
            count += boardpathDiceArray(sp + diceArray[dice], ep, dp, diceArray);
        }
        return dp[sp] = count;
    }

    public static int boardpathDiceArrayDP(int sp, int ep, int[] dp, int[] diceArray) {
        for (sp = ep; sp >= 0; sp--) {
            if (sp == ep) {
                dp[sp] = 1;
                continue;
            }

            int count = 0;
            for (int dice = 0; dice < diceArray.length && sp + diceArray[dice] <= ep; dice++) {
                count += dp[sp + diceArray[dice]];// boardpathDiceArray(sp + diceArray[dice], ep, dp, diceArray);
            }
            dp[sp] = count;
        }
        return dp[0];
    }

    // leetcode 70

    public static int climbStairs_01(int n, int[] dp) {
        if (n <= 1) {
            return dp[n] = 1;
        }

        if (dp[n] != 0)
            return dp[n];

        int ans = climbStairs_01(n - 1, dp) + climbStairs_01(n - 2, dp);
        return dp[n] = ans;
    }

    public static int climbStairs_DP(int n, int[] dp) {
        int N = n;
        for (n = 0; n <= N; n++) {

            if (n <= 1) {
                dp[n] = 1;
                continue;
            }

            int ans = dp[n - 1] + dp[n - 2]; // climbStairs_01(n - 1, dp) + climbStairs_01(n - 2, dp);
            dp[n] = ans;
        }
        return dp[N];
    }

    public static int climbStairs_btr(int n) {
        int a = 1;
        int b = 1;
        int sum = 1;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return sum;
    }

    public static int climbStairs(int n) {
        // vector<int> dp(n + 1, 0);
        int[] dp = new int[n + 1];
        // return climbStairs_01(n, dp);
        return climbStairs_DP(n, dp);
        // return climbStairs_btr(n);
    }


    
}