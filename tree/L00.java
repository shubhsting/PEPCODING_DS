import java.util.ArrayList;

public class L00 {
    public static void main(String[] args) {

    }

    public class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    static int idx = 0;

    public static Node constructTree(int[] arr) {
        if (idx >= arr.length || arr[idx] == -1) {
            idx++;
            return null;
        }
        Node root = new Node(arr[idx++]);
        root.left = constructTree(arr);
        root.right = constructTree(arr);
        return root;
    }

    public static void display(Node root) {
        String ans = "";
        if (root.left != null)
            ans += root.left.data;
        ans += " <- " + root.data + " -> ";
        if (root.right != null)
            ans += root.right.data;

        display(root.left);
        display(root.right);
    }

    // ++++++++++++++++++++++++++++++++++ Basic ++++++++++++++++++++++++++++++++

    public static int size(Node root) {
        if (root == null)
            return 0;
        return size(root.right) + size(root.left) + 1;
    }

    public static int height(Node root) {
        if (root == null)
            return -1;
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public static int Maximum(Node root) {
        if (root == null)
            return Integer.MIN_VALUE;
        return Math.max(Maximum(root.left), Math.max(Maximum(root.right), root.data));
    }

    public static int Minimum(Node root) {
        if (root == null)
            return Integer.MAX_VALUE;
        return Math.min(Maximum(root.left), Math.min(Maximum(root.right), root.data));
    }

    public static int Minimum_02(Node root) {
        if (root == null)
            return Integer.MAX_VALUE;
        int min = root.data;
        if (root.left != null)
            min = Math.min(min, Minimum_02(root.left));
        if (root.right != null)
            min = Math.min(min, Minimum_02(root.right));
        return min;
    }

    public static boolean find(Node root, int val) {
        if (root == null)
            return false;
        if (root.data == val)
            return true;
        return find(root.right, val) || find(root.left, val);

    }

    // Traversal.============================================================

    public static void preOrder(Node node) {
        if (node == null)
            return;

        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void inOrder(Node node) {
        if (node == null)
            return;

        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    public static void postOrder(Node node) {
        if (node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");

    }

    public static boolean rootToNodePath_(Node root, int val, ArrayList<Node> ans) {
        if (root == null)
            return false;
        if (root.data == val) {
            ans.add(root);
            return true;
        }
        boolean res = rootToNodePath_(root.left, val, ans) || rootToNodePath_(root.left, val, ans);
        if (res)
            ans.add(root);
        return res;
    }

    public static void rootToNodePath(Node root,int data){
        ArrayList<Node> path=new ArrayList<>();
        rootToNodePath_(root,data,path);
        for(Node n: path){
            System.out.print(n.data + " -> ");
        }
    }

    public static ArrayList<Node> roottoNode_02(root,val,)
}