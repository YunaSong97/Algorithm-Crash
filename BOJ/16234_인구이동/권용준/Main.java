import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, L, R;
    static int[][] map;
    static boolean visited[][];
    static int dx[] = new int[]{0, 1, 0, -1};
    static int dy[] = new int[]{-1, 0, 1, 0};
    static ArrayList<int []> nodeList;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;

        while (true) {
            boolean isMove = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        int sum = bfs(new int[]{i, j});
                        if (nodeList.size() > 1) {
                            isMove = true;
                            updatePopulation(sum);
                        }
                    }
                }
            }
            if (!isMove) {
                System.out.println(day);
                break;
            }

            day++;

            visited = new boolean[N][N];
        }
    }

    static public int bfs(int[] start) {
        Queue<int[]> queue = new LinkedList<>();
        nodeList = new ArrayList<>();

        int sum = 0;
        queue.add(start);

        int tmp[];
        int dirX, dirY;

        while(!queue.isEmpty()) {
            tmp = queue.poll();

            if (visited[tmp[0]][tmp[1]])
                continue;

            visited[tmp[0]][tmp[1]] = true;
            nodeList.add(tmp);
            sum += map[tmp[0]][tmp[1]];
            for (int i = 0; i < 4; i++) {
                dirX = tmp[1] + dx[i];
                dirY = tmp[0] + dy[i];

                if (dirX >= 0 && dirX < N && dirY >= 0 && dirY < N && !visited[dirY][dirX]) {
                    int diff = Math.abs(map[tmp[0]][tmp[1]] - map[dirY][dirX]);
                    if (diff >= L && diff <= R) {
                        queue.offer(new int[] {dirY, dirX});
                    }
                }
            }
        }
        return sum;
    }

    static public void updatePopulation(int sum) {

        int updatedPop = sum / nodeList.size();
        int tmp[];

        for (int i = 0; i < nodeList.size(); i++) {
            tmp = nodeList.get(i);
            map[tmp[0]][tmp[1]] = updatedPop;
        }
    }
}
