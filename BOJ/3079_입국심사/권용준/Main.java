import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static long ans;
    static int[] times;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        times = new int[N];

        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(br.readLine());
        }

        ans = bSearch();

        System.out.println(ans);
    }

    static long bSearch() {
        long left = 1;
        long right = 1_000_000_000_000_000_000L;
        long res = 0;
        long mid = 0;
        while(left <= right) {
            mid = (left + right) / 2;
            long d = 0;
            for (int i = 0; i < N; i++) {
                d += mid / times[i];
                if (d >= M) break;
            }
            // true이면 탐색범위(소요되는 시간이 너무 짧은경우)
            if (d >= M) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}

