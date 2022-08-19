import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main_BOJ_12100 {
    static int[][] map;
    static int[][] temp;
    static int n;
    static int ans;

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        ans = 0;
        n = sc.nextInt();
        map = new int[n][n];
        temp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                temp[i][j] = map[i][j];
            }
        }

        int[] res = new int[5];
        repPermutation(res, 0, 0);
        
        System.out.println(ans);
    }

    static void repPermutation(int[] res, int depth, int start) {
        
        if (depth == 5) {
            for (int i = 0; i < 5; i++) {
                
                switch (res[i]) {
                case 0:
                    up();
                    break;
                case 1:
                    down();
                    break;
                case 2:
                    left();
                    break;
                case 3:
                    right();
                }
            }
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    ans = Math.max(ans, temp[i][j]);
                    temp[i][j] = map[i][j];
                }
            }

            return;
        }

        for (int i = start; i < 4; i++) {
            res[depth] = i;
            repPermutation(res, depth + 1, 0);
        }
    }

    static void left() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                queue.add(temp[i][j]);
            }
            queue = merge(queue);

            int k = 0;
            while (!queue.isEmpty()) {
                temp[i][k++] = queue.poll();
            }
            for (int j = k; j < n; j++) {
                temp[i][j] = 0;
            }
        }
    }

    static void right() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                queue.add(temp[i][n - 1 - j]);
            }
            queue = merge(queue);

            int k = n - 1;
            while (!queue.isEmpty()) {
                temp[i][k--] = queue.poll();
            }
            for (int j = k; j >= 0; j--) {
                temp[i][j] = 0;
            }
        }
    }

    static void up() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                queue.add(temp[j][i]);
            }
            queue = merge(queue);

            int k = 0;
            while (!queue.isEmpty()) {
                temp[k++][i] = queue.poll();
            }
            for (int j = k; j < n; j++) {
                temp[j][i] = 0;
            }
        }
    }

    static void down() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                queue.add(temp[n - 1 - j][i]);
            }
            queue = merge(queue);

            int k = n - 1;
            while (!queue.isEmpty()) {
                temp[k--][i] = queue.poll();
            }
            for (int j = k; j >= 0; j--) {
                temp[j][i] = 0;
            }
        }
    }

    static Queue<Integer> merge(Queue<Integer> queue) {
        Queue<Integer> resQueue = new LinkedList<>();
        while (!queue.isEmpty()) {
            while (queue.peek() != null && queue.peek() == 0) {
                queue.poll();
            }
            if (queue.isEmpty()) {
                break;
            }
            int now = queue.poll();

            while (queue.peek() != null && queue.peek() == 0) {
                queue.poll();
            }
            if (queue.peek() != null && now == queue.peek()) {
                now *= 2;
                queue.poll();
            }
            resQueue.add(now);
        }

        return resQueue;
    }
}
