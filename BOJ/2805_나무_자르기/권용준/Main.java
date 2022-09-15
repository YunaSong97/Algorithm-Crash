import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] trees;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("SDS/firstweek/daytwo/prob2805/input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        trees = new int[N];

        st = new StringTokenizer(bf.readLine());
        int tMax = 0;

        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            tMax = Math.max(tMax, trees[i]);
        }

        //Arrays.sort(trees); // 이 부분 최적화 가능...

        int left = 0;
        int right = tMax;
        int h = 0;

        while(left <= right) {
            long namu = 0;
            int mid = (left + right) / 2;

            h = mid;

            //나무 베면서 잘린 나무크기 구함
            for (int i = 0; i < trees.length; i++) {
                //h보다 작은 나무들은 못자른다
                if (trees[i] <= h) continue;
                namu += trees[i] - h;
            }

            if (namu < M) //톱의 높이를 낮추어서 더 많은 나무 얻도록 함
                right = mid - 1;
            else if (namu > M) {//톱의 높이를 높여서 조금 더 적은 나무 얻도록 함
                left = mid + 1;
                max = Math.max(max, h);
            }
            else if (namu == M) { // 절단기의 높이 최대값을 얻어내야함
                max = Math.max(max, h);
                break;
            }
        }

        System.out.println(max);
    }
}
