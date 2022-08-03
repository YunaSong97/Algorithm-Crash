import java.io.*;
import java.util.*;

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main_BOJ_10026 {
    static int n;
    static Character[][] map;
    static Queue<Node> queue = new LinkedList<>();
    static boolean[][] visit;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new Character[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        visit = new boolean[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j]) {
                    cnt++;
                    bfs(i, j, map[i][j]);
                }
            }
        }

        System.out.print(cnt + " ");
        
        visit = new boolean[n][n];
        
        cnt=0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j]) {
                    cnt++;
                    RGbfs(i, j, map[i][j]);
                }
            }
        }
        System.out.print(cnt);
    }

    static void bfs(int x, int y, char c) {
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        queue.add(new Node(x, y));
        visit[x][y] = true;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isIn(nx, ny, n) && map[nx][ny] == c && !visit[nx][ny]) {
                    visit[nx][ny] = true;
                    queue.add(new Node(nx, ny));
                }
            }
        }
    }
    
    static void RGbfs(int x, int y, char c) {
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        queue.add(new Node(x, y));
        visit[x][y] = true;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isIn(nx, ny, n) && !visit[nx][ny]) {
                    if(c == 'B') {
                        if(map[nx][ny]!='B') {
                            continue;
                        }
                    }else {
                        if(map[nx][ny]=='B') {
                            continue;
                        }
                    }
                    visit[nx][ny] = true;
                    queue.add(new Node(nx, ny));
                }
            }
        }
    }

    static boolean isIn(int x, int y, int n) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
