import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        int cnt = 0;
        int[] eatCnt = new int[d+1];
        // 연속해서 먹는 접시의 수 k만큼 미리 배열 값 증가 & 가짓수 카운팅
        for(int i=0; i<k; i++) {
            int idx = arr[i];
            if(eatCnt[idx] == 0) {
                cnt++;
            }
            eatCnt[idx]++;
        }

        int max = cnt;
        for(int i=0; i<N; i++) {
            if(max <= cnt) {
                // 쿠폰 초밥을 아직 먹지 않았을 경우 +1
                if(eatCnt[c] == 0) {
                    max = cnt+1;
                } else {
                    max = cnt;
                }
            }

            // 같은 종류의 초밥을 한 번 먹었다면 횟수 감소시키기
            if(eatCnt[arr[i]] == 1) {
                cnt--;
            }
            eatCnt[arr[i]]--;

            // 새로 추가한 초밥과 같은 것을 먹은적이 없다면 횟수 증가
            if(eatCnt[arr[(i+k)%N]] == 0) {
                cnt++;
            }
            eatCnt[arr[(i+k)%N]]++;
        }

        System.out.println(max);
    }
}
