public class Q211 {
    public class Node {
        Node[] childs;
        int WordEnd = 0;
        String word = "";

        Node() {
            childs = new Node[26];
            WordEnd = 0;
            word = "";
        }
    }

    Node root = null;

    public WordDictionary() {
      root=new Node();  
    }

    public void addWord(String word) {
        Node curr = root;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            int idx = str.charAt(i) - 'a';
            if (curr.childs[idx] == null)
                curr.childs[idx] = new Node();
            curr = curr.childs[idx];
        }
        curr.WordEnd++;
    }

    public boolean search_(Node node, int si, String word) {
        if (node == null)
            return false;
        if (si == word.length())
            return node.WordEnd != 0;
        char ch = word.charAt(si);
        boolean res = false;
        if (ch == '.') {
            for (int i = 0; i < 26; i++)
                if (node.childs[i] != null)
                    res = res || search_(node.childs[i], si + 1, word);
        } else {
            res = res || search_(node.childs[ch - 'a'], si + 1, word);

        }
        return res;
    }

    public boolean search(String word) {
        return search_(root, 0, word);
    }
}