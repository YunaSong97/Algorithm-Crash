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

class Solution {
    static boolean ans;
    static ArrayList<Integer>[] arr;
    static Queue<Integer> queue;

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        int v = sc.nextInt();
        int e = sc.nextInt();
        queue = new LinkedList<>();

        arr = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) {
            arr[i] = new ArrayList<Integer>();
            arr[i].add(0);
        }

        for (int i = 0; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            arr[a].add(b);
            arr[b].set(0, arr[b].get(0) + 1);
        }

        for (int i = 1; i <= v; i++) {
            bfs(i);
        }
    }

    static void bfs(int i) {
        if (arr[i].get(0) == 0) {
            queue.add(i);
            System.out.print(i + " ");
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int j = 1; j < arr[now].size(); j++) {
                int n = arr[now].get(j);
                
                if (arr[n].get(0) >= 0) {
                    arr[n].set(0, arr[n].get(0) - 1);

                    if (arr[n].get(0) == 0) {
                        arr[n].set(0, arr[n].get(0) - 1);
                        System.out.print(n + " ");
                        queue.add(n);
                    }
                }
            }
        }
    }
}
