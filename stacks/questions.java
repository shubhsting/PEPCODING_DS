import java.util.Stack;

public class questions {
    public static void main(String[] args) {

    }

    // next greater from left sicde jiska koi greater nhi hai uska index -1 pda hai
    public int[] ngor(int[] arr) {
        Stack st = new Stack();
        int[] ans = new int[arr.length];
        Arrays.fill(ans, -1);
        for (int i = 0; i < arr.length; i++) {
            while (st.size() != 0 && arr[(int) st.peek()] < arr[i]) // for smaller replace to '<' to '>'
            {
                ans[(int) st.peek()] = i;
                st.pop();
            }

            st.push(i);
        }

        return ans;
    }

    public int[] ngol(int[] arr) {
        Stack st = new Stack();
        int[] ans = new int[arr.length];
        Arrays.fill(ans, -1);
        for (int i = arr.length - 1; i >= 0; i--) {
            while (st.size() != 0 && arr[(int) st.peek()] < arr[i]) // for smaller replace to '<' to '>'
            {
                ans[(int) st.peek()] = i;
                st.pop();
            }

            st.push(i);
        }

        return ans;
    }

    public boolean isValid(String s) {
        Stack st = new Stack();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{')
                st.push(c);
            else {
                if (st.size() == 0)
                    return false;
                if (c == ')' && (char) st.peek() != '(')
                    return false;
                if (c == ']' && (char) st.peek() != '[')
                    return false;
                if (c == '}' && (char) st.peek() != '{')
                    return false;
                st.pop();
            }
        }
        return st.size() == 0 ? true : false;
    }

    public String removeOuterParentheses(String str) {
        String ans = "";
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(' && count++ > 0)
                ans += ch;

            else if (ch == ')' && count-- > 1)
                ans += ch;
        }
        return ans;
    }

    // circular ghum skte hai to 2 baar traverse kr lenge hm log
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        Stack st = new Stack();
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < 2 * n; i++) {
            while (st.size() != 0 && nums[(int) st.peek()] < nums[i % n]) {
                ans[(int) st.peek()] = nums[i % n];
                st.pop();
            }
            st.push(i % n);
        }
        return ans;
    }

    // leetcode 901

    class StockSpanner {
        Stack st;
        int i;

        public class pair {
            int idx = 0;
            int val = 0;

            pair(int idx, int val) {
                this.idx = idx;
                this.val = val;
            }
        }

        public StockSpanner() {
            st = new Stack();
            st.push(new pair(-1, -1));
            i = 0;
        }

        public int next(int price) {
            int ans = 1;
            while (((pair) st.peek()).idx != -1 && ((pair) st.peek()).val <= price) {
                st.pop();
            }
            ans = i - ((pair) st.peek()).idx;
            st.push(new pair(i, price));
            i++;
            return ans;
        }
    }

    // leetcode 921
    public int minAddToMakeValid(String str) {
        Stack st = new Stack();
        st.push(-1);
        for (int i = 0; i < str.length(); i++) {
            if ((int) st.peek() != -1 && str.charAt((int) st.peek()) == '(' && str.charAt(i) == ')')
                st.pop();

            else
                st.push(i);
        }
        return st.size() - 1;
    }

    public int minAddToMakeValid_02(String str) {
        int openningBracReq = 0;
        int closingBracReq = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(')
                closingBracReq++;
            else if (str.charAt(i) == ')' && closingBracReq >= 1)
                closingBracReq--;
            else
                openningBracReq++;
        }
        return openningBracReq + closingBracReq;
    }

    // leetcode 1249
    public String minRemoveToMakeValid(String str) {
        StringBuilder sb = new StringBuilder();
        Stack st = new Stack();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(') {
                sb.append('(');
                st.push(i);
            } else if (ch == ')') {
                if (st.size() != 0 && str.charAt((int) st.peek()) == '(' && ch == ')') {
                    sb.setCharAt((int) st.peek(), '[');
                    sb.append(']');
                    st.pop();
                } else
                    sb.append(ch);
            } else {
                sb.append(ch);
            }
        }
        StringBuilder ans = new StringBuilder();
        String temp = sb.toString();
        for (int i = 0; i < temp.length(); i++) {
            char ch = temp.charAt(i);
            if (ch == '[')
                ans.append("(");
            else if (ch == ']')
                ans.append(")");
            else if (ch >= 'a' && ch <= 'z')
                ans.append(ch);
        }
        return ans.toString();
    }

    public String minRemoveToMakeValid_02(String str) {
        StringBuilder sb = new StringBuilder(str);
        Stack st = new Stack();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'a' && ch <= 'z')
                continue;
            if (st.size() != 0 && str.charAt((int) st.peek()) == '(' && ch == ')')
                st.pop();
            else
                st.push(i);
        }
        while (!st.isEmpty()) {
            int idx = (int) st.pop();
            sb.setCharAt(idx, '*');
        }
        String ans = "";
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) != '*')
                ans += sb.charAt(i);
        }
        return ans;
    }

    // leetcode 1047
    public String removeDuplicates(String str) {
        Stack st = new Stack();
        HashSet<Character> hs = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (st.size() == 0 || (char) st.peek() != ch)
                st.push(ch);
            else
                st.pop();
        }
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty())
            sb.insert(0, (char) st.pop());
        return sb.toString();
    }

    // 735 leetcode

    public int[] asteroidCollision(int[] asteroids) {
        Stack st = new Stack();
        for (int i = 0; i < asteroids.length; i++) {
            int num = asteroids[i];
            if (num > 0)
                st.push(num);
            else {
                while (st.size() != 0 && (int) st.peek() > 0 && (int) st.peek() < -num)
                    st.pop();

                if (st.size() != 0 && (int) st.peek() == -num)
                    st.pop();
                else if (st.size() == 0 || (int) st.peek() < 0)
                    st.push(num);
                else if (st.size() != 0 && (int) st.peek() > -num) {
                    // kuch mt kro kyoki kbhi collide nhi honge
                }
            }
        }

        int[] ans = new int[st.size()];
        int i = st.size() - 1;
        while (st.size() != 0) {
            ans[i] = (int) st.pop();
            i--;
        }

        return ans;

    }

    public static int[] nsor(int[] arr) {
        Stack st = new Stack();
        int ans[] = new int[arr.length];
        Arrays.fill(ans, arr.length);
        for (int i = 0; i < arr.length; i++) {
            while (st.size() != 0 && arr[(int) st.peek()] > arr[i]) {
                ans[(int) st.peek()] = i;
            }
            st.push(i);
        }
        return ans;
    }

    public static int[] nsol(int[] arr) {
        Stack st = new Stack();
        int ans[] = new int[arr.length];
        Arrays.fill(ans, -1);
        for (int i = arr.length - 1; i >= 0; i--) {
            while (st.size() != 0 && arr[(int) st.peek()] > arr[i]) {
                ans[(int) st.peek()] = i;
            }
            st.push(i);
        }
        return ans;
    }

    public static int largestRectangleArea(int[] heights) {
        int[] nsorA = nsor(heights);
        int[] nsolA = nsol(heights);
        int max_ = 0;
        for (int i = 0; i < heights.length; i++) {
            int width = nsorA[i] - nsolA[i] - 1;
            int area = width * heights[i];
            max_ = Math.max(max_, area);
        }
        return max_;
    }

    public static int largestRectangleArea_(int[] heights) {
        Stack st = new Stack();
        st.push(-1);
        int max_ = 0;
        for (int i = 0; i < heights.length; i++) {
            while ((int) st.peek() != -1 && heights[(int) st.peek()] >= heights[i]) {
                int height = heights[(int) st.pop()];
                int width = i - (int) st.peek() - 1;
                int area = height * width;
                if (area > max_)
                    max_ = area;
            }
            st.push(i);
        }
        while ((int) st.peek() != -1) {
            int height = heights[(int) st.pop()];
            int width = heights.length - (int) st.peek() - 1;
            int area = height * width;
            if (area > max_)
                max_ = area;
        }
        return max_;
    }

    public int maximalRectangle(char[][] matrix) {
        int[] arr = new int[matrix[0].length];
        int max_ = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int num = matrix[i][j] - 'a';
                if (num == 1)
                    arr[j] += 1;
                else
                    arr[j] = 0;
            }
            int area = largestRectangleArea(arr);
            max_ = Math.max(area, max_);
        }
        return max_;
    }

    public int trap(int[] height) {
        int n = height.length;
        int[] gol = new int[n];
        int[] gor = new int[n];
        int prev = -1;
        for (int i = 0; i < n; i++) {
            gol[i] = Math.max(prev, height[i]);
            prev = gol[i];
        }
        prev = -1;
        for (int i = n - 1; i >= 0; i--) {
            gor[i] = Math.max(prev, height[i]);
            prev = gor[i];
        }
        int trap = 0;
        for (int i = 0; i < n; i++)
            trap += Math.min(gol[i], gor[i]) - height[i];

        return trap;
    }

    public int trap_(int[] height) {
        Stack st = new Stack();
        int n = height.length;
        for (int i = 0; i < n; i++) {
            while (st.size() != 0 && height[(int) st.peek()] <= height[i]) {
                int height_ = height[(int) st.pop()];
                if (st.size() == 0)
                    break;
                int width = i - (int) (st.peek()) - 1;
                water += width * (Math.min(height[i], height[(int) st.peek()]) - height_);
            }
            st.push(i);
        }
        return water;
    }

    public int trap02(int[] height) {
        int n = height.length;
        int lft = 0;
        int rt = n - 1;
        int lmaxBH = 0;
        int rmaxBH = 0;
        int water = 0;
        while (lft <= rt) {
            lmaxBH = Math.max(lmaxBH, height[lft]);
            rmaxBH = Math.max(rmaxBH, height[rt]);
            water += lmaxBH >= rmaxBH ? rmaxBH - height[rt--] : lmaxBH - height[lft++];
        }
        return water;
    }

    // leetcode 155
    class MinStack {
        Stack st;
        long minSF = 0;

        /** initialize your data structure here. */
        public MinStack() {
            st = new Stack();
            minSF = 0;
        }

        public void push(long x) {
            if (st.size() == 0) {
                st.push(x);
                minSF = x;
                return;
            }
            if (x < minSF) {
                long temp = (2 * x) - minSF;
                st.push(temp);
                minSF = x;
            } else
                st.push(x);
        }

        public void pop() {
            if ((long) st.peek() < minSF) {
                long temp = (2 * minSF) - (long) st.peek();
                minSF = temp;
            }

            st.pop();
        }

        public long top() {
            if ((long) st.peek() > minSF)
                return (long) st.peek();
            return minSF;
        }

        public long getMin() {
            return minSF;
        }
    }

    /**
     * Your MinStack object will be instantiated and called as such: MinStack obj =
     * new MinStack(); obj.push(x); obj.pop(); int param_3 = obj.top(); int param_4
     * = obj.getMin();
     */
    // leetcode 946
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int idx = 0;
        Stack st = new Stack();
        for (int i = 0; i < pushed.length; i++) {
            while (idx < popped.length && st.size() != 0 && (int) st.peek() == popped[idx]) {
                st.pop();
                idx++;
            }
            st.push(pushed[i]);
        }
        while (idx < popped.length && st.size() != 0 && (int) st.peek() == popped[idx]) {
            st.pop();
            idx++;
        }
        return st.size() == 0;
    }

    // leetcode 1190

    public String reverseParentheses(String str) {
        Stack st = new Stack();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == ')') {
                String rvse = "";
                while (st.size() != 0 && (char) st.peek() != '(') {
                    rvse += (char) st.pop();
                }
                st.pop();
                for (int ip = 0; ip < rvse.length(); ip++)
                    st.push(rvse.charAt(ip));
            } else
                st.push(ch);
        }
        String ans = "";
        while (!st.isEmpty()) {
            ans = (char) st.pop() + ans;
        }
        return ans;
    }

    // leetcode 103

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        LinkedList<TreeNode> que = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        que.addFirst(root);
        int level = 0;
        while (que.size() != 0) {
            int siz = que.size();
            ArrayList<Integer> temp = new ArrayList<>();
            while (siz-- > 0) {
                TreeNode nd = que.removeFirst();
                temp.add(nd.val);
                if (nd.left != null)
                    que.addLast(nd.left);
                if (nd.right != null)
                    que.addLast(nd.right);
            }
            if ((level % 2) != 0)
                Collections.reverse(temp);
            level++;
            ans.add(temp);
        }
        return ans;
    }

    // leetcode 394
    public String decodeString(String str) {
        Stack st = new Stack();
        String fans = "";
        for (int i = 0; i < str.length(); i++) {
            String ch = str.charAt(i) + "";
            if (ch.charAt(0) == ']') {
                String stri = "";
                while (((String) st.peek()).charAt(0) != '[')
                    stri = (String) st.pop() + stri;

                st.pop();
                String num = "";
                while (st.size() != 0 && ((String) st.peek()).charAt(0) >= '0' && ((String) st.peek()).charAt(0) <= '9')
                    num = (String) st.pop() + num;

                int tot = Integer.parseInt(num);

                while (tot-- > 0) {
                    st.push(stri);
                }
            } else
                st.push(ch);
        }
        while (st.size() != 0) {
            fans = (String) st.pop() + fans;
        }
        return fans;
    }

    // leetcode 339
    public boolean isValidSerialization(String str) {
        String[] nodes = str.split(",");
        Stack st = new Stack();
        for (int i = 0; i < nodes.length; i++) {
            String ch = nodes[i];
            if (ch.charAt(0) == '#') {
                while (st.size() != 0 && ((String) st.peek()).charAt(0) == '#') {
                    st.pop();
                    if (st.size() == 0)
                        return false;
                    st.pop();
                }

                st.push(ch);

            } else
                st.push(ch);
        }

        return st.size() == 1 && ((String) st.pop()).charAt(0) == '#';
    }
}
