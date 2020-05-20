import java.util.*;

import javax.lang.model.type.ArrayType;

public class L00 {
    public static class Node {
        int data;
        Node left = null;
        Node right = null;

        Node(int data) {
            this.data = data;
        }
    }

    static int idx = 0;

    public static Node constructTree(int[] arr) {
        if (idx == arr.length || arr[idx] == -1) {
            idx++;
            return null;
        }
        Node root = new Node(arr[idx++]);
        root.left = constructTree(arr);
        root.right = constructTree(arr);
        return root;
    }

    public static void display(Node root) {
        if (node == null)
            return;
        String str = "";
        str += ((node.left != null) ? node.left.data : ".");
        str += " <- " + node.data + " -> ";
        str += ((node.right != null) ? node.right.data : ".");
        System.out.println(str);
        display(root.left);
        display(root.right);
    }

    public static int size(Node root) {
        if (node == null)
            return 0;
        return size(root.left) + size(root.right) + 1;
    }

    public static int height(Node root) {
        if (root == null)
            return -1;
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public static int maximum(Node root) {
        if (node == null)
            return Integer.MIN_VALUE;
        return Math.max(Math.max(root.data, maximum(root.left)), maximum(root.right));
    }

    public static int minimum(Node root) {
        if (node == null)
            return Integer.MAX_VALUE;
        return Math.MIN(Math.MIN(root.data, minimum(root.left)), minimum(root.right));
    }

    public static boolean find(Node node, int num) {
        if (node == null)
            return false;
        if (node.data == num)
            return true;
        return find(node.left, num) || find(node.right, num);
    }

    public static void preOrder(Node node) {
        if (node == null)
            return;
        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void inOrder(Node node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }

    public static void postOrder(Node node) {
        if (node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.data);
    }

    public static boolean rootToNode(Node node, int num, ArrayList<Node> path) {
        if (node == null)
            return false;
        if (node.data == num) {
            path.add(node);
            return true;
        }
        boolean res = rootToNode(node.left, num, path) || rootToNode(node.right, num, path);
        if (res)
            path.add(node);
        return res;
    }

    public static void rootToNodePath(Node root, int data) {
        ArrayList<Node> path = new ArrayList<>();
        rootToNode(root, data, path);
        for (Node n : path) {
            System.out.print(n.data + " -> ");
        }
    }

    public static ArrayList<Node> rootToNode_02(Node node, int num) {
        if (node == null)
            return new ArrayList<Node>();

        if (node.data == num) {
            ArrayList<Node> temp = new ArrayList<>();
            temp.add(node);
            return temp;
        }

        ArrayList<Node> left = rootToNode_02(node.left, num, path);
        if (left.size() != 0) {
            left.add(node);
            return left;
        }
        ArrayList<Node> right = rootToNode_02(node.right, num, path);
        if (right.size() != 0) {
            right.add(node);
            return right;
        }
        return new ArrayList<>();
    }

    public Node lowestCommAncestor(Node root, int p, int q) {
        ArrayList<Node> path1 = new ArrayList<>();
        ArrayList<Node> path2 = new ArrayList<>();

        rootToNode(root, p, path1);
        rootToNode(root, q, path2);
        Node prev = null;
        int i = path1.size() - 1;
        int j = path2.size() - 1;

        while (i >= 0 && j >= 0) {
            if (path1.get(i).data != path2.get(j).data)
                break;

            prev = path1.get(i);
            i--;
            j--;
        }

        return prev;
    }
}