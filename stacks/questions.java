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

    
}
