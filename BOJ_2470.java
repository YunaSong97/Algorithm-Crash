import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2470 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int min = Integer.MAX_VALUE;
        int n = Integer.parseInt(br.readLine());
        int[] sol = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            sol[i] = Integer.parseInt(st.nextToken());
        }
        int minn=0;
        int maxn=0;
        int start=0;
        int end=n-1;
        int count=0;
        Arrays.sort(sol);

        while(start<end){
            int sum = Math.abs(sol[start]+sol[end]);
            if(min>sum){
                min = sum;
                minn = sol[start];
                maxn = sol[end];
                if(sum==0) break;
            }
            if(sol[start]+sol[end]<0) start++;
            else end--;


        }
        System.out.println(minn+" "+maxn);

    }
}