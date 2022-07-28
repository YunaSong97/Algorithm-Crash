package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_17070 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][n];
        int[][][] ans = new int[n + 2][n + 2][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //0 가로, 1 세로, 2 대각선
        ans[1][2][0] = 1;

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (ans[i][j][0] != 0) {
                    if (isIn(n, i - 1, j) && map[i - 1][j] == 0) {
                        ans[i][j + 1][0] += ans[i][j][0];
                    }
                    if (isIn(n, i, j) && map[i][j] == 0 && map[i][j - 1] == 0 && map[i - 1][j] == 0) {
                        ans[i + 1][j + 1][2] += ans[i][j][0];
                    }
                }
                if (ans[i][j][1] != 0) {
                    if (isIn(n, i, j - 1) && map[i][j - 1] == 0) {
                        ans[i + 1][j][1] += ans[i][j][1];
                    }
                    if (isIn(n, i, j) && map[i][j] == 0 && map[i][j - 1] == 0 && map[i - 1][j] == 0) {
                        ans[i + 1][j + 1][2] += ans[i][j][1];
                    }
                }
                if (ans[i][j][2] != 0) {
                    if (isIn(n, i - 1, j) && map[i - 1][j] == 0) {
                        ans[i][j + 1][0] += ans[i][j][2];
                    }
                    if (isIn(n, i, j - 1) && map[i][j - 1] == 0) {
                        ans[i + 1][j][1] += ans[i][j][2];
                    }
                    if (isIn(n, i, j) && map[i][j] == 0 && map[i][j - 1] == 0 && map[i - 1][j] == 0) {
                        ans[i + 1][j + 1][2] += ans[i][j][2];
                    }
                }
            }
        }

        System.out.println(ans[n][n][0] + ans[n][n][1] + ans[n][n][2]);
    }

    static boolean isIn(int n, int x, int y) {
        return x < n && x >= 0 && y < n && y >= 0;
    }
}
