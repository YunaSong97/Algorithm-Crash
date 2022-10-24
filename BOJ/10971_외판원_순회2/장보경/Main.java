import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    static boolean[] visited;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = Integer.MAX_VALUE;
        visited = new boolean[N];
        // 어디서 시작하든 사이클이 돼야 하니까 그냥 0에서 시작
        visited[0] = true;
        perm(1, 0, 0);
        
        System.out.println(ans);
    }

    static void perm(int cnt, int first, int sum){
        if (cnt == N){
        	if(arr[first][0] != 0) {
        		ans = Math.min(ans, sum+arr[first][0]);
        	}
            return;
        }

        for (int i=0; i<N; i++){
            if (!visited[i]){
                if (arr[first][i] == 0) continue;

                visited[i] = true;
                perm(cnt+1, i, sum+arr[first][i]);
                visited[i] = false;
            }
        }
    }
}
