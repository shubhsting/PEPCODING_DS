package classcodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class L00 {
    public static void main(String[] args) {
        int[] arr = { 10, 20, 40, -1, -1, 50, 80, -1, -1, 90, -1, -1, 30, 60, 100, -1, -1, -1, 70, 110, -1, -1, 120, -1,
                -1 };
        Node root = constructTree(arr);
        // display(root);
        // rightView(root);
        // verticalView(root);
        // VerticalOrderSum(root);
        // bottomView(root);
        // topView(root);
        // diagonal_print(root);
        // diagonal_sum(root);
        set2(root);
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

    // helper function for vertical view
    static int leftMinValue = Integer.MAX_VALUE;
    static int rightMaxValue = Integer.MIN_VALUE;

    public static void width(Node root, int level) {
        if (root == null)
            return;
        leftMinValue = Math.min(leftMinValue, level);
        rightMaxValue = Math.max(rightMaxValue, level);
        width(root.left, level - 1);
        width(root.right, level + 1);
    }

    public static class pairVO {
        Node node = null;
        int vl = 0;

        pairVO(Node nd, int vlevel) {
            this.node = nd;
            this.vl = vlevel;
        }
    }

    public static void verticalView(Node root) {
        width(root, 0);
        int num = rightMaxValue - leftMinValue + 1;
        ArrayList<Integer>[] VertView = new ArrayList[num];
        for (int i = 0; i < num; i++)
            VertView[i] = new ArrayList<>();
        LinkedList<pairVO> que = new LinkedList<>();
        que.addLast(new pairVO(root, -leftMinValue));
        while (que.size() != 0) {
            int siz = que.size();
            while (siz-- > 0) {
                pairVO rpair = que.removeFirst();
                VertView[rpair.vl].add(rpair.node.data);
                if (rpair.node.left != null)
                    que.addLast(new pairVO(rpair.node.left, rpair.vl - 1));
                if (rpair.node.right != null)
                    que.addLast(new pairVO(rpair.node.right, rpair.vl + 1));
            }
        }
        for (ArrayList<Integer> ar : VertView)
            System.out.println(ar);
        System.out.println();
    }

    public static void VerticalOrderSum(Node root) {
        width(root, 0);
        int[] arr = new int[rightMaxValue - leftMinValue + 1];
        LinkedList<pairVO> que = new LinkedList<>();
        que.addLast(new pairVO(root, -leftMinValue));
        while (que.size() != 0) {
            int siz = que.size();
            while (siz-- > 0) {
                pairVO rpair = que.removeFirst();
                arr[rpair.vl] += rpair.node.data;
                if (rpair.node.left != null)
                    que.addLast(new pairVO(rpair.node.left, rpair.vl - 1));
                if (rpair.node.right != null)
                    que.addLast(new pairVO(rpair.node.right, rpair.vl + 1));
            }
        }
        for (int val : arr)
            System.out.print(val + " ");
    }

    public static void bottomView(Node root) {
        width(root, 0);
        int[] arr = new int[rightMaxValue - leftMinValue + 1];
        LinkedList<pairVO> que = new LinkedList<>();
        que.addLast(new pairVO(root, -leftMinValue));
        while (que.size() != 0) {
            int siz = que.size();
            while (siz-- > 0) {
                pairVO rpair = que.removeFirst();
                arr[rpair.vl] = rpair.node.data;
                if (rpair.node.left != null)
                    que.addLast(new pairVO(rpair.node.left, rpair.vl - 1));
                if (rpair.node.right != null)
                    que.addLast(new pairVO(rpair.node.right, rpair.vl + 1));
            }
        }
        for (int val : arr)
            System.out.print(val + " ");
    }

    public static void topView(Node root) {
        width(root, 0);
        int[] arr = new int[rightMaxValue - leftMinValue + 1];
        Arrays.fill(arr, -1);
        LinkedList<pairVO> que = new LinkedList<>();
        que.addLast(new pairVO(root, -leftMinValue));
        while (que.size() != 0) {
            int siz = que.size();
            while (siz-- > 0) {
                pairVO rpair = que.removeFirst();
                if (arr[rpair.vl] == -1)
                    arr[rpair.vl] = rpair.node.data;
                if (rpair.node.left != null)
                    que.addLast(new pairVO(rpair.node.left, rpair.vl - 1));
                if (rpair.node.right != null)
                    que.addLast(new pairVO(rpair.node.right, rpair.vl + 1));
            }
        }
        for (int val : arr)
            System.out.print(val + " ");
    }

    static int leftMinValue_D = Integer.MAX_VALUE;

    public static void width_D(Node root, int level) {
        if (root == null)
            return;
        leftMinValue_D = Math.min(leftMinValue_D, level);
        width_D(root.left, level - 1);
        width_D(root.right, level);
    }

    public static void diagonal_print(Node root) {
        width_D(root, 0);
        int n = -leftMinValue_D + 1;
        // System.out.println(leftMinValue_D);
        ArrayList<Integer>[] fans = new ArrayList[n];
        LinkedList<pairVO> que = new LinkedList<>();
        for (int i = 0; i < n; i++)
            fans[i] = new ArrayList<>();
        que.addLast(new pairVO(root, 0 - leftMinValue_D));
        while (que.size() != 0) {
            int siz = que.size();
            while (siz-- > 0) {
                pairVO rvtxDo = que.removeFirst();
                fans[rvtxDo.vl].add(rvtxDo.node.data);
                if (rvtxDo.node.left != null)
                    que.addLast(new pairVO(rvtxDo.node.left, rvtxDo.vl - 1));
                if (rvtxDo.node.right != null)
                    que.addLast(new pairVO(rvtxDo.node.right, rvtxDo.vl));
            }
        }
        for (ArrayList<Integer> ar : fans)
            System.out.println(ar);
        System.out.println();
    }

    public static void diagonal_sum(Node root) {
        width_D(root, 0);
        int n = -leftMinValue_D + 1;
        int[] fans = new int[n];
        LinkedList<pairVO> que = new LinkedList<>();
        que.addLast(new pairVO(root, 0 - leftMinValue_D));
        while (que.size() != 0) {
            int siz = que.size();
            while (siz-- > 0) {
                pairVO rvtxDo = que.removeFirst();
                fans[rvtxDo.vl] += rvtxDo.node.data;
                if (rvtxDo.node.left != null)
                    que.addLast(new pairVO(rvtxDo.node.left, rvtxDo.vl - 1));
                if (rvtxDo.node.right != null)
                    que.addLast(new pairVO(rvtxDo.node.right, rvtxDo.vl));
            }
        }
        for (Integer ar : fans)
            System.out.print(ar + " ");
        // System.out.println();
    }

    static Node DLLhead = null;
    static Node DLLprev = null;

    // convert into DLL
    public static void convertDLL(Node root) {
        if (root == null)
            return;
        convertDLL(root.left);
        if (DLLhead == null) {
            DLLhead = root;
            // DLLprev = root;
        } else {
            root.left = DLLprev;
            DLLprev.right = root;
        }
        DLLprev = root;
        convertDLL(root.right);
    }

    public static void set2(Node node) {
        convertDLL(node);
        while (DLLhead != null) {
            System.out.print(DLLhead.data + " ");
            DLLhead = DLLhead.right;
        }
    }

    public static class allSolution {
        int height = 0;
        int size = 0;
        boolean find = false;
        Node pred = null;
        Node succ = null;
        Node prev = null;
    }

    public static void full(Node root, int data, int level, allSolution pair) {
        if (root == null)
            return;
        pair.size++;
        pair.height = Math.max(pair.height, level);
        full(root.left, data, level + 1, pair);
        full(root.right, data, level + 1, pair);
    }
}
