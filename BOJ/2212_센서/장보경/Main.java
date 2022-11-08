import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] arr;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++){
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int[] diff = new int[N-1];
        for (int i=0; i<N-1; i++){
            diff[i] = arr[i+1]-arr[i];
        }
        Arrays.sort(diff);

        for (int i=0; i<N-K; i++){
            ans += diff[i];
        }

        System.out.println(ans);
    }
}
