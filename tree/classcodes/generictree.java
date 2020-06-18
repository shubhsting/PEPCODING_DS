package classcodes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class generictree {
    public static class Node {
        int data = 0;
        ArrayList<Node> childs = new ArrayList<>();

        Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 100, -1, 110, -1, -1, 90, -1, -1, 40, 120, 140, -1,
                150, -1, -1, -1, -1 };
        Node root = createGTtree(arr);

        display(root);
        // levelOrder(root);
        linearize(root);
        // flattern(root);
        display(root);
    }

    public static Node createGTtree(int[] arr) {
        Stack<Node> st = new Stack<>();
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == -1) {
                Node temp = st.pop();
                st.peek().childs.add(temp);
            } else {
                Node nod = new Node(arr[i]);
                st.push(nod);
            }
        }
        return st.pop();
    }

    public static void preorder(Node root) {
        System.out.println(root.data);
        for (Node e : root.childs)
            preorder(e);
    }

    public static void display(Node root) {
        String str = "";
        str += root.data + " => ";
        for (Node e : root.childs)
            str += e.data + " ";

        System.out.println(str);
        for (Node nd : root.childs)
            display(nd);
    }

    public static int height(Node root) {
        int height = 0;
        for (Node e : root.childs)
            height = Math.max(height, height(e));
        return height + 1;
    }

    public static int size(Node root) {
        int size = 0;
        for (Node e : root.childs)
            size += size(e);
        return size + 1;
    }

    public static boolean find(Node root, int data) {
        if (root.data == data)
            return true;
        boolean temp = false;
        for (Node e : root.childs)
            temp = temp || find(e, data);
        return temp;
    }

    public static boolean rootToNode(Node root, ArrayList<Integer> ans, int data) {
        if (root.data == data)
            return true;
        boolean res = false;
        ans.add(root.data);
        for (Node e : root.childs)
            rootToNode(e, ans, data);
        if (!res)
            ans.remove(ans.size() - 1);
        return res;
    }

    public static void levelOrder(Node root) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(root);
        while (que.size() != 0) {
            int siz = que.size();
            while (siz-- > 0) {
                Node rnode = que.removeFirst();
                System.out.print(rnode.data + " ");
                for (Node e : rnode.childs)
                    que.addLast(e);
            }
            System.out.println();
        }
    }

    public static Node linearize(Node root) {
        if (root.childs.size() == 0)
            return root;

        int n = root.childs.size();
        Node lastTail = linearize(root.childs.get(n - 1));
        for (int i = n - 2; i >= 0; i--) {
            Node secondlastTail = linearize(root.childs.get(i));
            secondlastTail.childs.add(root.childs.get(i + 1));
            root.childs.remove(root.childs.size() - 1);
        }
        return lastTail;
    }

    // attach all elemnts to root directly
    public static void flattern(Node root) {
        ArrayList<Node> temp = new ArrayList<>();
        for (Node e : root.childs) {
            flattern(e);
            temp.add(e);
            for (Node m : e.childs)
                temp.add(m);

            e.childs.clear();
        }
        root.childs.clear();
        root.childs = temp;
    }


    
}