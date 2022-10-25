import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] adjArr;
    static int[] visit;
    static int[] arr;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        visit = new int[N];
        arr = new int[N];
        adjArr = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                adjArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve(0);
        System.out.println(answer);
    }
    
    public static void solve(int depth) {
        if(depth == N) {
            //calc
            int sum = 0;
            for(int i=1; i<N; i++) {
                if(adjArr[arr[i-1]][arr[i]] == 0) {
                    return;
                } else {
                    sum += adjArr[arr[i-1]][arr[i]];
                }
            }
            if(adjArr[arr[N-1]][arr[0]] == 0) {
                return;
            } else {
                sum += adjArr[arr[N-1]][arr[0]];
            }
            answer = Math.min(answer, sum);
            return;
        }
        for(int i=0; i<N; i++) {
            if(visit[i] == 0) {
                arr[depth] = i;
                visit[i] = 1;
                solve(depth + 1);
                visit[i] = 0;
            }
        }
    }
}
