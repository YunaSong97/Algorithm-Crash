import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int x, y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main_BOJ_20058 {
    static int N, Q, size;
    static int[] lList;
    static int[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        size = (int) Math.pow(2, N);
        map = new int[size][size];
        lList = new int[Q];

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            lList[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < Q; i++) {
            int part = (int) Math.pow(2, lList[i]);
            for (int j = 0; j < size; j += part) {
                for (int k = 0; k < size; k += part) {
                    turn(part, j, k);
                }
            }
            melt();
        }

        int answer = -1;
        int ice = 0;
        boolean[][] visit = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ice += map[i][j];
                if (!visit[i][j] && map[i][j] != 0) {
                    answer = Math.max(answer, countIce(visit, i, j));
                }
            }
        }

        if (answer <= 1) {
            answer = 0;
        }
        System.out.println(ice);
        System.out.println(answer);
    }

    static int countIce(boolean[][] visit, int x, int y) {
        visit[x][y] = true;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(x, y));
        int cnt = 1;

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isIn(nx, ny) && map[nx][ny] != 0 && !visit[nx][ny]) {
                    cnt++;
                    visit[nx][ny] = true;
                    queue.add(new Node(nx, ny));
                }
            }
        }

        return cnt;
    }

    static void turn(int n, int x, int y) {
        int[][] temp = new int[n][n];

        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                temp[j - y][n + x - i - 1] = map[i][j];
            }
        }

        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                map[i][j] = temp[i - x][j - y];
            }
        }
    }

    static void melt() {
        int[][] temp = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!checkIce(i, j)) {
                    if (map[i][j] != 0) {
                        temp[i][j] = map[i][j] - 1;
                    }
                } else {
                    temp[i][j] = map[i][j];
                }
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = temp[i][j];
            }
        }
    }

    static boolean checkIce(int x, int y) {
        int cnt = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isIn(nx, ny)) {
                if (map[nx][ny] > 0) {
                    cnt++;
                }
            }

            if (cnt >= 3) {
                return true;
            }
        }

        return false;
    }

    static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < size && y < size;
    }
}
