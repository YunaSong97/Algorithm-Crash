import java.util.Scanner;

class Node {
    int x, y;
    int dir;

    public Node(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}

public class Main_BOJ_14503 {
    static int n;
    static int m;
    static int ans;
    static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][m];
        ans = 0;

        Node start = new Node(sc.nextInt(), sc.nextInt(), sc.nextInt());

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        while (start != null) {
            start = clean(start);
        }

        System.out.println(ans);
    }

    static Node clean(Node node) {
        int[] dx = {-1, 0, 1, 0};//북 동 남 서
        int[] dy = {0, 1, 0, -1};

        Node next = null;

        if (map[node.x][node.y] == 0) {
            map[node.x][node.y] = 2;
            ans++;
        }

        for (int i = 3; i > -1; i--) {
            int nx = node.x + dx[(node.dir + i) % 4];
            int ny = node.y + dy[(node.dir + i) % 4];

            if (isIn(nx, ny) && map[nx][ny] == 0) {
                next = new Node(nx, ny, (node.dir + i) % 4);
                break;
            }
        }

        if (next == null) {
            int backDir = (node.dir + 2) % 4;
            node.x += dx[backDir];
            node.y += dy[backDir];

            if (map[node.x][node.y] != 1) {
                next = node;
            }
        }

        return next;
    }

    static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }
}
