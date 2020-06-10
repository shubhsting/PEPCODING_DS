public class AVL {
    public static class Node {
        int data = 0;
        Node left = null;
        Node right = null;
        int height = 0;
        int balance = 0;

        public Node(int data) {
            this.data = data;
        }

        // Node() {
        // }
    }

    public static void main(String[] args) {
        Node root = null;
        int[] arr = { 100, 40, 20, 70,80};
        for (int i = 0; i < arr.length; i++) {
            root = addData(root, arr[i]);
            display(root);
            System.out.println();
            System.out.println();
            System.out.println();
        }

        // display(root);
    }

    // =====================AVL utilities ============================
    public static void updateHeightAndBalance(Node root) {
        int lh = -1;
        int rh = -1;
        if (root.left != null)
            lh = root.left.height;
        if (root.right != null)
            rh = root.right.height;
        root.height = Math.max(lh, rh) + 1;
        root.balance = lh - rh;
    }

    public static Node ll(Node A) {
        Node B = A.left;
        Node bkaright = B.right;
        B.right = A;
        A.left = bkaright;
        updateHeightAndBalance(A);
        updateHeightAndBalance(B);
        return B;
    }

    public static Node rr(Node A) {
        Node B = A.right;
        Node bkaleft = B.left;
        B.left = A;
        A.right = bkaleft;
        updateHeightAndBalance(A);
        updateHeightAndBalance(B);
        return B;
    }

    public static Node getRotation(Node root) {
        updateHeightAndBalance(root);
        if (root.balance == 2) { //ll lr
            if (root.left.balance == 1) { // ll
                return ll(root);
            } else { // lr
                root.left = rr(root.left);
                return ll(root);
            }
        } else if (root.balance == -2) { // rr rl
            if (root.right.balance == -1) { // rr
                return rr(root);
            } else { // rl
                root.right = ll(root.right);
                return rr(root);
            }
        }
        return root;
    }

    // =====================BST===============================

    public static Node constructBST(int[] arr, int si, int ei) {
        if (si > ei)
            return null;
        int mid = (si + ei) / 2;
        Node root = new Node(arr[mid]);
        root.left = constructBST(arr, si, mid);
        root.right = constructBST(arr, mid, ei);
        updateHeightAndBalance(root);
        return root;
    }

    public static void display(Node root) // O(n)
    {
        if (root == null)
            return;

        String str = "";
        str += ((root.left != null) ? root.left.data + "[" + root.left.balance + "," + root.left.height + "]" : ".");
        str += " <- " + root.data + "[" + root.balance + "," + root.height + "]" + " -> ";
        str += ((root.right != null) ? root.right.data + "[" + root.right.balance + "," + root.right.height + "]"
                : ".");
        System.out.println(str);

        display(root.left);
        display(root.right);
    }

    public static Node max(Node root) {
        while (root.right != null)
            root = root.right;
        return root;
    }

    public static Node addData(Node root, int data) {
        if (root == null)
            return new Node(data);
        if (data < root.data)
            root.left = addData(root.left, data);
        if (data > root.data)
            root.right = addData(root.right, data);
        return getRotation(root);
    }

    public static Node removeData(Node root, int data) {
        if (root == null)
            return null;
        if (data > root.data)
            root.right = removeData(root.right, data);
        else if (data < root.data)
            root.left = removeData(root.left, data);
        else {
            if (root.left == null || root.right == null)
                return root.left == null ? root.right : root.left;
            Node leftmax = max(root.left);
            root.data = leftmax.data;
            root.left = removeData(root.left, leftmax.data);
        }
        return getRotation(root);
    }
}