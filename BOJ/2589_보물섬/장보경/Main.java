import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static String[][] arr;
    static boolean[][] visited;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new String[N][M];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().split("");
        }

        // 모든 L의 위치에서 bfs를 하며 가장 긴 시간을 찾기
        for (int i=0; i<N; i++) {
            for (int j = 0; j < M; j++) {
                visited = new boolean[N][M];
                if (arr[i][j].equals("L")) {
                    int time = bfs(new Pos(i, j, 0));
                    ans = Math.max(ans, time);
                }
            }
        }

        System.out.println(ans);
    }

    static int[] X = {-1, 1, 0, 0};
    static int[] Y = {0, 0, -1, 1};

    public static int bfs(Pos p){
        Queue<Pos> q = new ArrayDeque<>();
        q.add(p);
        visited[p.x][p.y] = true;

        int time = 0;
        while (!q.isEmpty()){
            Pos np = q.poll();

            for (int i=0; i<4; i++){
                int nx = np.x + X[i];
                int ny = np.y + Y[i];
                int nCnt = np.cnt + 1;

                if (nx>-1 && ny>-1 && nx<N && ny<M && !visited[nx][ny]){
                    if (arr[nx][ny].equals("L")){
                        visited[nx][ny] = true;
                        q.add(new Pos(nx, ny, nCnt));
                        time = Math.max(time, nCnt);
                    }
                }
            }
        }

        return time;
    }
}

class Pos{
    int x;
    int y;
    int cnt;

    Pos(int x, int y, int cnt){
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}
