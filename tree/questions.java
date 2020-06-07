import java.util.*;

// import javax.swing.tree.TreeNode;

// import javax.swing.tree.TreeNode;

public class questions {
    public static void main(String[] args) {

    }

    double prev = -1e12;

    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        if (!isValidBST(root.left))
            return false;
        if (prev >= root.val)
            return false;
        prev = root.val;
        if (!isValidBST(root.right))
            return false;

        return true;
    }

// ===================leetcode 993===================
 class Pair{
        TreeNode parent;
        int level;
        
        Pair(TreeNode parent, int level){
            this.parent = parent;
            this.level = level;
        }
    }
    public boolean isCousins(TreeNode root, int x, int y) {
        Pair px=isCousins_(root,null,x,0);
        Pair py=isCousins_(root,null,y,0);
        return px.parent!=py.parent && px.level==py.level;
    }
     public Pair isCousins_(TreeNode root,TreeNode par, int req,int level) {
        if(root==null) return null;
         if(root.val==req) return new Pair(par,level);
        Pair lft=isCousins_(root.left,root,req,level+1);
        Pair rt=isCousins_(root.right,root,req,level+1);
         return lft==null?rt:lft;      
    }




    // =================leetcode 684==================

    static int[] par;
    static int[] setSize;

    public int findPar(int vtx) {
        if (par[vtx] == vtx)
            return vtx;
        return findPar(par[vtx]);
    }

    public void mergeset(int l1, int l2) {
        if (setSize[l2] > setSize[l1]) {
            par[l2] = l1;
            setSize[l1] += l2;
        } else {
            par[l1] = l2;
            setSize[l2] += l1;
        }
    }

    public int[] findRedundantConnection(int[][] edges) {
        par = new int[edges.length + 1];
        setSize = new int[edges.length + 1];
        for (int i = 0; i < edges.length; i++) {
            par[i] = i;
            setSize[i] = 1;
        }
        for (int[] arr : edges) {
            int p1 = findPar(arr[0]);
            int p2 = findPar(arr[1]);
            if (p1 != p2)
                mergeset(p1, p2);

            else
                return arr;
        }
        return new int[0];
    }

    // ================= leetcode 589=====================
    public List<Integer> preorder(TreeNode_G root) {
        List<Integer> ans = new ArrayList<>();
        preorder_(root, ans);
        return ans;

    }

    public void preorder_(TreeNode_G root, List<Integer> ans) {
        if (root == null)
            return;
        ans.add(root.val);
        for (TreeNode_G e : root.children)
            preorder_(e, ans);
    }

    // =====================leetcode 897=====================

    public TreeNode increasingBST(TreeNode root) {
        if (root == null)
            return null;
        LinkedList<TreeNode> que = new LinkedList<>();
        inorder(que, root);
        TreeNode temp = que.removeFirst();
        TreeNode nroot = temp;
        while (!que.isEmpty()) {
            temp.right = que.removeFirst();
            temp.left = null;
            temp = temp.right;
        }
        return nroot;
    }

    public static void inorder(LinkedList<TreeNode> ans, TreeNode root) {
        if (root == null)
            return;
        inorder(ans, root.left);
        ans.addLast(root);
        inorder(ans, root.right);
    }

    // leetcode 965=======================
    public boolean isUnivalTree(TreeNode root) {
        if (root == null)
            return true;

        if (root.left != null && root.val != root.left.val)
            return false;

        if (root.right != null && root.val != root.right.val)
            return false;

        return isUnivalTree(root.right) && isUnivalTree(root.left);
    }

    // ==================== leetcode 1022=================

    int fans = 0;

    public int sumRootToLeaf(TreeNode root) {
        sumRootToLeaf_(root, 0);
        return fans;
    }
 public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }
     public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.val = data;
        }
    }
    public static class TreeNode_G{
        int val;
       TreeNode_G[] children;

        TreeNode_G(int data) {
            this.val = data;
        }
    }
    public void sumRootToLeaf_(TreeNode root, int currsum) {
        if (root == null)
            return;
        currsum = (currsum << 1) | root.val;
        if (root.left == null && root.right == null) {
            fans += currsum;
            return;
        }

        sumRootToLeaf_(root.left, currsum);
        sumRootToLeaf_(root.right, currsum);
    }

    // ===============leetcode 111==================
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        int lft = minDepth(root.left);
        int rt = minDepth(root.right);
        if (lft == 0)
            lft = Integer.MAX_VALUE;
        if (rt == 0)
            rt = Integer.MAX_VALUE;
        return Math.min(lft, rt) + 1;
    }

    // ============leetcode 872====================

    void preOrder(TreeNode root, ArrayList<Integer> list) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            list.add(root.val);
            return;
        }
        preOrder(root.left, list);
        preOrder(root.right, list);
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        ArrayList<Integer> leaf1 = new ArrayList<Integer>();
        ArrayList<Integer> leaf2 = new ArrayList<Integer>();
        preOrder(root1, leaf1);
        preOrder(root2, leaf2);
        if (leaf1.size() != leaf2.size())
            return false;
        Iterator<Integer> it1 = leaf1.iterator();
        Iterator<Integer> it2 = leaf2.iterator();
        while (it1.hasNext()) {
            if (it1.next() != it2.next())
                return false;
        }
        return true;
    }

    // ===============leetcode 617==============
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null)
            return null;

        int far = 0;
        if (t1 != null && t2 != null) {
            far = t1.val + t2.val;
            TreeNode my = new TreeNode(far);
            my.left = mergeTrees(t1.left, t2.left);
            my.right = mergeTrees(t1.right, t2.right);
            return my;
        } else if (t1 == null) {
            far = t2.val;
            TreeNode my = new TreeNode(far);
            my.left = mergeTrees(null, t2.left);
            my.right = mergeTrees(null, t2.right);
            return my;
        } else {
            far = t1.val;
            TreeNode my = new TreeNode(far);
            my.left = mergeTrees(t1.left, null);
            my.right = mergeTrees(t1.right, null);
            return my;
        }
    }

    // alternate solution
    public TreeNode mergeTrees_01(TreeNode t1, TreeNode t2) {
        if (t1 == null)
            return t2;
        if (t2 == null)
            return t1;
        t1.val += t2.val;
        t1.left = mergeTrees_01(t1.left, t2.left);
        t1.right = mergeTrees_01(t1.right, t2.right);
        return t1;
    }

    // leetcode 669======================
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null)
            return null;

        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);

        return root.val < L ? root.right : root.val > R ? root.left : root;
    }

    // ============leetcode 637==================
    public List<Double> averageOfLevels(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        List<Double> ans = new ArrayList<>();
        que.add(root);
        while (que.size() != 0) {
            int siz = que.size();
            double fs = (double) siz;
            double tot = 0;
            while (siz-- > 0) {
                TreeNode node = que.removeFirst();
                tot += node.val;
                if (node.left != null)
                    que.addLast(node.left);
                if (node.right != null)
                    que.addLast(node.right);
            }
            ans.add(tot / fs);
        }
        return ans;
    }
}