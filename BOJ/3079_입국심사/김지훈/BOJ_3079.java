import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3079 {
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] times = new int[n];
        int max = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            times[i] = Integer.parseInt(br.readLine());
            max = Math.max(max,times[i]);
        }
        long s=1;
        long e=max*1000000000L;
        while(s<=e){
            long cnt=0;
            long mid = (e+s)/2;
            for(int i=0;i<n;i++){
                cnt+=mid/times[i];
                if(cnt>m) break;
            }
            if(cnt<m) s = mid+1;
            else e = mid-1;
        }
        System.out.println(s);
    }
}
