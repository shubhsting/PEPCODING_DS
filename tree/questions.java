import java.util.*;

// import javax.swing.tree.TreeNode;

// import javax.swing.tree.TreeNode;

public class questions {
    public static void main(String[] args) {

    }

    long prev = -1e12;

    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        if (!isValidBST(root.left))
            return false;
        if (prev >= root.data)
            return false;
        rev = root.data;
        if (!isValidBST(root.right))
            return false;

        return true;
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
    public List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        preorder_(root, ans);
        return ans;

    }

    public void preorder_(Node root, List<Integer> ans) {
        if (root == null)
            return;
        ans.add(root.val);
        for (Node e : root.children)
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
}