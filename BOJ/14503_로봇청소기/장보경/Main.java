import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static boolean[][] visited;
    static int cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];
        dfs(r, c, d);
        System.out.println(cnt);
    }

    static int[] X = {-1, 0, 1, 0};
    static int[] Y = {0, 1, 0, -1};

    public static void dfs(int r, int c, int d){
        if (arr[r][c] == 0 && !visited[r][c]){
            cnt++;
        }
        visited[r][c] = true;

        int backCnt = 0;
        for (int i=0; i<4; i++){
            d = (d+3)%4;
            int nx = r+X[d];
            int ny = c+Y[d];

            if (nx>-1 && nx<N && ny>-1 && ny<M){
                if (!visited[nx][ny] && arr[nx][ny] == 0){
                    dfs(nx, ny, d);
                    return;
                } else {
                    backCnt++;
                }
            } else{
                backCnt++;
            }
        }

        if (backCnt >= 4){
            int back = (d+2)%4;
            int bx = r + X[back];
            int by = c + Y[back];

            if(bx>-1 && bx<N && by>-1 && by<M && arr[bx][by] != 1) {
                dfs(bx, by, d);
            }
        }
    }
}
