import java.util.*;
import java.io.*;

public class wordsandtrees {

    static char[] letter;
    static ArrayList<LinkedList<Integer>> adj = new ArrayList<LinkedList<Integer>>();
    static int[] parents;

    static void fillParents(int n) {
        for (int i : adj.get(n)) {
            if (parents[i] == 0) {
                parents[i] = n;
                fillParents(i);
            }
        }
    }

    static int findLetters(int n, ArrayList<Character> c) {
        for (int i : adj.get(n)) {
            if (parents[i] == n) {
                findLetters(i, c);
            }
        }
        remove(letter[n], c);
        return c.size();
    }

    static boolean remove(char ch, ArrayList<Character> c) {
        int l = 0;
        int r = c.size() - 1;
        while (r >= l) {
            int m = l + (r - l) / 2;
            if (c.get(m) == ch) {
                c.remove(m);
                return true;
            } else if (c.get(m) > ch) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        letter = new char[n + 1];
        parents = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            adj.add(new LinkedList<Integer>());
        }

        String letters = in.readLine();
        for (int i = 0; i < n; i++) {
            letter[i + 1] = letters.charAt(i * 2);
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        parents[1] = 1;
        fillParents(1);
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            String s = st.nextToken();
            ArrayList<Character> c = new ArrayList<Character>();
            for (int j = 0; j < s.length(); j++) {
                c.add(s.charAt(j));
            }
            c.sort(null);
            System.out.println(findLetters(x, c));
        }
    }
}