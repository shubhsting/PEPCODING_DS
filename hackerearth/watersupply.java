import java.util.*;

public class watersupply {
    public static void main(String args[]) throws Exception {// Scanner

        Scanner sc = new Scanner(System.in);

        int c = sc.nextInt();

        for (int i = 0; i < c - 1; i++) {
            int d = sc.nextInt();
            int e = sc.nextInt();

        }
        int arr[] = new int[c];
        String str = "";
        String temp = "";

        for (int i = 0; i < c; i++) {
            arr[i] = sc.nextInt();
        }
        int s = 1;
        for (int i = 0; i < c; i++) {
            if (arr[i] == 0) {
                s = 0;
                break;
            }
        }

        if (s == 0) {
            for (int i = 0; i < c; i++) {

                if (arr[i] == 0) {
                    str = str + "0";
                } else if (arr[i] == 1) {
                    if (str.length() > temp.length()) {
                        temp = str;
                    }
                    str = "";
                }

            }
            System.out.println(temp.length() + 1 + 1);

        } else
            System.out.println(1);

    }
}