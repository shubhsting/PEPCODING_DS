import java.util.*;

public class questions {
    public static void main(String[] args) {

    }

    // leetcode 1108
    public String defangIPaddr(String address) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < address.length(); i++) {
            char c = address.charAt(i);
            if (c == '.')
                sb.append("[.]");
            else
                sb.append(c);
        }
        return sb.toString();
    }

    // leetcode 1221
    public int balancedStringSplit(String str) {
        int count = 0;
        int ans = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == 'R')
                count++;
            if (ch == 'L')
                count--;
            if (count == 0)
                ans++;
        }
        return ans;
    }

    // leetcode 709
    public String toLowerCase(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'A' && ch <= 'Z')
                sb.append((char) ('a' + (ch - 'A')));
            else
                sb.append(ch);
        }
        return sb.toString();
    }



    
}