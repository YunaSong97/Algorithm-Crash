import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static char[][] map;
    static int[] dy = new int[] {1, 0, -1, 0};
    static int[] dx = new int[] {0, 1, 0, -1};
    static boolean[][] visited;
    static int[][] pos = new int[2500][2];
    static int W, H;
    static int idx;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new char[50][50];

        for (int x = 0; x < H; x++) {
            String line = sc.nextLine();
            for (int y = 0; y < W; y++) {
                map[x][y] = line.charAt(y);
                if (map[x][y] == 'L') {
                    pos[idx++] = new int[] {x , y};
                }
            }
        }

        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < idx; i++) {
            visited = new boolean[50][50];
            ans = Math.max(ans, bfs(pos[i][0], pos[i][1]));
        }


        System.out.println(ans);
    }

    static int bfs(int sX, int sY) {
        Queue<Info> Q = new LinkedList<>();
        int maxDist = 0;
        visited[sX][sY] = true;
        Q.offer(new Info(sX, sY, 0));

        while(!Q.isEmpty()) {
            Info cur = Q.poll();

            if (maxDist < cur.dist) {
                maxDist = cur.dist;
            }

            for (int d = 0; d < 4; d++) {
                int nX = cur.x + dx[d];
                int nY = cur.y + dy[d];

                // 경계 검사
                if (nX < 0 || nX >= H || nY < 0 || nY >= W) continue;
                // 물쪽으로 갈수없음
                if (map[nX][nY] == 'W') continue;
                if (visited[nX][nY]) continue;
                // 현재 dist + 1 > 다음 위치의 dist con

                visited[nX][nY] = true;
                Q.offer(new Info(nX, nY, cur.dist + 1));
            }
        }

        return maxDist;
    }

    static class Info {
        int x;
        int y;
        int dist;

        public Info(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}

