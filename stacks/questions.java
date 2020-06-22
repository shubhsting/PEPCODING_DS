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


    
}
