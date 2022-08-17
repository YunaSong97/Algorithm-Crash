import java.util.ArrayList;
import java.util.Scanner;

class Node {
    int x, y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getDist(Node node) {
        int xDist = Math.abs(this.x - node.x);
        int yDist = Math.abs(this.y - node.y);
        
        return xDist + yDist;
    }
}

class Main {
    static int[][] map;
    static ArrayList<Node> chicken;
    static ArrayList<Node> home;
    static int ans;
    static int m;
    static int n;
    static boolean[] visit;
    static int[][] chickenDist;

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        ans = 99999;
        map = new int[n][n];
        chicken = new ArrayList<>();
        home = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 2) {
                    chicken.add(new Node(i, j));
                }

                if (map[i][j] == 1) {
                    home.add(new Node(i, j));
                }
            }
        }

        int cCnt = chicken.size();
        int hCnt = home.size();
        visit = new boolean[cCnt];
        chickenDist = new int[hCnt][cCnt];

        for (int i = 0; i < hCnt; i++) {
            for (int j = 0; j < cCnt; j++) {
                chickenDist[i][j] = home.get(i).getDist(chicken.get(j));
            }
        }

        chooseChicken(hCnt, cCnt, 0, m);

        System.out.println(ans);
    }

    static void chooseChicken(int hCnt, int cCnt, int idx, int r) {
        if (r == 0) {

            int dist = 0;
            for (int i = 0; i < hCnt; i++) {
                
                int min = 9999;
                
                for (int j = 0; j < cCnt; j++) {
                    if (visit[j]) {
                        min = Math.min(min, chickenDist[i][j]);
                    }
                }
                dist += min;
            }

            ans = Math.min(ans, dist);
            return;
        }

        for (int i = idx; i < cCnt; i++) {
            visit[i] = true;
            chooseChicken(hCnt, cCnt, i + 1, r - 1);
            visit[i] = false;
        }
    }
}
