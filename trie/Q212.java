public class Q212 {
    public class Node {
        Node[] childs;
        boolean WordEnd = false;
        String word = "";

        Node() {
            childs = new Node[26];
            WordEnd = false;
            word = "";
        }
    }

    Node root = null;

    public void insert(String word) {
        Node curr = root;
        int n = word.length();
        for (int i = 0; i < n; i++) {
            int idx = word.charAt(i) - 'a';
            if (curr.childs[idx] == null)
                curr.childs[idx] = new Node();
            curr = curr.childs[idx];
        }
        curr.WordEnd = true;
        curr.word = word;
    }

    int[][] dirA = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };
    ArrayList<String> ans = new ArrayList<>();

    public void dfs(int r, int c, Node node, char[][] board) {
        if (node.WordEnd) {
            node.WordEnd = false;
            ans.add(node.word);
        } 
        char ch = board[r][c];
        board[r][c] = '$';
        for (int i = 0; i < dirA.length; i++) {
            int x = r + dirA[i][0];
            int y = c + dirA[i][1];
            if (x >= 0 && y >= 0 && x < board.length && y < board[0].length && board[x][y] != '$'
                    && node.childs[board[x][y] - 'a'] != null)
                dfs(x, y, node.childs[board[x][y] - 'a'], board);
        }
        board[r][c] = ch;
    }

    public List<String> findWords(char[][] board, String[] words) {
        root = new Node();
        for (int i = 0; i < words.length; i++)
            insert(words[i]);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (root.childs[board[i][j] - 'a'] != null)
                    dfs(i, j, root.childs[board[i][j] - 'a'], board);
            }
        }

        return ans;
    }
}