import java.util.Scanner;

public class Problem17070 {
    static int N;
    static int[][] map;
    static int[][][] dp;
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        map = new int[N][N];
        dp = new int[N][N][3];// 0일 때 가로, 1일 때 대각선, 2일 때 세로
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = s.nextInt();
            }
        }
        int answer = 0;
        dp[0][1][0] = 1;
        for(int i=0; i<N; i++) {
            for(int j=2; j<N; j++) {
                if(map[i][j] == 0) {
                    dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][1];
                    if(i != 0) {
                        dp[i][j][2] = dp[i-1][j][2] + dp[i-1][j][1];
                        if(map[i-1][j] == 0 && map[i][j-1] == 0) {
                            dp[i][j][1] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
                        }
                    }
                }
            }
        }
        answer = dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2];
        System.out.println(answer);
    }
}
