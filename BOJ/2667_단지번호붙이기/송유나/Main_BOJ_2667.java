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

public class Main {

    static boolean[][] visit;
    static Queue<Node> queue = new LinkedList<>();
    static int n;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        visit = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int j = 0; j < n; j++) {
                map[i][j] = Character.getNumericValue(str.charAt(j));
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0 && !visit[i][j]) {
                    ans.add(bfs(i, j));
                }
            }
        }
        Collections.sort(ans);
        System.out.println(ans.size());
        for(int i=0;i<ans.size();i++) {
            System.out.println(ans.get(i));
        }
    }

    static int bfs(int x, int y) {
        int cnt = 1;
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };

        queue.add(new Node(x, y));
        visit[x][y] = true;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isIn(n, nx, ny) && map[nx][ny] != 0 && !visit[nx][ny]) {
                    visit[nx][ny] = true;
                    queue.add(new Node(nx, ny));
                    cnt++;
                }
            }
        }

        return cnt;
    }

    static boolean isIn(int n, int x, int y) {
        return (x >= 0 && x < n && y >= 0 && y < n);
    }
}
