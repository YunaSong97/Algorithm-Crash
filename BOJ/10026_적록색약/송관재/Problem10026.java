import java.util.Scanner;

public class Problem10026 {

    static int N;
    static char[][] map;
    static int[][] visit;
    static int count = 0;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { -1, 0, 1, 0 };

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        s.nextLine();
        String str = "";
        map = new char[N][N];
        visit = new int[N][N];
        for (int i = 0; i < N; i++) {
            str = s.nextLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        int normal = 0;
        int redGreen = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                count = 0;
                dfs(j, i, map[i][j]);
                if (count != 0) {
                    normal++;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                count = 0;
                solve(j, i, map[i][j]);
                if (count != 0) {
                    redGreen++;
                }
            }
        }
        System.out.println(normal + " " + redGreen);
    }

    public static void dfs(int x, int y, char color) {
        if (x < 0 || y < 0 || x >= N || y >= N || map[y][x] != color || visit[y][x] == 1) {
            return;
        }

        if (map[y][x] == color) {
            count++;
            visit[y][x] = 1;
            for (int i = 0; i < 4; i++) {
                dfs(x + dx[i], y + dy[i], color);
            }
        }
    }

    public static void solve(int x, int y, char color) {
        if (x < 0 || y < 0 || x >= N || y >= N || visit[y][x] == 2) {
            return;
        } else {
            if (color == 'B') {
                if (color != map[y][x]) {
                    return;
                }
            } else {
                if (map[y][x] == 'B') {
                    return;
                }
            }
        }

        count++;
        visit[y][x] = 2;
        for (int i = 0; i < 4; i++) {
            solve(x + dx[i], y + dy[i], color);
        }

    }

}
