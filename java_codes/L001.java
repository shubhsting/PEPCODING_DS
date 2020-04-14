import java.util.ArrayList;
// import java.util.*;

public class L001 {
    public static void main(String[] args) {
        solve();

    }

    public static void solve() {
        // set1();
        // set2();
        set3();
    }

    public static void set1() {
        // int m=factorial(5);
        int m = powerEf(5, 2);
        System.out.println(m);
    }

    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
        // return n<=1?1:n*factorial(n-1);
    }

    public static int power(int n, int p) {
        return p == 0 ? 1 : n * power(n, p - 1);
    }

    public static int powerEf(int n, int p) {
        if (p == 0) {
            return 1;
        }
        return p % 2 == 0 ? (powerEf(n, p / 2) * powerEf(n, p / 2)) : n * (powerEf(n, p / 2) * powerEf(n, p / 2));
    }

    // this question is for recursion practice
    public static int calls(int n) { // n=5
        if (n <= 1) {
            System.out.println("base: " + n);
            return n + 1;
        }

        int count = 0;

        System.out.println("Pre: " + n);
        count += calls(n - 1);

        System.out.println("In: " + n);

        count += calls(n - 2);
        System.out.println("Post: " + n);

        return count + 3;
    }

    // ==================================== set 2
    // ========================================

    public static void set2() {
        int[] arr = { 12, 23, 34, 56, 78, 90 };
        // display(arr,0);
        // System.out.print(find_02(arr,0,910));
        // maximum_02(arr,0);
        System.out.println(minimum(arr, 0));
    }

    public static void display(int[] arr, int vidx) {
        if (vidx == arr.length) {
            return;
        }
        System.out.print(arr[vidx] + " ");
        display(arr, vidx + 1);
    }

    public static boolean find(int[] arr, int vidx, int num) {
        if (vidx == arr.length)
            return false;

        if (arr[vidx] == num)
            return true; // pre section mein check kr rhe hai isme

        return find(arr, vidx + 1, num);
    }

    public static boolean find_02(int[] arr, int vidx, int num) {
        if (vidx == arr.length)
            return false;

        boolean ans = find_02(arr, vidx + 1, num);
        // if(ans) return true; ye post area mein check kr rha hai
        // else return arr[vidx]==num;
        return (arr[vidx] == num) || ans;
    }

    public static int maximum(int[] arr, int vidx) {
        if (vidx == arr.length - 1)
            return arr[vidx];
        // last pauchne ke baad hm check krte hai
        return Math.max(arr[vidx], maximum(arr, vidx + 1)); // max(a,b) for c++
    }

    static int max_ = -1;

    public static void maximum_02(int[] arr, int vidx) {
        if (vidx == arr.length)
            return;
        // ander jaate waqt hi check krte chlte hai
        // isiliye dono code maximum ke alag hai
        max_ = Math.max(arr[vidx], max_);
        maximum_02(arr, vidx + 1);

    }

    public static int minimum(int[] arr, int vidx) {

        if (vidx == arr.length - 1)
            return arr[vidx];

        return Math.min(arr[vidx], minimum(arr, vidx + 1));

    }

    // ============================================set 3
    // ========================================================

    public static void set3() {
        System.out.print(per_withdupl_02("abc", ""));

    }

    // subsequence of abc is [, a, b, ab, c, ac, bc, abc]
    public static ArrayList<String> subseq(String str) {
        if (str.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        char ch = str.charAt(0);
        ArrayList<String> smallAns = subseq(str.substring(1));
        ArrayList<String> newAns = new ArrayList<>();
        for (String random : smallAns) {
            newAns.add(random);
            newAns.add(ch + random);
        }
        return newAns;
    }

    public static int subseq_02(String str, String ans) {
        if (str.length() == 0) {
            System.out.print(ans + " ");
            return 1;
        }

        int count = 0;
        count = count + subseq_02(str.substring(1), ans);
        count = count + subseq_02(str.substring(1), ans + str.charAt(0));

        return count;
    }

    public static ArrayList<String> per_withdup(String ques) {
        if (ques.length() == 1) {
            ArrayList<String> base = new ArrayList<>();
            base.add(ques);
            return base;
        }
        char ch = ques.charAt(0);
        ArrayList<String> smallAns = per_withdup(ques.substring(1));
        ArrayList<String> newAns = new ArrayList<>();
        for (String s : smallAns) {
            for (int i = 0; i <= s.length(); i++) {
                newAns.add(s.substring(0, i) + ch + s.substring(i));
            }
        }
        return newAns;
    }

    public static int per_withdupl_02(String ques, String ans) {

        if (ques.length() == 0) {
            System.out.print(ans + " ");
            return 1;
        }
        int count = 0;
        for (int i = 0; i < ques.length(); i++) {
            char ch = ques.charAt(i);
            count = count + per_withdupl_02(ques.substring(0, i) + ques.substring(i + 1), ans + ch);
        }
        return count;
    }

    // public static int per_without_dupl(String ques, String ans) {

    // }
}
