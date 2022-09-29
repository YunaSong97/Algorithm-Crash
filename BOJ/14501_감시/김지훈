import java.io.*;
import java.util.*;

public class BOJ_14501 {
    static int t[];
    static int p[];
    static int N;
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        t = new int[N];
        p = new int[N];

        for(int i = 0; i< N; i++) {
            StringTokenizer st  = new StringTokenizer(br.readLine());

            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0,0);

        System.out.println(ans);

    }

    static void dfs(int day, int sum) {
        if(day == N) {
            if(ans < sum) ans = sum;
            return;
        }
        if(day > N) return;
        dfs(day + t[day], sum+p[day]);
        dfs(day+1, sum);

    }
}
