import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class L00 {
    public static void main(String[] args) {
        int[] arr = { 10, 20, 40, -1, -1, 50, 80, -1, -1, 90, -1, -1, 30, 60, 100, -1, -1, -1, 70, 110, -1, -1, 120, -1,
                -1 };
        Node root = constructTree(arr);
        // display(root);
        rightView(root);
        // DiaPair ab = diameter_02(root);
        // diameter_03(root);
        // System.out.println(fdiameter);
        // System.out.println(ab.dmtr);
    }

    public static class Node {
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

    public static void rootToNodePath(Node root, int data) {
        ArrayList<Node> path = new ArrayList<>();
        rootToNodePath_(root, data, path);
        for (Node n : path) {
            System.out.print(n.data + " -> ");
        }
    }

    public static ArrayList<Node> roottoNode_02(Node root, int val) {
        if (root == null)
            return new ArrayList<>();
        if (root.data == val) {
            ArrayList<Node> base = new ArrayList<>();
            base.add(root);
            return base;
        }
        ArrayList<Node> lft = roottoNode_02(root.left, val);
        if (lft.size() != 0) {
            lft.add(root);
            return lft;
        }
        ArrayList<Node> right = roottoNode_02(root.right, val);
        if (right.size() != 0) {
            right.add(root);
            return right;
        }
        return new ArrayList<>();
    }

    public static Node lowestCommonAncestor(Node root, int p, int q) {
        ArrayList<Node> first = new ArrayList<>();
        ArrayList<Node> second = new ArrayList<>();
        rootToNodePath_(root, p, first);
        rootToNodePath_(root, q, second);
        Node prev = null;
        int i = first.size() - 1;
        int j = second.size() - 1;
        while (i >= 0 && j >= 0) {
            if (first.get(i).data != second.get(j).data)
                break;
            prev = first.get(i);
            i--;
            j--;

        }
        return prev;
    }

    static Node LCA_node = null;

    public static boolean lowestCommonAncestor_02(Node root, int p, int q) {
        if (root == null)
            return false;
        boolean selfDone = false;
        if (root.data == p || root.data == q)
            selfDone = true;
        boolean leftDone = lowestCommonAncestor_02(root.left, p, q);
        boolean rightDone = lowestCommonAncestor_02(root.right, p, q);
        if ((selfDone && leftDone) || (selfDone && rightDone) || (rightDone && leftDone))
            LCA_node = root;
        return selfDone || leftDone || rightDone;
    }

    public static void Kdown(Node root, int level, Node blockednode) {
        if (root == null || root == blockednode)
            return;
        if (level == 0) {
            System.out.println(root.data);
            return;
        }
        Kdown(root.left, level - 1, blockednode);
        Kdown(root.right, level - 1, blockednode);
    }

    public static void allNodeKaway(Node root, int target, int K) {
        ArrayList<Node> roottnode = new ArrayList<>();
        rootToNodePath_(root, target, roottnode);
        Node blockNode = null;
        for (int i = 0; i < roottnode.size(); i++) {
            if (K - i < 0)
                break;
            Kdown(root, K - i, blockNode);
            blockNode = roottnode.get(i);
        }
    }

    public static int diameter_01(Node node) {
        if (node == null)
            return 0;

        int ld = diameter_01(node.left);
        int rd = diameter_01(node.right);

        int lh = height(node.left);
        int rh = height(node.right);

        int myDia = lh + rh + 2;
        return Math.max(Math.max(ld, rd), myDia);
    }

    public static class DiaPair {
        int ht = 0;
        int dmtr = 0;

        DiaPair(int height, int diameter) {
            this.ht = height;
            this.dmtr = diameter;
        }
    }

    public static DiaPair diameter_02(Node root) {
        if (root == null)
            return new DiaPair(-1, 0);
        DiaPair lft = diameter_02(root.left);
        DiaPair rt = diameter_02(root.right);
        DiaPair myPair = new DiaPair(-1, 0);
        myPair.ht = Math.max(lft.ht, rt.ht) + 1;
        myPair.dmtr = Math.max(Math.max(lft.dmtr, rt.dmtr), lft.ht + rt.ht + 2);
        return myPair;
    }

    static int fdiameter = 0;

    // function will act as height fxn but static will ensure highest diameter
    public static int diameter_03(Node root) {
        if (root == null)
            return -1;
        int lftht = diameter_03(root.left);
        int rgtht = diameter_03(root.right);
        fdiameter = Math.max(fdiameter, lftht + rgtht + 2);
        return Math.max(lftht, rgtht) + 1;
    }

    // +++++++++++++++++++++++=====levelorder
    // series=====++++++++++++++++++++++++++++++++++++
    public static void levelOrder_00(Node root) {
        LinkedList<Node> pQueue = new LinkedList<>();
        // LinkedList<Node> sQueue = new LinkedList<>();
        int level = 0;
        pQueue.addLast(root);
        while (pQueue.size() != 0) {
            Node frnt = pQueue.removeFirst();
            System.out.print(frnt.data + " ");
            if (frnt.left != null)
                pQueue.addLast(frnt.left);
            if (frnt.right != null)
                pQueue.addLast(frnt.right);
        }
    }

    public static void levelOrder_01(Node root) {
        LinkedList<Node> pQueue = new LinkedList<>();
        LinkedList<Node> sQueue = new LinkedList<>();
        int level = 0;
        pQueue.addLast(root);
        System.out.print("Level: " + level + " -> ");
        while (pQueue.size() != 0) {
            Node frnt = pQueue.removeFirst();
            System.out.print(frnt.data + " ");
            if (frnt.left != null)
                sQueue.addLast(frnt.left);
            if (frnt.right != null)
                sQueue.addLast(frnt.right);
            if (pQueue.size() == 0 && sQueue.size() != 0) {
                LinkedList<Node> temp = sQueue;
                sQueue = pQueue;
                pQueue = temp;
                level++;
                System.out.println();
                System.out.print("Level: " + level + " -> ");
            }
        }
    }

    public static void levelOrder_02(Node root) {
        LinkedList<Node> temp = new LinkedList<>();
        temp.addLast(root);
        temp.addLast(null);
        int level = 0;
        System.out.print("Level-- " + level + " ");
        while (temp.size() != 1) {
            Node frnt = temp.removeFirst();
            System.out.print(frnt.data + " ");
            if (frnt.left != null)
                temp.addLast(frnt.left);
            if (frnt.right != null)
                temp.addLast(frnt.right);
            if (temp.peek() == null && temp.size() != 1) {
                temp.removeFirst();
                temp.addLast(null);
                System.out.println();
                level++;
                System.out.print("Level-- " + level + " ");
            }
        }
    }

    public static void levelOrder_03(Node root) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(root);
        int level = 0;
        while (que.size() != 0) {
            int siz = que.size();
            System.out.print("level-" + level + "-> ");
            while (siz-- > 0) {
                Node frnt = que.removeFirst();
                System.out.print(frnt.data + " ");
                if (frnt.left != null)
                    que.addLast(frnt.left);
                if (frnt.right != null)
                    que.addLast(frnt.right);
            }
            level++;
            System.out.println();
        }
    }

    // +++++++++++++++++++++++++++++++=====view=====+++++++++++++++++++++++++++++++++++++++++++
    public static void leftView(Node root) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(root);
        while (que.size() != 0) {
            int siz = que.size();
            System.out.print(que.peek().data + " ");
            while (siz-- > 0) {
                Node frnt = que.removeFirst();
                // System.out.print(frnt.data + " ");
                if (frnt.left != null)
                    que.addLast(frnt.left);
                if (frnt.right != null)
                    que.addLast(frnt.right);
            }
            System.out.println();
        }
    }

    public static void rightView(Node root) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(root);
        Node prev = null;
        while (que.size() != 0) {
            int siz = que.size();
            while (siz-- > 0) {
                Node frnt = que.removeFirst();
                // System.out.print(frnt.data + " ");
                if (frnt.left != null)
                    que.addLast(frnt.left);
                if (frnt.right != null)
                    que.addLast(frnt.right);
                prev = frnt;
            }
            System.out.print(prev.data + " ");
            System.out.println();
        }
    }

}