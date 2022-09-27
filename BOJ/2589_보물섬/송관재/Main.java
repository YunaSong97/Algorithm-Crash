import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static char[][] map;
    static int[][] visit;
    static int[] dy = { -1, 0, 1, 0 };
    static int[] dx = { 0, -1, 0, 1 };
    static ArrayList<int[]> lastNode;
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visit = new int[N][M];
        lastNode = new ArrayList<>();
        String str = "";
        for (int i = 0; i < N; i++) {
            str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L') {
                    bfs(i, j);
                }
            }
        }
        for (int[] node : lastNode) {
            visit = new int[N][M];
            solve(node[0], node[1]);
        }
        System.out.println(answer);
    }

    public static void bfs(int i, int j) {
        Queue<int[]> q = new ArrayDeque<>();
        int[] node = new int[3];
        node[0] = i;
        node[1] = j;
        node[2] = 0;
        visit[i][j] = 1;
        q.add(node);
        while (!q.isEmpty()) {
            node = q.poll();
            int y = node[0];
            int x = node[1];
            int d = node[2];
            boolean flag = true;
            for (int k = 0; k < 4; k++) {
                int ni = y + dy[k];
                int nj = x + dx[k];
                int nd = d + 1;
                if (ni < 0 || ni >= N || nj < 0 || nj >= M || visit[ni][nj] == 1) {
                    continue;
                }
                if (map[ni][nj] == 'L') {
                    flag = false;
                    int[] newNode = new int[3];
                    newNode[0] = ni;
                    newNode[1] = nj;
                    newNode[2] = nd;
                    answer = Math.max(answer, nd);
                    visit[ni][nj] = 1;
                    q.add(newNode);
                }
            }
            if (flag) {
                int[] tempNode = new int[2];
                tempNode[0] = y;
                tempNode[1] = x;
                lastNode.add(tempNode);
            }
        }
    }

    public static void solve(int i, int j) {
        Queue<int[]> q = new ArrayDeque<>();
        int[] node = new int[3];
        node[0] = i;
        node[1] = j;
        node[2] = 0;
        visit[i][j] = 1;
        q.add(node);
        while(!q.isEmpty()) {
            node = q.poll();
            int y = node[0];
            int x = node[1];
            int d = node[2];
            for(int k=0; k<4; k++) {
                int ni = y + dy[k];
                int nj = x + dx[k];
                int nd = d+1;
                if(ni<0 || ni >=N || nj<0 || nj>=M || visit[ni][nj] == 1) {
                    continue;
                }
                if(map[ni][nj] == 'L') {
                    int[] newNode = new int[3];
                    newNode[0] = ni;
                    newNode[1] = nj;
                    newNode[2] = nd;
                    answer = Math.max(answer, nd);
                    visit[ni][nj] = 1;
                    q.add(newNode);
                }
            }
        }
    }
}
