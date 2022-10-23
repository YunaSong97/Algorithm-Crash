import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static int[] arr2;
    static int[] visit;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        arr2 = new int[N];
        visit = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        solve(0);
        System.out.println(answer);
    }
    
    public static void solve(int depth) {
        if(depth == N) {
            int temp = 0;
            for(int i=1; i<N; i++) {
                temp += Math.abs(arr2[i] - arr2[i-1]);
            }
            answer = Math.max(answer, temp);
        }
        for(int i=0; i<N; i++) {
            if(visit[i] == 0) {
                arr2[depth] = arr[i];
                visit[i] = 1;
                solve(depth + 1);
                visit[i] = 0;
            }
        }
    }
}
