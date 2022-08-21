import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node implements Comparable<Node> {
    int x, y;
    int emptyCnt;
    int likeCnt;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Node o) {
        //빈칸 중 좋아하는 학생이 많은것 -> 빈칸이 많은 것 -> x 작은 것 -> y 작은 것
        if (this.likeCnt == o.likeCnt) {
            if (this.emptyCnt == o.emptyCnt) {
                if (this.x == o.x) {
                    return this.y - o.y;
                }
                return this.x - o.x;
            }
            return o.emptyCnt - this.emptyCnt;
        }
        return o.likeCnt - this.likeCnt;
    }
}

public class Main_BOJ_21608 {
    static int[][] map;
    static int n;
    static ArrayList<Integer>[] likeList;
    static PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
    static Scanner sc;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) {
        sc = new Scanner(System.in);

        n = sc.nextInt();

        likeList = new ArrayList[n * n + 1];
        map = new int[n][n];

        for (int i = 0; i < n * n; i++) {
            likeList[i + 1] = new ArrayList<>();
        }

        placeStudent();

        System.out.println(calScore());
    }

    static void placeStudent() {
        for (int i = 0; i < n * n; i++) {
            int now = sc.nextInt();

            if (i == 0) {
                map[1][1] = now;
                for (int j = 0; j < 4; j++) {
                    likeList[now].add(sc.nextInt());
                }
                continue;
            }

            for (int j = 0; j < 4; j++) {
                likeList[now].add(sc.nextInt());
            }

            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    if (map[x][y] == 0) {
                        Node Seat = new Node(x, y);
                        checkSide(Seat, now);
                    }
                }
            }

            Node nextSeat = priorityQueue.poll();
            map[nextSeat.x][nextSeat.y] = now;
            priorityQueue.clear();
        }
    }

    static int calScore() {
        int score = 0;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                score = 0;

                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (isOut(nx, ny)) {
                        continue;
                    }

                    if (likeList[map[i][j]].contains(map[nx][ny])) {
                        score++;
                    }

                }

                switch (score) {
                    case 1:
                        sum += 1;
                        break;
                    case 2:
                        sum += 10;
                        break;
                    case 3:
                        sum += 100;
                        break;
                    case 4:
                        sum += 1000;
                }
            }
        }

        return sum;
    }

    static void checkSide(Node node, int student) {
        int empty = 0;
        int like = 0;

        for (int i = 0; i < 4; i++) {
            int nx = node.x + dx[i];
            int ny = node.y + dy[i];

            if (isOut(nx, ny)) {
                continue;
            }

            if (map[nx][ny] == 0) {
                empty++;
            } else {
                if (likeList[student].contains(map[nx][ny])) {
                    like++;
                }
            }
        }

        node.emptyCnt = empty;
        node.likeCnt = like;
        priorityQueue.add(node);
    }

    static boolean isOut(int x, int y) {
        return x < 0 || y < 0 || x >= n || y >= n;
    }
}
