import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] arr;
    static int[] visit;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visit = new int[N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=1; i<=N/2; i++) {
            solve(i, 0);
        }
        System.out.println(answer);
    }
    
    public static void solve(int depth, int j) {
        if(depth == 0) {
            int link = 0;
            int start = 0;
            for(int i=0; i<N; i++) {
                for(int k=i+1; k<N; k++) {
                    if(visit[i] == 1 && visit[k] == 1) {
                        link += arr[i][k] + arr[k][i];
                    } else if(visit[i] == 0 && visit[k] == 0) {
                        start += arr[i][k] + arr[k][i];
                    }
                }
            }
            answer = Math.min(answer, Math.abs(link - start));
            return;
        }
        for(int i = j; i<N; i++) {
            if(visit[i] == 0) {
                visit[i] = 1;
                solve(depth-1, i);
                visit[i] = 0;
            }
        }
    }

}
