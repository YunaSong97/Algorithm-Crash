import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main_BOJ_2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<Long> liqList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            liqList.add(Long.parseLong(st.nextToken()));
        }

        Collections.sort(liqList);
        int l = 0;
        int h = n - 1;
        int L = 0;
        int H = n - 1;
        long min = Math.abs(liqList.get(l) + liqList.get(h));

        while (l < h) {
            long sum = liqList.get(l) + liqList.get(h);

            if (sum > 0) {
                if (Math.abs(sum) > min) {
                    break;
                } else {
                    min = Math.abs(sum);
                    L = l;
                    H = h;
                }
                h--;
            } else if (sum < 0) {
                if (Math.abs(sum) > min) {
                    break;
                } else {
                    min = Math.abs(sum);
                    L = l;
                    H = h;
                }
                l++;
            } else {
                break;
            }
        }
        System.out.println(liqList.get(L) + " " + liqList.get(H));
    }
}
