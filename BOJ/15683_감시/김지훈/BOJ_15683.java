import java.util.*;
import java.io.*;

public class BOJ_15683 {
    static int N, M;
    static int[][] map;
    static int cCount;
    static int[] dx = {-1, 0, 1, 0}; //상우하좌
    static int[] dy = {0, 1, 0, -1};
    static int[][] dir = { //초기방향
            {},
            {1}, //1번(북)
            {1, 3},//2번
            {0, 1},//3번
            {0, 1, 3},//4번
            {0, 1, 2, 3}//5번
    };
    static CCTV[] c = new CCTV[8];
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                if (num == 0 || num == 6) continue;
                c[cCount++] = new CCTV(i, j, num);
            }
        }

        dfs(0, map);
        System.out.println(answer);
    }

    static void dfs(int now, int[][] map) {
        if (now == cCount) { //모든 cctv 다돔
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0)
                        cnt++;
                }
            }
            answer = Math.min(answer, cnt);

            return;
        }

        int x = c[now].x;
        int y = c[now].y;
        int num = c[now].num;

        for (int d = 0; d < 4; d++) {
            int[][] copied = copy(map);
            for (int i = 0; i < dir[num].length; i++) {
                int nd = (dir[num][i] + d) % 4;
                int nx = x;
                int ny = y;
                while (true) {  //map을 벗어나거나 벽이면 더이상 못감
                    nx += dx[nd];
                    ny += dy[nd];
                    if (!(nx >= 0 && nx < N && ny >= 0 && ny < M) || map[nx][ny] == 6)
                        break;
                    copied[nx][ny] = -1;
                }
            }
            dfs(now + 1, copied);
        }
    }

    static int[][] copy(int[][] arr) {
        int[][] copied = new int[N][M];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                copied[i][j] = arr[i][j];

        return copied;
    }


}
class CCTV {
    int x;
    int y;
    int num;

    public CCTV(int x, int y, int num) {
        this.x = x;
        this.y = y;
        this.num = num;
    }
}
