import java.io.*;
import java.util.*;

public class Main {
    static long[] arr;
    static long m;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new long[n];

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        System.out.println(bs());
    }

    public static long bs(){
        long left = 0;
        long right = arr[n-1];

        while (left <= right){
            long mid = (left+right)/2;

            long cnt = 0;
            for (int i=0; i<n; i++){
                if (arr[i] > mid){
                    cnt += arr[i] - mid;
                }
            }

            if (cnt < m){
                right = mid-1;
            }
            else {
                left = mid+1;
            }
        }

        return right;
    }
}
