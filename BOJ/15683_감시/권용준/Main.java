import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static CCTV[] cctv = new CCTV[8];
    static int cctvSize = 0;
    static int[][] map = new int[8][8];
    static int[] dy = new int[] {0, 1, 0, -1};
    static int[] dx = new int[] {1, 0, -1, 0};
    static int[] iterations = new int[] {4, 2, 4, 4, 1};
    static int ans = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctv[cctvSize++] = new CCTV(i, j, map[i][j] - 1);
                }
            }
        }
        ans = 100;
        dfs(0);
        System.out.println(ans);
    }

    static void copyMap(int[][] desc, int[][] src) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                desc[i][j] = src[i][j];
            }
        }
    }

    static void dfs(int cctvIdx) {
        if (cctvIdx == cctvSize) {
            int cnt = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0) cnt++;
                }
            }

            ans = Math.min(ans, cnt);
            return;
        }

        int[][] tmpMap = new int[8][8];

        int type = cctv[cctvIdx].type;
        int curY = cctv[cctvIdx].y;
        int curX = cctv[cctvIdx].x;

        // cctv로 볼 수 있는 영역 체킹
        for (int dir = 0; dir < iterations[type]; dir++) {
            copyMap(tmpMap, map);
            if (type == 0) {
                observant(curY, curX, dir);
            } else if (type == 1) {
                observant(curY, curX, dir);
                observant(curY, curX, dir + 2);
            } else if (type == 2) {
                observant(curY, curX, dir);
                observant(curY, curX, dir + 1);
            } else if (type == 3) {
                observant(curY, curX, dir);
                observant(curY, curX, dir + 1);
                observant(curY, curX, dir + 2);
            } else if (type == 4) {
                observant(curY, curX, dir);
                observant(curY, curX, dir + 1);
                observant(curY, curX, dir + 2);
                observant(curY, curX, dir + 3);
            }

            dfs(cctvIdx + 1);
            copyMap(map, tmpMap);
        }
    }

    static void observant(int curY, int curX, int dir) {
        dir = (dir % 4);

        if (dir == 0) {
            for (int x = curX + 1; x < M; x++) {
                if (map[curY][x] == 6) break;
                map[curY][x] = -1;
            }
        }

        if (dir == 1) {
            for (int y = curY + 1; y < N; y++) {
                if (map[y][curX] == 6) break;
                map[y][curX] = -1;
            }
        }

        if (dir == 2) {
            for (int x = curX - 1; x >= 0; x--) {
                if (map[curY][x] == 6) break;
                map[curY][x] = -1;
            }
        }

        if (dir == 3) {
            for (int y = curY - 1; y >= 0; y--) {
                if (map[y][curX] == 6) break;
                map[y][curX] = -1;
            }
        }
    }
    static class CCTV {
        public CCTV(int y, int x, int type) {
            this.y = y;
            this.x = x;
            this.type = type;
        }

        int y, x, type;
    }
}
