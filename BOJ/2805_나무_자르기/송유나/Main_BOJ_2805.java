import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_BOJ_2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Integer> trees = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trees.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(trees);

        long  ans = 0;
        long  low = 0;
        long  high = trees.get(n - 1);
        long  mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            long sum = 0;

            for (int i = 0; i < n; i++) {
                if (trees.get(i) - mid > 0) {
                    sum += (trees.get(i) - mid);
                }
            }

            if (sum < m) {
                high = mid - 1;
            } else {
                ans = mid;
                low = mid + 1;
            }
        }

        System.out.println(ans);
    }
}
