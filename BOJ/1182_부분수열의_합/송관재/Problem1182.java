import java.util.Scanner;

public class Problem1182 {
    static int[] arr;
    static int N;
    static int S;
    static int answer = 0;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        S = s.nextInt();
        arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = s.nextInt();
        }
        dfs(0, 0);
        if(S == 0) {
            answer--;
        }
        System.out.println(answer);
    }
    
    public static void dfs(int depth, int sum) {
        if(depth == N) {
            if(sum == S) {
                answer++;
            }
            return;
        }
        dfs(depth + 1, sum + arr[depth]);
        dfs(depth + 1, sum);
    }
}
