import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Problem2667 {
    static int N = 0;
    static int[][] map;
    static int[][] visit;
    static int count = 0;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        N = s.nextInt();
        s.nextLine();
        map = new int[N][N];
        visit = new int[N][N];
        for (int i = 0; i < N; i++) {
            String str = s.nextLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                count = 0;
                dfs(i, j);
                if(count != 0) {
                    result.add(count);
                }
            }
        }
        Collections.sort(result);
        System.out.println(result.size());
        for(int i : result) {
            System.out.println(i);
        }
    }
    
    public static void dfs(int x, int y) {
        if(x < 0 || y < 0 || x > N-1 || y > N-1 || map[y][x] == 0 || visit[y][x] == 1) {
            return;
        }
        
        if(map[y][x] == 1) {
            count++;
            visit[y][x] = 1;
            for(int i=0; i<4; i++) {
                dfs(x + dx[i], y + dy[i]);
            }
        }
    }
}
