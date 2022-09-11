import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] simsaTime = new int[N];
        for (int i=0; i<N; i++){
            simsaTime[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(simsaTime);

        long ans = 0;
        long min = 0;
        long max = (long)simsaTime[N-1] * M;
        while (min <= max){
            long mid = (min+max) / 2;  // 심사를 하는 데 걸리는 시간
            long sum = 0;

            //시간 당 각 심사대에서 몇 명을 심사할 수 있는지
            for (int i=0; i<N; i++){
                sum += mid/simsaTime[i];
            }

            if (sum < M){
                min = mid+1;
            } else {
                max = mid-1;
                ans = mid;
            }
        }
        
        System.out.println(ans);
    }
}
