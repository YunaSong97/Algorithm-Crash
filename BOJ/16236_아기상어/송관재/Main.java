import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static int shark = 2;
    static int[][] arr;
    static int[][] visit;
    static int N;
    static int x;
    static int y;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int dist = 0;
    static int count = 0;
    static Queue<int[]> q = new LinkedList<>();
    static ArrayList<int[]> list = new ArrayList<>();
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        arr = new int[N][N];
        visit = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                arr[i][j] = s.nextInt();
                if(arr[i][j] == 9) {
                    y = i;
                    x = j;
                    arr[i][j] = 0;
                }
            }
        }
        while(bfs()) {
            initVisit();
        }
        System.out.println(dist);
    }
    
    public static boolean bfs() {
        q.add(new int[]{y, x, 0});
        visit[y][x] = 1;
        while(!q.isEmpty()) {
            int[] node = q.poll();
            int i = node[0];
            int j = node[1];
            int d = node[2];
            for(int k = 0; k < 4; k++) {
                int ni = i + dy[k];
                int nj = j + dx[k];
                int nd = d + 1;
                if(ni < 0 || ni >= N || nj < 0 || nj >= N || arr[ni][nj] > shark || visit[ni][nj] == 1) {
                    continue;
                } else {
                    int[] newNode = {ni, nj, nd};
                    if(arr[ni][nj] == shark || arr[ni][nj] == 0) {
                        visit[ni][nj] = 1;
                        q.add(newNode);
                    }
                    if(arr[ni][nj] < shark && arr[ni][nj] != 0) {
                        if(list.size() == 0) {
                            visit[ni][nj] = 1;
                            q.add(newNode);
                            list.add(newNode);
                        } else {
                            int least = Integer.MAX_VALUE;
                            for(int[] temp : list) {
                                if(least > temp[2]) {
                                    least = temp[2];
                                }
                            }
                            if(nd > least) {
                                continue;
                            } else {
                                visit[ni][nj] = 1;
                                q.add(newNode);
                                list.add(newNode);
                            }
                        }
                    }
                }
            }
        }
        if(list.size() == 0) {
            return false;
        } else {
            int least = Integer.MAX_VALUE;
            int li = 0;
            int lj = 0;
            for(int[] node : list) {
                if(least > node[2]) {
                    li = node[0];
                    lj = node[1];
                    least = node[2];
                } else if (least == node[2]){
                    if(node[0] < li) {
                        li = node[0];
                        lj = node[1];
                    } else if(node[0] == li && node[1] < lj) {
                        li = node[0];
                        lj = node[1];
                    }
                }
            }
            dist += least;
            arr[li][lj] = 0;
            y = li;
            x = lj;
            count++;
            if(count == shark) {
                shark++;
                count = 0;
            }
            list.clear();
            return true;//먹을것 없으면 false 있으면 true
        }
    }
    
    public static void initVisit() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                visit[i][j] = 0;
            }
        }
    }
}
