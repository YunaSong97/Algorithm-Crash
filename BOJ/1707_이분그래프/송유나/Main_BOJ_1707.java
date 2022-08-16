import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node {
    int x, y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Main_BOJ_1707 {
    static boolean ans;
    static ArrayList<Integer>[] arr;
    static Queue<Integer> queue;

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            ans = true;
            int v = sc.nextInt();
            int e = sc.nextInt();
            queue = new LinkedList<>();

            arr = new ArrayList[v + 1];
            for (int j = 1; j <= v; j++) {
                arr[j] = new ArrayList<Integer>();
            }

            int[] visit = new int[v + 1];

            for (int j = 0; j < e; j++) {
                int a = sc.nextInt();
                int b = sc.nextInt();

                arr[a].add(b);
                arr[b].add(a);
            }

            for(int j=1;j<=v;j++) {
                bfs(j, visit, arr);
            }

            if (ans) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    static void bfs(int idx, int[] visit, ArrayList<Integer>[] edges) {
        if(visit[idx]==0) {
            queue.add(idx);

            visit[idx] = 1;
        }

        while (!queue.isEmpty()) {
            int i = queue.poll();
            ArrayList<Integer> now = edges[i];

            for (int n : now) {
                if (visit[n] == 0) {
                    if (visit[i] == 1) {
                        visit[n] = 2;
                    } else if (visit[i] == 2) {
                        visit[n] = 1;
                    }
                    queue.add(n);
                } else {
                    if (visit[n] == visit[i]) {
                        ans = false;
                        return;
                    }
                }
            }
        }
    }
}
