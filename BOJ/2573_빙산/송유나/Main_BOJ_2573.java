import java.io.*;
import java.util.*;

class Node {
    int x, y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main_BOJ_2573 {
    static int[][] map;
    static boolean[][] visit;
    static int n;
    static int m;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int ice = 0;
        int ans = 0;

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            ice = 0;

            visit = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] != 0 && !visit[i][j]) {
                        countIce(i, j, visit);
                        ice++;
                        if (ice == 2) {
                            System.out.println(ans);
                            return;
                        }
                    }
                }
            }
            melt();
            ans++;

            if (ice == 0) {
                System.out.println(0);
                return;
            }
        }

    }

    static void countIce(int x, int y, boolean[][] visit) {

        queue.add(new Node(x, y));
        visit[x][y] = true;

        int nx;
        int ny;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            for (int i = 0; i < 4; i++) {
                nx = now.x + dx[i];
                ny = now.y + dy[i];

                if (!isIn(nx, ny)) {
                    continue;
                }

                if (map[nx][ny] != 0 && !visit[nx][ny]) {
                    queue.add(new Node(nx, ny));
                    visit[nx][ny] = true;
                }
            }
        }

    }

    static int countWater(int x, int y) {
        int cnt = 0;
        int nx;
        int ny;

        for (int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];

            if (isIn(nx, ny) && map[nx][ny] == 0) {
                cnt++;
            }
        }

        return cnt;
    }

    static void melt() {
        int[][] temp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (map[i][j] != 0) {
                    temp[i][j] = map[i][j] - countWater(i, j);
                    if (temp[i][j] < 0) {
                        temp[i][j] = 0;
                    }
                }

            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = temp[i][j];
            }
        }

    }

    static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }
}
