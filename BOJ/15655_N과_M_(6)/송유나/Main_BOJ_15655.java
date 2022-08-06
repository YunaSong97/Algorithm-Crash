package BOJ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main_BOJ_15655 {
    static boolean[] visit;
    static ArrayList<Integer> numList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        visit = new boolean[n];
        numList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            numList.add(sc.nextInt());
        }

        Collections.sort(numList);

        combination(0, n, m);
    }

    static void combination(int start, int n, int m) {

        if (m == 0) {
            for (int i = 0; i < n; i++) {
                if (visit[i]) {
                    System.out.print(numList.get(i) + " ");
                }
            }

            System.out.println();
            return;
        }

        if (start < n) {
            visit[start] = true;
            combination(start + 1, n, m - 1);
            visit[start] = false;

            combination(start + 1, n, m);
        }
    }
}
