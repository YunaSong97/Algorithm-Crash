import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long answer = -1;
    static int count = 9;
    static int N;
    static int[] arr = new int[10];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        if(N < 10) {
            answer = N;
        } else {
            for(int i=1; i<10; i++) {
                for(int j=1; j<10; j++) {
                    solve(i, j, i);
                }
            }
        }
        System.out.println(answer);
    }

    public static void solve(int depth, int prev, int start) {
        arr[depth] = prev;
        if(depth == 0) {
            count++;
            if(count == N) {
                //answer
                long temp = 0;
                for(int i = start; i>=0; i--) {
                    temp *= 10;
                    temp += arr[i];
                }
                answer = temp;
                return;
            }
            return;
        }
        for(int i=0; i<=9; i++) {
            if(prev > i) {
                solve(depth - 1, i, start);
            }
        }
    }
}
