import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] arr;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        ans = 0;
        search(0, 0);

        System.out.println(ans);
    }

    public static void search(int idx, int benefit){
        // 만약 인덱스가 N일 보다 크거나 같으면 return
        if (idx >= N){
            if (idx == N){
                ans = Math.max(ans, benefit);
            }
            return;
        }

        search(idx+arr[idx][0], benefit+arr[idx][1]); // 현재인덱스 + 상담기간
        search(idx+1, benefit); // 현재 인덱스를 건너뛰고 다음 인덱스 선택
    }
}
