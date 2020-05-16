import java.util.Scanner;

class gasstations {
    public static void main(String args[]) throws Exception {

        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int fuel = scn.nextInt();

        int count = 0;
        while (n-- > 0) {
            int p = scn.nextInt();
            if ((fuel - p) > 0) {
                count++;
                fuel = fuel - p;
            } else if ((fuel - p) <= 0) {
                count++;
                break;
            }

        }
        System.out.println(count);

    }
}
