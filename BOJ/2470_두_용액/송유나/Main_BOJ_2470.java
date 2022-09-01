import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_BOJ_2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> liqList = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            liqList.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(liqList);
        int l = 0;
        int h = n - 1;
        int L = 0;
        int H = n - 1;
        int min = Math.abs(liqList.get(l) + liqList.get(h));

        while (l < h) {
            int sum = liqList.get(l) + liqList.get(h);

            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                L = l;
                H = h;
            }
            if (sum > 0) {
                h--;
            } else if (sum < 0) {
                l++;
            } else {
                break;
            }
        }
        System.out.println(liqList.get(L) + " " + liqList.get(H));
    }
}
