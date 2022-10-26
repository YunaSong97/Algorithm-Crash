import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int H;
    static int[][] ladder;
    static int answer = -1;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new int[N+1][H+1];
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[b][a] = 1;
        }
        for(int i=0; i<4; i++) {
            if(answer == -1) {
                solve(i, i);
            }
        }
        System.out.println(answer);
    }

    public static void solve(int depth, int start) {
        if(depth == 0) {
            //check condition
            for(int i=1; i<=N; i++) {
                int current = i;
                for(int j=1; j<=H; j++) {
                    if(ladder[current-1][j] == 1) {
                        current--;
                    } else if(ladder[current][j] == 1) {
                        current++;
                    }
                }
                if(current != i) {
                    return;
                }
            }
            answer = start;
            return;
        }
        
        for(int i=1; i<N; i++) {
            for(int j=1; j<=H; j++) {
                if(ladder[i][j] == 0) {
                    if(ladder[i-1][j] == 0 && ladder[i+1][j] == 0) {
                        ladder[i][j] = 1;
                        solve(depth - 1, start);
                        ladder[i][j] = 0;
                    }
                }
            }
        }
    }
}
