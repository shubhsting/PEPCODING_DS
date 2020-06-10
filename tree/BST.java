import java.lang.reflect.Array;
import java.util.ArrayList;

public class BST {
    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130 };

        // display(constructTree(arr, 0, arr.length - 1));
        Node root = constructTree(arr, 0, arr.length - 1);
        // System.out.println(greatestBST(root));
        ArrayList<Integer> ans = new ArrayList<>();
        inRange_01(root, 10, 65, ans);
        System.out.println(ans);
    }

    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    public static Node constructTree(int[] arr, int left, int right) {
        if (left > right)
            return null;
        int mid = (left + right) >> 1;
        Node curr = new Node(arr[mid]);
        curr.left = constructTree(arr, left, mid - 1);
        curr.right = constructTree(arr, mid + 1, right);
        return curr;
    }

    public static void display(Node root) {
        if (root == null)
            return;
        String ans = "";
        if (root.left != null)
            ans += root.left.data;
        ans += " <- " + root.data + " -> ";

        if (root.right != null)
            ans += root.right.data;
        System.out.println(ans);
        display(root.left);
        display(root.right);
    }

    public static boolean find(Node root, int data) {
        Node curr = root;
        while (curr != null) {
            if (curr.data == data)
                return true;
            else if (data > curr.data)
                curr = curr.right;
            else
                curr = curr.left;
        }
        return false;
    }

    public static int greatestBST(Node root) {
        Node curr = root;
        while (curr.right != null)
            curr = curr.right;
        return curr.data;
    }

    public static int smallestBST(Node root) {
        Node curr = root;
        while (curr.left != null)
            curr = curr.left;
        return curr.data;
    }

    public static ArrayList<Integer> inRange(Node root, int x, int y) {
        if (root == null)
            return new ArrayList<>();
        ArrayList<Integer> myAns = new ArrayList<>();

        ArrayList<Integer> lft = inRange(root.left, x, y);
        for (Integer e : lft)
            myAns.add(e);
        if (root.data >= x && root.data <= y)
            myAns.add(root.data);
        ArrayList<Integer> rgt = inRange(root.right, x, y);

        for (Integer m : rgt)
            myAns.add(m);
        return myAns;
    }

    public static int LCA(Node root, int x, int y) {
        Node curr = root;
        while (curr != null) {
            if (x < curr.data && y < curr.data)
                curr = curr.left;
            else if (x > curr.data && y > curr.data)
                curr = curr.right;
            else {
                if (find(root, x) && find(root, y))
                    return curr.data;
            }
        }
        return -1;
    }

    public static void inRange_01(Node root, int x, int y, ArrayList<Integer> ans) {
        if (root == null)
            return;
        inRange_01(root.left, x, y, ans);
        if (root.data >= x && root.data <= y)
            ans.add(root.data);
        inRange_01(root.right, x, y, ans);
    }

    // node pr pachne tak dhyaan dhyaan se predecessor aur successor decide kro uske
    // baad to fix hai
    public static void predndsuccessor(Node root, int data) {
        Node curr = root;
        Node pred = null;
        Node successor = null;
        while (curr != null) {
            if (curr.data == data) {
                if (curr.left == null)
                    System.out.println("Predecessor is: " + (pred != null ? pred.data : -1));
                else {
                    pred = curr.left;
                    while (pred.right != null)
                        pred = pred.right;
                    System.out.println("Predecessor is:" + pred.data);
                }

                if (curr.right == null)
                    System.out.println("Successor is: " + (successor != null ? successor.data : -1));
                else {
                    successor = curr.right;
                    while (successor.left != null)
                        successor = successor.left;
                    System.out.println("Successor is:" + successor.data);
                }
                break;

            } else if (data < curr.data) {
                successor = curr;
                curr = curr.left;
            } else {
                pred = curr;
                curr = curr.right;
            }
        }
    }

    static int idx = 0;

    // bstFromPreorder_(preorder,Integer.MIN_VALUE,Integer.MAX_VALUE);
    public static Node constructTreefromPreOrder(int[] preOrder, int lb, int rb) {
        if (idx >= preOrder.length || preOrder[idx] > rb || preOrder[idx] < lb)
            return null;

        Node root = new Node(preOrder[idx]);
        idx++;
        root.left = constructTreefromPreOrder(preOrder, lb, root.data);
        root.right = constructTreefromPreOrder(preOrder, root.data, rb);
        return root;
    }

    public static int heightofBST(int[] preorder, int lb, int rb) {
        if (idx >= preorder.length || preorder[idx] > rb || preorder[idx] < lb)
            return -1;
        int ele = preorder[idx];
        idx++;
        int lh = heightofBST(preorder, lb, ele);
        int rh = heightofBST(preorder, ele, rb);
        return Math.max(rh, lh) + 1;
    }


    

    public static Node addData(Node root, int data) {
        if (root == null)
            return new Node(data);
        if (data < root.data)
            root.left = addData(root.left, data);
        if (data > root.data)
            root.right = addData(root.right, data);
        return root;
    }

    public static Node removeData(Node root, int data) {
        if (root == null)
            return null;
        if (data < root.data)
            root.left = removeData(root.left, data);
        else if (data > root.data)
            root.right = removeData(root.right, data);
        else {
            if (root.right == null || root.left == null)
                return root.right == null ? root.left : root.right;
            int left_max = greatestBST(root.left);
            root.data = left_max;
            root.left = removeData(root.left, left_max);
        }
        return root;
    }
}