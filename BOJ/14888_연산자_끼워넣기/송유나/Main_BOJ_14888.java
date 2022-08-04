import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer> numList;
    static ArrayList<Integer> opCntList;
    static int k;

    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        numList = new ArrayList<>();
        opCntList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            numList.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 4; i++) {
            opCntList.add(Integer.parseInt(st.nextToken()));
        }

        k = numList.size() - 1;

        int[] visit = new int[k];

        comb(visit, 0, opCntList.get(0), opCntList.get(1), opCntList.get(2), opCntList.get(3));

        System.out.println(max);
        System.out.println(min);
    }

    static int cal(int a, int b, int op) {
        //1 : +, 2: -, 3: *, 4: %
        switch (op) {
            case 1:
                return a + b;
            case 2:
                return a - b;
            case 3:
                return a * b;
            case 4:
                if (a < 0) {
                    return -(-a) / b;
                }
                return a / b;
        }

        return 0;
    }

    static void comb(int[] visit, int n, int a, int b, int c, int d) {
        if (a == 0 && b == 0 && c == 0 && d == 0) {
            int res = cal(numList.get(0), numList.get(1), visit[0]);

            for (int i = 1; i < k; i++) {
                res = cal(res, numList.get(i + 1), visit[i]);
            }

            max = Math.max(max, res);
            min = Math.min(min, res);
            return;
        }

        if (n >= k) {
            return;
        } else {
            if (visit[n] == 0) {
                if (a > 0) {
                    visit[n] = 1;
                    comb(visit, n + 1, a - 1, b, c, d);
                }
                if (b > 0) {
                    visit[n] = 2;
                    comb(visit, n + 1, a, b - 1, c, d);
                }

                if (c > 0) {
                    visit[n] = 3;
                    comb(visit, n + 1, a, b, c - 1, d);
                }

                if (d > 0) {
                    visit[n] = 4;
                    comb(visit, n + 1, a, b, c, d - 1);
                }
                visit[n] = 0;
                comb(visit, n + 1, a, b, c, d);
            }
        }
    }
}

