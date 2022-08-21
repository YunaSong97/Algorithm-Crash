package BOJ;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

class Node implements Comparable<Node> {
    int x, y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Node o) {
        //거리가 가장 짧은것, x가 작은것, y가 작은 것
        if (this.x == o.x) {
            return this.y - o.y;
        }
        return this.x - o.x;
    }
}

class Main_BOJ_16236 {
    static int[][] map;
    static int n;
    static int ans;
    static int time;
    static int fish;
    static Node now;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ans = 0;
        n = sc.nextInt();
        map = new int[n][n];
        fish = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 9) {
                    now = new Node(i, j);
                } else if (map[i][j] != 0) {
                    fish++;
                }
            }
        }


        map[now.x][now.y] = 0;
        bfs(now);
        System.out.println(ans);
    }

    static void bfs(Node node) {
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};

        boolean[][] visit = new boolean[n][n];

        if (fish == 0) {
            return;
        }

        time = 1;
        int size = 2;
        int cnt = 0;

        Queue<Node> queue = new ArrayDeque<>();
        PriorityQueue<Node> temp = new PriorityQueue<>();

        visit[node.x][node.y] = true;
        queue.add(node);

        while (!queue.isEmpty()) {
            int qSize = queue.size();

            while (qSize-- > 0) {

                now = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if (!isIn(nx, ny) || visit[nx][ny] || map[nx][ny] > size) {
                        continue;
                    }

                    if (map[nx][ny] > 0 && map[nx][ny] < size) {
                        temp.add(new Node(nx, ny));
                    }

                    visit[nx][ny] = true;
                    queue.add(new Node(nx, ny));
                }
            }
            if (!temp.isEmpty()) {
                //우선순위가 가장 높은것 poll
                now = temp.poll();
                map[now.x][now.y] = 0;

                //먹은지점에서 새로 시작
                visit = new boolean[n][n];
                queue.clear();
                temp.clear();

                //먹었으면
                fish--;
                ans = time;
                cnt++;
                visit[now.x][now.y] = true;
                queue.add(new Node(now.x, now.y));

                if (size == cnt) {
                    size++;
                    cnt = 0;
                }
            }

            if (fish == 0) {
                return;
            }

            time++;
        }
    }

    static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }
}
