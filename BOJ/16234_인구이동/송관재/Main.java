import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static int N;
    static int L;
    static int R;
    static int[][] map;
    static int[][] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int count = 0;
        while(true) {
            visit = new int[N][N];
            int v = 1;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(visit[i][j] == 0) {
                        if(bfs(i, j, v)) {
                            v++;
                        }
                    }
                }
            }
            boolean flag = false;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(visit[i][j] != 0) {
                        flag = true;
                    }
                }
            }
            if(flag) {
                int[] sum = new int[v];
                int[] countryCount = new int[v];
                for(int i=0; i<N; i++) {
                    for(int j=0; j<N; j++) {
                        sum[visit[i][j]] += map[i][j];
                        countryCount[visit[i][j]]++;
                    }
                }
                for(int i=1; i<v; i++) {
                    sum[i] /= countryCount[i];
                }
                for(int i=0; i<N; i++) {
                    for(int j=0; j<N; j++) {
                        if(visit[i][j] != 0) {
                            map[i][j] = sum[visit[i][j]];
                        }
                    }
                }
                count++;
            } else {
                break;
            }
        }
        System.out.println(count);
    }
    
    public static boolean bfs(int i, int j, int v) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        int[] node = new int[2];
        node[0] = i;
        node[1] = j;
        visit[i][j] = v;
        q.add(node);
        int count = 0;
        while(!q.isEmpty()) {
            node = q.poll();
            int y = node[0];
            int x = node[1];
            count++;
            for(int k=0; k<4; k++) {
                int ny = y + dy[k];
                int nx = x + dx[k];
                if(ny < 0 || ny >= N || nx < 0 || nx >= N || visit[ny][nx] != 0) {
                    continue;
                } else {
                    int diff = Math.abs(map[y][x] - map[ny][nx]);
                    if(diff >= L && diff <= R) {
                        visit[ny][nx] = v;
                        int[] newNode = new int[2];
                        newNode[0] = ny;
                        newNode[1] = nx;
                        q.add(newNode);
                    }
                }
            }
        }
        if(count == 1) {
            visit[i][j] = 0;
            return false;
        } else {
            return true;
        }
    }

}
