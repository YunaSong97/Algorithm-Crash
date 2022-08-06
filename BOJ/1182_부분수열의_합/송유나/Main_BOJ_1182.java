package BOJ;

import java.util.Scanner;

public class Main_BOJ_1182 {
    static int[] num;
    static int answer = 0;
    static int s;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        s = sc.nextInt();

        num = new int[n];

        for (int i = 0; i < n; i++) {
            num[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            calSum(0, n, i + 1, 0);
        }

        System.out.println(answer);
    }

    static void calSum(int start, int n, int r, int sum) {
        if (r == 0) {
            if (sum == s) {
                answer++;
            }
            return;
        }

        if (start < n) {
            calSum(start + 1, n, r - 1, sum + num[start]);
            calSum(start + 1, n, r, sum);
        }
    }
}
