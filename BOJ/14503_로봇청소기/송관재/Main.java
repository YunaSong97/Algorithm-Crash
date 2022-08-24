import java.util.Scanner;

public class Main {
    static int N;
    static int M;
    static int direction;
    static int[][] arr;
    static int[][] visit;
    static int count = 0;
    static int y;
    static int x;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        M = s.nextInt();
        y = s.nextInt();
        x = s.nextInt();
        direction = s.nextInt();
        arr = new int[N][M];
        visit = new int[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                arr[i][j] = s.nextInt();
            }
        }
        solve();
        System.out.println(count);
    }

    public static void solve() {
        if(visit[y][x] == 0) {
            count++;
            visit[y][x] = 1;
        }
        boolean flag = true;
        int temp = direction;
        while(flag) {
            temp--;
            if(temp < 0) {
                temp += 4;
            }
            int ty = y + dy[temp];
            int tx = x + dx[temp];
            if(arr[ty][tx] == 1 || visit[ty][tx] == 1) {
                if(temp == direction) {
                    temp -= 2;
                    if(temp < 0) {
                        temp += 4;
                    }
                    ty = y + dy[temp];
                    tx = x + dx[temp];
                    if(arr[ty][tx] == 1) {
                        flag = false;
                    } else {
                        y = ty;
                        x = tx;
                        temp = direction;
                    }
                }
            } else {
                y = ty;
                x = tx;
                count++;
                visit[y][x] = 1;
                direction = temp;
            }
        }
    }
}
