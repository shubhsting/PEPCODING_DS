/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
import java.util.*;
*/

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail
import java.util.*;

public class crazymatrix {
    public static void main(String args[]) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = scn.nextInt();
            }
        }
        boolean resx = false;
        for (int i = 0; i < n; i++) {
            if (arr[0][i] == 1) {
                resx = dfsone(arr, 0, i);
                if (resx)
                    break;
            }
        }
        boolean resy = false;
        for (int j = 0; j < n; j++) {
            if (arr[j][0] == 2) {
                resy = dfstwo(arr, j, 0);
                if (resy)
                    break;
            }
        }
        if (resx && resy) {
            System.out.println("AMBIGUOUS");
        } else if (resx) {
            System.out.println("1");
        } else if (resy) {
            System.out.println("2");
        }

        else {
            System.out.println("0");
        }
    }

    static int[][] dir = { { 1, 0 }, { 0, -1 }, { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 }, { 0, 1 }, { -1, 0 } };

    public static boolean dfsone(int[][] arr, int cr, int cc) {
        if (cr == arr.length - 1) {
            return true;
        }
        arr[cr][cc] = 10;
        boolean res = false;
        for (int i = 0; i < dir.length; i++) {
            int r = cr + dir[i][0];
            int c = cc + dir[i][1];
            if (r >= 0 && r < arr.length && c >= 0 && c < arr[0].length && arr[r][c] == 1)
                res = res || dfsone(arr, r, c);
        }
        // arr[cr][cc]=1;
        return res;
    }

    public static boolean dfstwo(int[][] arr, int cr, int cc) {
        if (cc == arr[0].length - 1) {
            return true;
        }
        arr[cr][cc] = 10;
        boolean res = false;
        for (int i = 0; i < dir.length; i++) {
            int r = cr + dir[i][0];
            int c = cc + dir[i][1];
            if (r >= 0 && r < arr.length && c >= 0 && c < arr[0].length && arr[r][c] == 2)
                res = res || dfstwo(arr, r, c);
        }
        // arr[cr][cc]=2;
        return res;
    }
}
