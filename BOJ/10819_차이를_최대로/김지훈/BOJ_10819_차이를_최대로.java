import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_10819_차이를_최대로 {

    static int N;
    static int[] nums;
    static int max;
    static int[] per;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        per = new int[N];
        v = new boolean[N];
        max = Integer.MIN_VALUE;
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        perm(0);
        System.out.println(max);
    }

    static void perm(int depth){
        if(depth==N){
            int sum=0;
            for(int i=0;i<N-1;i++)
                sum+=Math.abs(nums[per[i]]-nums[per[i+1]]);
            if(sum>max)
                max = sum;
            return ;
        }
        for(int i=0;i<N;i++){
            if(v[i]) continue;
            v[i] = true;
            per[depth] = i;
            perm(depth+1);
            v[i] = false;
        }
    }


}

