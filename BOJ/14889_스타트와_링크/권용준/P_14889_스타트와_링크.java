import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int N;
    static boolean[] visited;
    static int[] comb;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());

        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1];
        comb = new int[N / 2];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combination(0);
        System.out.println(ans);
    }

    static void combination(int k) {
        if (k == N / 2) {
            int sSum = 0;
            int tSum = 0;
            // comb에는 스타트 팀의 조합이 만들어져 있고 이것을 토대로 팀능력치 계산
            for (int i = 1; i <= N; i++) {
                for (int j = i; j <= N; j++) {
                    if (i == j) continue;
                    if (visited[i] && visited[j]) {
                        sSum += map[i][j];
                        sSum += map[j][i];
                    }
                }
            }

            for (int i = 1; i <= N; i++) {
                for (int j = i; j <= N; j++) {
                    if (i == j) continue;
                    if (!visited[i] && !visited[j]) {
                        tSum += map[i][j];
                        tSum += map[j][i];
                    }
                }
            }

            ans = Math.min(ans, Math.abs(sSum - tSum));
            return;
        }

        for (int cand = 1; cand <= N; cand++) {
            if (visited[cand]) continue;
            if (k == 0) {
                visited[cand] = true;
                comb[k] = cand;
                combination(k + 1);
                visited[cand] = false;
            } else {
                if (comb[k - 1] < cand) {
                    comb[k] = cand;
                    visited[cand] = true;
                    combination(k + 1);
                    visited[cand] = false;
                }
            }
        }
    }
}
