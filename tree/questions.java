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
}