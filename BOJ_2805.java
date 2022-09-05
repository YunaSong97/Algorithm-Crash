import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2805 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] heights = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++)
            heights[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(heights);
        int s = 1;
        int e = heights[n-1];
        int max = 0;
        int mid=0;
        while(s<=e){
            mid = (s+e)/2;
            long sum=0;
            for(int height:heights){
                if(height>mid) sum+=height-mid;
                if(sum>m) break;
            }
            if(sum>=m) s = mid+1;
            else e = mid-1;

        }
        System.out.println(e);
        br.close();

    }
}