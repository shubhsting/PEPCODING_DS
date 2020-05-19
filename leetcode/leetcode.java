import java.util.*;

public class leetcode {
    public static void main(String[] args) {
        // int[] wells = { 1, 2, 7 };
        int[][] pipes = { { 1, 2 }, { 3, 4 } };
        // minCostToSupplyWater(3, wells, pipes);
        System.out.println(journeyToMoon(100000, pipes));
    }

    // leetcode
    // 1376=====================================================================
    public class Edge {
        int n = 0;
        int m = 0;

        Edge(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();
        for (int i = 0; i < manager.length; i++) {
            if (manager[i] != -1)
                graph[manager[i]].add(i);
        }

        LinkedList<Edge> que = new LinkedList<>();
        que.addLast(new Edge(headID, 0));
        int fans = 0;
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {

                Edge item = que.removeFirst();
                for (int p : graph[item.n]) {
                    que.addLast(new Edge(p, item.m + informTime[item.n]));
                }
                fans = Math.max(fans, item.m);
            }

        }
        return fans;
    }
    // ===================================question
    // end===============================

    // leetcode 1034================================================================

    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int oldColor = grid[r0][c0];
        dfs(visited, grid, r0, c0, oldColor, color);
        return grid;
    }

    public void dfs(boolean[][] visited, int[][] grid, int i, int j, int color, int newColor) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || visited[i][j] || grid[i][j] != color)
            return;
        visited[i][j] = true;
        if (i == 0 || j == 0 || i == grid.length - 1 || j == grid[0].length - 1
                || checkNei(grid, i, j, visited, color)) {
            grid[i][j] = newColor;
        }
        dfs(visited, grid, i + 1, j, color, newColor);
        dfs(visited, grid, i - 1, j, color, newColor);
        dfs(visited, grid, i, j + 1, color, newColor);
        dfs(visited, grid, i, j - 1, color, newColor);
    }

    public boolean checkNei(int[][] grid, int i, int j, boolean[][] visited, int oldColor) {
        if (i + 1 >= 0 && i + 1 < grid.length && !visited[i + 1][j] && grid[i + 1][j] != oldColor) {
            return true;
        }
        if (i - 1 >= 0 && i - 1 < grid.length && !visited[i - 1][j] && grid[i - 1][j] != oldColor) {
            return true;
        }
        if (j + 1 >= 0 && j + 1 < grid[0].length && !visited[i][j + 1] && grid[i][j + 1] != oldColor) {
            return true;
        }
        if (j - 1 >= 0 && j - 1 < grid[0].length && !visited[i][j - 1] && grid[i][j - 1] != oldColor) {
            return true;
        }
        return false;
    }

    // ===================================leetcode 329
    // =================================

    public int longestIncreasingPath(int[][] arr) {

        int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        if (arr.length == 0 || arr[0].length == 0)
            return 0;
        int n = arr.length;
        int m = arr[0].length;

        int[][] indegree = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int d = 0; d < 4; d++) {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];
                    if (r >= 0 && r < n && c >= 0 && c < m && arr[r][c] > arr[i][j])
                        indegree[r][c]++;
                }
            }
        }
        LinkedList<Integer> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (indegree[i][j] == 0)
                    que.addLast(i * m + j);
            }
        }
        int length = 0;
        while (que.size() != 0) {
            int siz = que.size();
            while (siz-- > 0) {
                int temp = que.removeFirst();
                int i = temp / m;
                int j = temp % m;
                for (int d = 0; d < 4; d++) {
                    int x = i + dir[d][0];
                    int y = j + dir[d][1];
                    if (x >= 0 && x < n && y >= 0 && y < m && arr[x][y] > arr[i][j] && --indegree[x][y] == 0)
                        que.addLast(x * m + y);
                }
            }
            length++;
        }
        return length;
    }

    // ==================leetcode 22============

    class Solution {
        public List<String> generateParenthesis(int n) {
            if (n == 1) {
                List<String> ret = new ArrayList<>();
                ret.add("()");
                return ret;
            }
            List<String> myRes = new ArrayList<>();
            List<String> recRe = generateParenthesis(n - 1);
            for (String s : recRe) {
                for (int i = s.length(); i >= 0; i--) {
                    String fp = s.substring(0, i) + "()" + s.substring(i);
                    if (!myRes.contains(fp))
                        myRes.add(fp);
                }
            }
            return myRes;
        }
    }

    // ==========================leetcode 207
    // =================================================================

    public static ArrayList<Integer> khansAlgo_cf(int n, ArrayList<Integer>[] gp, int[][] prerequisites) {
        ArrayList<Integer> ans = new ArrayList<>();
        int[] indegree = new int[n];
        for (int[] arr : prerequisites)
            indegree[arr[0]]++;
        LinkedList<Integer> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0)
                que.addLast(i);
        }

        while (que.size() != 0) {
            int siz = que.size();
            while (siz-- > 0) {
                int num = que.removeFirst();
                ans.add(num);
                for (int temp : gp[num]) {
                    if (--indegree[temp] == 0)
                        que.addLast(temp);
                }
            }
        }

        return ans;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < graph.length; i++)
            graph[i] = new ArrayList<>();
        for (int[] arr : prerequisites)
            graph[arr[1]].add(arr[0]);
        ArrayList<Integer> rec = khansAlgo_cf(numCourses, graph, prerequisites);
        return rec.size() != numCourses ? false : true;
    }

    // ========================================leetcode
    // 210=================================

    public static int[] khansAlgo(int n, ArrayList<Integer>[] gp, int[][] prerequisites) {
        ArrayList<Integer> ans = new ArrayList<>();
        int[] indegree = new int[n];
        for (int[] arr : prerequisites)
            indegree[arr[0]]++;
        LinkedList<Integer> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0)
                que.addLast(i);
        }

        while (que.size() != 0) {
            int siz = que.size();
            while (siz-- > 0) {
                int num = que.removeFirst();
                ans.add(num);
                for (int temp : gp[num]) {
                    if (--indegree[temp] == 0)
                        que.addLast(temp);
                }
            }
        }
        int[] apr = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++)
            apr[i] = ans.get(i);

        return apr;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < graph.length; i++)
            graph[i] = new ArrayList<>();
        for (int[] arr : prerequisites)
            graph[arr[1]].add(arr[0]);
        int[] rec = khansAlgo(numCourses, graph, prerequisites);
        return rec.length != numCourses ? new int[0] : rec;
    }

    // ========================leetcode
    // 684===========================================

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

    // ========================leetcode 547=======================================

    public int findCircleNum(int[][] M) {
        par = new int[M.length];
        setSize = new int[M.length];
        for (int i = 0; i < M.length; i++) {
            par[i] = i;
            setSize[i] = 1;
        }
        int n = M.length;
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] == 1 && i != j) {
                    int p1 = findPar(i);
                    int p2 = findPar(j);
                    if (p1 != p2) {
                        n--;
                        mergeset(p1, p2);
                    }
                }

            }
        }
        return n;
    }

    // leetcode 200=============================

    public int numIslands(char[][] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {

                if (arr[i][j] == '1') {
                    count++;
                    dfs_noi(arr, i, j);
                }
            }
        }
        return count;
    }

    public void dfs_noi(char[][] arr, int r, int c) {
        if (arr[r][c] != '1') {
            return;
        }
        arr[r][c] = '0';
        if (r - 1 >= 0)
            dfs_noi(arr, r - 1, c);
        if (c - 1 >= 0)
            dfs_noi(arr, r, c - 1);
        if (r + 1 < arr.length)
            dfs_noi(arr, r + 1, c);
        if (c + 1 < arr[0].length)
            dfs_noi(arr, r, c + 1);
    }

    // leetcode 695======================================

    public int maxAreaOfIsland(int[][] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {

                if (arr[i][j] == 1) {

                    int temp = dfs(arr, i, j);
                    ;
                    if (temp > max)
                        max = temp;
                }
            }
        }
        return max;
    }

    public int dfs(int[][] arr, int r, int c) {
        if (arr[r][c] != 1) {
            return 0;
        }

        int count = 0;
        arr[r][c] = 0;

        if (r - 1 >= 0)
            count += dfs(arr, r - 1, c);
        if (c - 1 >= 0)
            count += dfs(arr, r, c - 1);
        if (r + 1 < arr.length)
            count += dfs(arr, r + 1, c);
        if (c + 1 < arr[0].length)
            count += dfs(arr, r, c + 1);

        return count + 1;
    }

    // leetcode 130==========================================

    public void solve(char[][] arr) {
        if (arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr[0].length; i++) {
            if (arr[0][i] == 'O') {
                dfs(arr, 0, i);
            }
            if (arr[arr.length - 1][i] == 'O') {
                dfs(arr, arr.length - 1, i);
            }
        }
        for (int j = 0; j < arr.length; j++) {
            if (arr[j][0] == 'O') {
                dfs(arr, j, 0);
            }
            if (arr[j][arr[0].length - 1] == 'O') {
                dfs(arr, j, arr[0].length - 1);
            }
        }

        for (int m = 0; m < arr.length; m++) {
            for (int n = 0; n < arr[0].length; n++) {
                if (arr[m][n] == 'O')
                    arr[m][n] = 'X';

                if (arr[m][n] == '#')
                    arr[m][n] = 'O';
            }
        }
    }

    public void dfs(char[][] arr, int row, int col) {
        if (arr[row][col] != 'O')
            return;
        arr[row][col] = '#';
        if (row - 1 >= 0)
            dfs(arr, row - 1, col);

        if (col - 1 >= 0)
            dfs(arr, row, col - 1);

        if (row + 1 < arr.length)
            dfs(arr, row + 1, col);

        if (col + 1 < arr[0].length)
            dfs(arr, row, col + 1);
    }

    // leetcode 463==========================

    public int islandPerimeter(int[][] arr) {
        int noofone = 0;
        int nneb = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 1) {
                    noofone += 1;
                    if (i + 1 < arr.length && arr[i + 1][j] == 1)
                        nneb += 1;

                    if (j + 1 < arr[0].length && arr[i][j + 1] == 1)
                        nneb += 1;
                }
            }
        }
        return (noofone * 4 - nneb * 2);
    }

    // leetcode 1091=============================================
    public int shortestPathBinaryMatrix(int[][] grid) {
        int[][] dirA = { { -1, -1 }, { -1, 0 }, { 0, -1 }, { -1, 1 }, { 1, -1 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };

        int n = grid.length;
        int m = grid[0].length;
        if (m == 0 || n == 0) {
            return -1;
        }
        if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1)
            return -1;
        if (m == 1)
            grid[0][0] = 1;
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(0);
        int level = 1;
        while (que.size() != 0) {
            int siz = que.size();
            while (siz-- > 0) {
                int temp = que.removeFirst();
                int row = temp / m;
                int col = temp % m;
                if (row == n - 1 && col == m - 1) {
                    return level;
                }
                for (int i = 0; i < 8; i++) {
                    int r = row + dirA[i][0];
                    int c = col + dirA[i][1];
                    if (r >= 0 && r < n && c >= 0 && c < m && grid[r][c] == 0) {

                        grid[r][c] = 1;
                        que.addLast(r * m + c);
                    }
                }
            }
            level++;
        }
        return -1;
    }

    // leetcode 994 amazon
    // imp===========================================================

    public int orangesRotting(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        if (m == 0 || n == 0)
            return -1;
        int[][] dirA = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
        LinkedList<Integer> que = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2)
                    que.addLast(i * m + j);
            }
        }
        int level = 0;
        while (que.size() != 0) {
            int siz = que.size();
            while (siz-- > 0) {
                int temp = que.removeFirst();
                int row = temp / m;
                int col = temp % m;
                for (int mp = 0; mp < 4; mp++) {
                    int r = row + dirA[mp][0];
                    int c = col + dirA[mp][1];
                    if (r >= 0 && r < n && c >= 0 && c < m && grid[r][c] == 1) {
                        que.addLast(r * m + c);
                        grid[r][c] = 2;
                    }
                }
            }
            level++;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1)
                    return -1;
            }
        }
        if (level == 0)
            return 0;
        return level - 1;
    }

    // ===================leetcode 1061 ================
    public String smallestEquivalentString(String A, String B, String S) {
        for (int i = 0; i < 26; i++)
            par[i] = i;
        for (int i = 0; i < A.length(); i++) {
            int a = A.charAt(i) - 'a';
            int b = B.charAt(i) - 'a';
            int para = findPar(a);
            int parb = findPar(b);
            // if (para != parb) {
            // if (a < b)
            // par[b] = par[a];
            // else
            // par[a] = par[b];
            // }
            par[a] = Math.min(para, parb);
            par[b] = Math.min(para, parb);
        }
        String fans = "";
        for (int m = 0; m < S.length(); m++) {
            fans = fans + (char) ('a' + findPar(S.charAt(m)));
        }
        return fans;
    }

    // =======================leetcode 657=====================

    public boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < moves.length(); i++) {
            char c = moves.charAt(i);
            if (c == 'R')
                x++;
            else if (c == 'L')
                x--;
            else if (c == 'U')
                y++;
            else if (c == 'D')
                y--;
        }

        return x == 0 && y == 0;
    }

    // ======================== leetcode 947 ============================

    public int removeStones(int[][] stones) {
        par = new int[stones.length];
        setSize = new int[stones.length];
        for (int i = 0; i < stones.length; i++) {
            par[i] = i;
            setSize[i] = 1;
        }

        for (int i = 0; i < stones.length; i++) {
            for (int j = i + 1; j < stones.length; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    int pari = findPar(i);
                    int parj = findPar(j);
                    if (pari != parj)
                        mergeset(pari, parj);
                }
            }
        }
        int fans = 0;
        for (int i = 0; i < par.length; i++) {
            if (par[i] != i)
                fans++;
        }
        return fans;
    }

    // dfs solution 947 leetcode====================

    public int removeStones_01(int[][] stones) {
        int fcount = 0;
        boolean[] vis = new boolean[stones.length];
        for (int i = 0; i < vis.length; i++) {
            if (!vis[i])
                fcount += dfs_removeStones(stones, vis, i);
        }

        return fcount;

    }

    public int dfs_removeStones(int[][] stones, boolean[] vis, int ip) {
        LinkedList<Integer> que = new LinkedList<>();
        que.add(ip);
        vis[ip] = true;
        int count = 0;
        while (que.size() != 0) {
            int siz = que.size();
            while (siz-- > 0) {
                int temp = que.removeFirst();
                for (int i = 0; i < stones.length; i++) {
                    if (!vis[i] && (stones[i][0] == stones[temp][0] || stones[i][1] == stones[temp][1])) {
                        vis[i] = true;
                        count++;
                        que.addLast(i);
                    }
                }
            }
        }
        return count;
    }

    // leetcode 959=========================================
    // // |-------------------------------------|
    // | |
    // | |
    // | |
    // | |
    // | |
    // | |
    // | |
    // // |-------------------------------------|

    // =====================leetcode 990================================

    public boolean equationsPossible(String[] equations) {
        par = new int[26];
        setSize = new int[26];
        for (int i = 0; i < 26; i++) {
            par[i] = i;
            setSize[i] = 1;
        }
        for (String str : equations) {
            char abo = str.charAt(0);
            char b = str.charAt(1);
            char cbo = str.charAt(3);
            if (b == '=') {
                int par1 = findPar(abo - 'a');
                int par2 = findPar(cbo - 'a');
                if (par1 != par2)
                    mergeset(par1, par2);
            }
        }

        for (String str : equations) {
            char abo = str.charAt(0);
            char b = str.charAt(1);
            char cbo = str.charAt(3);
            if (b == '!') {
                int par1 = findPar(abo - 'a');
                int par2 = findPar(cbo - 'a');
                if (par1 == par2)
                    return false;
            }
        }
        return true;
    }

    // ========================leetcode 200===========================
    public int numIslands_(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return 0;
        int n = grid.length;
        int m = grid[0].length;
        par = new int[m * n];
        setSize = new int[m * n];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                par[i * m + j] = i * m + j;
                if (grid[i][j] == '1') {
                    count++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    if (i + 1 < n && grid[i + 1][j] == '1') {
                        int p1 = findPar(i * m + j);
                        int p2 = findPar((i + 1) * m + j);
                        if (p1 != p2) {
                            mergeset(p1, p2);
                            count--;
                        }
                    }
                    if (j + 1 < m && grid[i][j + 1] == '1') {
                        int p1 = findPar(i * m + j);
                        int p2 = findPar(i * m + (j + 1));
                        if (p1 != p2) {
                            mergeset(p1, p2);
                            count--;
                        }
                    }
                }
            }
        }

        return count;
    }
    // ===================leetcode 839 most most important
    // learning=========================

    int[] par_;

    public int findPar_(int vtx) {
        if (par_[vtx] == vtx)
            return vtx;
        return par_[vtx] = findPar_(par[vtx]); ////////// most important yaha pr hm usko refirn bhi kr rhe hai
        // mtlb uske parent ko bhi change kr diya jisse fast ho gya program
    }

    public boolean areSimiliar(String s1, String s2) {
        int cnt = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i) && ++cnt > 2)
                return false;
        }
        return true;
    }

    public int numSimilarGroups(String[] A) {
        par_ = new int[A.length];

        int count = A.length;

        for (int i = 0; i < A.length; i++) {
            par_[i] = i;
        }

        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {

                int p1 = findPar(i);
                int p2 = findPar(j);
                if (p1 != p2 && areSimiliar(A[i], A[j])) {
                    par_[p2] = p1;
                    // par_[p1]=p2;
                    count--;
                }

            }
        }

        return count;
    }
    // =======================leetcode 1168============================

    // There are n houses in a village. We want to supply water for all the houses
    // by building wells and laying pipes.

    // For each house i, we can either build a well inside it directly with cost
    // wells[i], or pipe in water from another well to it. The costs to lay pipes
    // between houses are given by the array pipes, where each pipes[i] = [house1,
    // house2, cost] represents the cost to connect house1 and house2 together using
    // a pipe. Connections are bidirectional.

    // Find the minimum total cost to supply water to all houses.

    // Example 1:

    // Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
    // Output: 3
    // Explanation:
    // The image shows the costs of connecting houses using pipes.
    // The best strategy is to build a well in the first house with cost 1 and
    // connect the other houses to it with cost 2 so the total cost is 3.

    // Constraints:

    // 1 <= n <= 10000
    // wells.length == n
    // 0 <= wells[i] <= 10^5
    // 1 <= pipes.length <= 10000
    // 1 <= pipes[i][0], pipes[i][1] <= n
    // 0 <= pipes[i][2] <= 10^5
    // pipes[i][0] != pipes[i][1]

    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        // jo har jagah pr well hai usko bhi ek edge maankr pipes mein add kr do fir
        // full array ko sort krke unionFind laga denge to kruskal se ho jayega
        int[][] fpipes = new int[pipes.length + wells.length][3];

        for (int i = 0; i < pipes.length; i++) {
            for (int j = 0; j < pipes[0].length; j++) {
                fpipes[i][j] = pipes[i][j];
            }
        }
        int m = 0;
        for (int i = pipes.length; i < fpipes.length; i++) {

            fpipes[i][0] = 0;
            fpipes[i][1] = m + 1;
            fpipes[i][2] = wells[m];

            m++;
        }
        Arrays.sort(fpipes, (int[] a, int[] b) -> {
            return a[2] - b[2];
        });
        int cost = 0;
        for (int[] p : fpipes) {
            int p1 = findPar(p[0]);
            int p2 = findPar(p[1]);

            if (p1 != p2) {
                cost += p[2];
                par[p1] = p2;
            }
        }

        return cost;
    }

    // https://www.hackerrank.com/challenges/journey-to-the-moon/problem

    public static long journeyToMoon(int n, int[][] astronaut) {
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        boolean[] vis = new boolean[n];
        ArrayList<Integer> countries = new ArrayList<>();
        for (int[] ar : astronaut) {
            graph[ar[0]].add(ar[1]);
            graph[ar[1]].add(ar[0]);
        }
        for (int i = 0; i < graph.length; i++) {

            if (!vis[i])
                countries.add(dfs(graph, i, vis));

        }
        int tot = n;
        long fans = 0;
        for (int i = 0; i < countries.size(); i++) {
            int curr = countries.get(i);
            fans = fans + (curr * (tot - curr));
            tot = tot - curr;
        }

        return fans;
    }

    public static int dfs(ArrayList<Integer>[] gp, int src, boolean[] vis) {
        int count = 0;
        vis[src] = true;
        for (Integer e : gp[src]) {
            if (!vis[e]) {

                count += dfs(gp, e, vis);
            }
        }
        return count + 1;
    }

    // =============================leetcode 1387===============================

    class edge {
        int num = 0;
        int val = 0;

        edge(int num, int val) {
            this.num = num;
            this.val = val;
        }
    }

    public int getKth(int lo, int hi, int k) {
        ArrayList<edge> arr = new ArrayList<>();
        for (int i = lo; i <= hi; i++) {
            p = 0;
            pow(i, 0);
            arr.add(new edge(i, p));
        }
        Collections.sort(arr, (edge a, edge b) -> {
            return a.val - b.val;
        });
        return arr.get(k - 1).num;
    }

    static int p = 0;

    public static void pow(int num, int k) {
        if (num == 1) {
            p = k;
            return;
        }
        if (num % 2 == 0)
            pow(num / 2, k + 1);
        else
            pow((3 * num + 1), k + 1);
    }

    // ====================== leetcode 1306==========================

    public boolean canReach(int[] arr, int start) {
        boolean[] vis = new boolean[arr.length];
        return can(arr, start, vis);
    }

    public boolean can(int[] arr, int start, boolean[] vis) {
        if (arr[start] == 0)
            return true;
        boolean res = false;

        vis[start] = true;
        if ((start - arr[start]) >= 0 && !vis[start - arr[start]])
            res = res || can(arr, start - arr[start], vis);

        if ((start + arr[start]) < arr.length && !vis[start + arr[start]])
            res = res || can(arr, start + arr[start], vis);

        return res;
    }

    // ======================leetcode 1267========================

    public int countServers(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return 0;
        int n = grid.length;
        int m = grid[0].length;

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && isOk(grid, i, j))
                    count++;
            }
        }

        return count;
    }

    public boolean isOk(int[][] grid, int cr, int cc) {
        for (int i = 0; i < grid.length; i++) {
            if (i != cr && grid[i][cc] == 1)
                return true;
        }
        for (int i = 0; i < grid[0].length; i++) {
            if (i != cc && grid[cr][i] == 1)
                return true;
        }
        return false;
    }

}