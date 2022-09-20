import java.util.Arrays;

class PG_72413 {
    static int[][] map;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;

        map = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(map[i], 100000001);
        }

        for (int i = 1; i <= n; i++) {
            map[i][i] = 0;
        }

        for (int[] fare : fares) {
            map[fare[0]][fare[1]] = fare[2];
            map[fare[1]][fare[0]] = fare[2];
        }

        answer = floydWarshall(n, s, a, b);

        return answer;
    }

    int floydWarshall(int n, int s, int a, int b) {
        int[][] d = map.clone();

        int sum = Integer.MAX_VALUE;
        for (int k = 1; k <= n; k++) {//k : 거쳐가는 노드
            for (int i = 1; i <= n; i++) {//i : 출발 노드
                for (int j = 1; j <= n; j++) {//j : 도착 노드
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);

                }
            }
        }

        for (int k = 1; k <= n; k++) {
            sum = Math.min(d[s][k] + d[k][a] + d[k][b], sum);
        }

        return sum;
    }
}
