import java.util.Scanner;

public class Problem2573 {
    static int N;
    static int M;
    static int[][] arr;
    static int[][] visit;
    static int count = 1;
    static int run = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        M = s.nextInt();
        arr = new int[N][M];
        visit = new int[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                arr[i][j] = s.nextInt();
            }
        }
        int year = 0;
        while(count == 1) {
            count = 0;
            melt();
            year++;
            for(int i=1; i<N-1; i++) {
                for(int j=1; j<M-1; j++) {
                    dfs(i, j);
                    if(run > 0) {
                        run = 0;
                        count++;
                    }
                }
            }
        }
        if(count == 0) {
            System.out.println(0);
        } else {
            System.out.println(year);
        }
    }
    
    public static void dfs(int y, int x) {
        if(arr[y][x] == 0 || visit[y][x] == 1) {
            return;
        }
        run++;
        visit[y][x] = 1;
        for(int i=0; i<4; i++) {
            dfs(y + dy[i], x + dx[i]);
        }
    }
    
    public static void melt() {
        for(int i=1; i<N - 1; i++) {
            for(int j=1; j<M - 1; j++) {
                visit[i][j] = 0;
                if(arr[i][j] != 0) {
                    int melt = 0;
                    for(int k=0; k<4; k++) {
                        if(arr[i + dy[k]][j + dx[k]] == 0) {
                            melt++;
                        }
                    }
                    arr[i][j] -= melt;
                    if(arr[i][j] == 0) {
                        arr[i][j] = -1;
                    }
                }
            }
        }
        for(int i=1; i<N - 1; i++) {
            for(int j=1; j<M - 1; j++) {
                if(arr[i][j] < 0) {
                    arr[i][j] = 0;
                }
            }
        }
    }
}
