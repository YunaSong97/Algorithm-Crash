import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int N;
    static int[][] map;
    static int[] dy = new int[] {0, 1, 0, -1};
    static int[] dx = new int[] {1, 0, -1, 0};
    static boolean[][] visited;
    static ArrayList<Info> info;
    static Shark shark;
    static int ans;
    public static void main(String[] args) throws Exception{
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0, index = 0; j < N; j++, index += 2) {
                map[i][j] = line.charAt(index) - '0';
                if (map[i][j] == 9) {
                    shark = new Shark(i, j, 2, 0);
                }
            }
        }

        while(true) {
            visited = new boolean[N][N];
            visited[shark.y][shark.x] = true;
            info = new ArrayList<>();
            int cnt = bfs(shark.y, shark.x);
            //System.out.println(cnt);
            //System.out.println("level: " + shark.level + " " + "exp: " + shark.exp + " ans: " + ans);
            if (cnt == 0) break;

            //info배열에 저장된 먹이 후보들중에서 조건에 맞는 물고기를 찾아 먹는다
            if (cnt == 1) {
                Info i = info.get(0);
                ans += i.time;
                shark.exp++;
                if (shark.exp == shark.level) {
                    shark.level++;
                    shark.exp = 0;
                }
                map[shark.y][shark.x] = 0;
                map[i.y][i.x] = 9;
                shark.y = i.y;
                shark.x = i.x;
            } else if (cnt > 1) {
                // 가장 가까운 거리의 물고기만 먹는다
                int min = Integer.MAX_VALUE;
                for (Info i : info) {
                    if (min > i.time) min = i.time;
                }

                ArrayList<Info> tmp = new ArrayList<>();
                for (Info i : info) {
                    if (min == i.time)
                        tmp.add(i);
                }

                if (tmp.size() == 1) {
                    Info i = tmp.get(0);
                    ans += i.time;
                    shark.exp++;
                    if (shark.exp == shark.level) {
                        shark.level++;
                        shark.exp = 0;
                    }
                    map[shark.y][shark.x] = 0;
                    map[i.y][i.x] = 9;
                    shark.y = i.y;
                    shark.x = i.x;
                } else if (tmp.size() > 1) {
                    // 가장 윗쪽에 물고기를 먹는다
                    ArrayList<Info> tmp2 = new ArrayList<>(); // y가 가장 높은 부분의 물고기만 가지고있음.
                    int minY = Integer.MAX_VALUE;
                    int minX = Integer.MAX_VALUE;
                    int idx = 0;

                    for (Info i : tmp) {
                        if (i.y < minY) minY = i.y;
                        if (i.x < minX) minX = i.x;
                    }

                    for (Info i : tmp) {
                        if (minY == i.y) tmp2.add(i);
                    }

                    if (tmp2.size() == 1) {
                        idx = 0;
                    } else if(tmp2.size() > 1){
                        Collections.sort(tmp2);
                    }
                    Info i = tmp2.get(0);
                    //System.out.println(i.y + " " + i.x);
                    ans += i.time;
                    shark.exp++;
                    if (shark.exp == shark.level) {
                        shark.level++;
                        shark.exp = 0;
                    }
                    map[shark.y][shark.x] = 0;
                    map[i.y][i.x] = 9;
                    shark.y = i.y;
                    shark.x = i.x;
                }
            }
        }

        System.out.println(ans);
    }

    // case 1: 물고기가 없는 경우 엄마상어를 부른다
    // case 2: 먹을 수 있는 물고기가 한마리만 있는 경우 해당 위치로 가서 물고기먹고 exp++
    // case 3: 먹을 수 있는 물고기가 여럿이라면
    // case 3-1: 가장 가까운 물고기를 먹는다
    // case 3-2: 가장 가까운 물고기가 여럿이라면 가장 위쪽 물고기를 먹는다
    // case 3-3: 가장 위쪽 물고기가 여럿이라면 가장 왼쪽 물고기를 먹는다
    static int bfs(int y, int x) {
        Queue<Info> Q = new LinkedList<>();

        Q.offer(new Info(y, x, 0));

        while(!Q.isEmpty()) {
            Info cur = Q.poll();

            for (int i = 0; i < 4; i++) {
                int nY = cur.y + dy[i];
                int nX = cur.x + dx[i];

                if (nY < 0 || nY >= N || nX < 0 || nX >= N) continue;
                // 아기상어보다 더 큰 경우는 못지나간다
                if (map[nY][nX] > shark.level) continue;
                if (visited[nY][nX]) continue;

                visited[nY][nX] = true;
                if (map[nY][nX] > 0 && map[nY][nX] < shark.level) {
                    info.add(new Info(nY, nX, cur.time + 1));
                    continue;
                }
                Q.offer(new Info(nY, nX, cur.time + 1));
            }
        }

        return info.size();
    }

    static class Shark {
        int y;
        int x;
        int level;
        int exp;

        public Shark(int y, int x, int level, int exp) {
            super();
            this.y = y;
            this.x = x;
            this.level = level;
            this.exp = exp;
        }
    }

    static class Info implements Comparable{
        int y;
        int x;
        int time;

        public Info(int y, int x, int time) {
            super();
            this.y = y;
            this.x = x;
            this.time = time;
        }

        @Override
        public int compareTo(Object o) {
            Info i = (Info)o;
            // TODO Auto-generated method stub
            return Integer.compare(this.x, i.x);
        }
    }
}
