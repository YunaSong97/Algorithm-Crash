import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static boolean[] visited;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[N];
        ans = Integer.MIN_VALUE;
        perm(0, new int[N]);

        System.out.println(ans);
    }

    static void perm(int cnt, int[] tmp){
        if (cnt == N){
            int sum = 0;
            for (int i=0; i<N-1; i++){
                sum += Math.abs(tmp[i]-tmp[i+1]);
            }

            ans = Math.max(ans, sum);
            return;
        }

        for (int i=0; i<N; i++){
            if (!visited[i]){
                visited[i] = true;
                tmp[cnt] = arr[i];
                perm(cnt+1, tmp);
                visited[i] = false;
            }
        }
    }
}
