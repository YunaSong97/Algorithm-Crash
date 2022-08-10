import java.util.Scanner;

class Main_BOJ_9663 {
    static int n;
    static int ans;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        boolean[] visit = new boolean[n];
        int[] res = new int[n];

        dfs(res, visit, 0);

        System.out.println(ans);
    }

    static void dfs(int[] res, boolean[] visit, int idx) {
        if (idx == n) {

            if (!check(n, res)) {
                return;
            }

            ans++;

            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                res[idx] = i;

                if (!check(idx, res)) {
                    return;
                }

                visit[i] = true;
                dfs(res, visit, idx + 1);
                visit[i] = false;
            }
        }
    }

    static boolean check(int n, int[] res) {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(res[i] - res[j]) == j - i) {
                    return false;
                }
            }
        }
        return true;
    }
}
