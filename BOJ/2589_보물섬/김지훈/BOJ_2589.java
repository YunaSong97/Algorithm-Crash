import java.io.BufferedReader;
import java.util.*;
import java.io.*;

public class BOJ_2589 {
    static int N, M;
    static int dx[] = {-1,0,1,0};
    static int dy[] = {0,1,0,-1};
    static char map[][];
    static boolean v[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max=0;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        v = new boolean[N][M];
        for(int i = 0; i< N; i++) {
            String str = br.readLine();
            for(int j = 0; j< M; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        for(int i = 0; i< N; i++) {
            for(int j = 0; j< M; j++) {
                if(map[i][j] == 'L') {
                    v = new boolean[N][M];
                    int result = bfs(i,j);
                    max = Math.max(max, result);
                }
            }
        }
        System.out.println(max);

    }
    static int bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        int count = 0;
        queue.add(new Point(x,y,0));
        while(!queue.isEmpty()) {
            Point p = queue.poll();
            v[x][y] = true;
            for(int d=0; d<4; d++) {
                int nx = p.x + dy[d];
                int ny = p.y + dx[d];
                if(0<=nx && 0<=ny && nx<N && ny< M && !v[nx][ny] && map[nx][ny]=='L') {
                    v[nx][ny] = true;
                    queue.add(new Point(nx, ny, p.c+1));
                    count = Math.max(count, p.c+1);
                }
            }
        }
        return count;
    }

}
class Point {
    int x;
    int y;
    int c;
    public Point(int x, int y, int c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }
}
