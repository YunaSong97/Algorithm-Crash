import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        long start = 0;
        long end = (long)arr[N-1] * (long)M;
        long mid = (start + end) / 2;
        long sum = 0;
        long ans = 0;
        while(start <= end) {
            mid = (start + end) / 2;
            sum = 0;
            for(int i=0; i<N; i++) {
                sum += mid / arr[i];
            }
            if(sum >= M) {
                end = mid - 1;
                ans = mid;
            } else {
                start = mid + 1;
            }
        }
        
        System.out.println(ans);
    }
}