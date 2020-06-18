import java.util.*;

public class L00 {
    public static void main(String[] args) {
        solve();
    }

    public static class Mystack {
        int N = 1000;
        int[] arr;
        int sze = 0;
        int tos = -1;

        Mystack() {
            arr = new int[N];
        }

        Mystack(int num) {
            arr = new int[num];
            this.N = num;
        }

        public int size() {
            return this.sze;
        }

        public boolean empty() {
            return this.sze == 0;
        }

        public void push(int val) {
            if (sze == N)
                return;
            tos++;
            sze++;
            arr[tos] = val;
        }

        public int pop() {
            if (sze == 0)
                return -1;
            int temp = arr[tos];
            arr[tos] = -1;
            tos--;
            sze--;
            return temp;
        }

        public int top() {
            if (sze == 0)
                return -1;
            return arr[tos];
        }

    }

    public static void solve() {
        Mystack st = new Mystack(10);

        for (int i = 1; i <= 20; i++)
            st.push(10 + i);

        while (st.size() != 0) {
            System.out.println(st.top() + " ");
            st.pop();
        }
    }
}