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

class treequery {
    public static void main(String args[]) throws Exception {
        /*
         * Sample code to perform I/O: Use either of these methods for input
         * 
         * //BufferedReader BufferedReader br = new BufferedReader(new
         * InputStreamReader(System.in)); String name = br.readLine(); // Reading input
         * from STDIN System.out.println("Hi, " + name + "."); // Writing output to
         * STDOUT
         * 
         * //Scanner Scanner s = new Scanner(System.in); String name = s.nextLine(); //
         * Reading input from STDIN System.out.println("Hi, " + name + "."); // Writing
         * output to STDOUT
         * 
         */

        // Write your code here

        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        ArrayList<Integer>[] rgraph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
            rgraph[i] = new ArrayList<>();
        }
        int m = scn.nextInt();
        while (m-- > 0) {
            int p = scn.nextInt();
            int q = scn.nextInt();
            graph[p].add(q);
            rgraph[q].add(p);
        }
        int ac = 0;
        int bc = 0;
        for (int i = 1; i < graph.length; i++) {
            if (graph[i].size() == 0)
                ac++;
        }
        for (int i = 1; i < graph.length; i++) {
            if (rgraph[i].size() == 0)
                bc++;
        }
        System.out.println(Math.max(ac, bc));
    }
}
