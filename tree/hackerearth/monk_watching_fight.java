package hackerearth;

import java.util.*;

class TestClass {
    public static class Node {
        Node left = null;
        Node right = null;
        int data = 0;

        Node(int data) {
            this.data = data;
        }
    }

    public static void main(String args[]) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        Node root = null;
        for (int i = 0; i < n; i++) {
            int m = scn.nextInt();
            root = addNode(root, m);
        }

        System.out.println(height(root));
        scn.close();
    }

    public static int height(Node root) {
        if (root == null)
            return 0;

        int left = height(root.left);
        int right = height(root.right);
        return Math.max(left, right) + 1;

    }

    public static Node addNode(Node root, int val) {
        if (root == null)
            return new Node(val);

        if (val <= root.data)
            root.left = addNode(root.left, val);
        if (val > root.data)
            root.right = addNode(root.right, val);
        return root;
    }
}
