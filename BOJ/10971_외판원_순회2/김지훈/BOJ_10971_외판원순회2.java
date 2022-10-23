import java.util.*;
import java.io.*;


public class BOJ_10971_외판원순회2 {

    static int N;
    static int[][] cost;
    static boolean[] v;
    static int[] perm;
    static int min;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        cost = new int[N][N];
        v = new boolean[N];
        perm = new int[N];
        min = Integer.MAX_VALUE;
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        route(0);
        System.out.println(min);

    }
    static void route(int depth){
        if(depth==N){
            int sum=0;
            int start= perm[0];
            for(int i=0;i<N-1;i++){
                int now = cost[perm[i]][perm[i+1]];
                if(now==0) return ;  //길이 없는경우
                sum+=now;
                if(sum>=min) return ; //이미 비용이 큰경우 더이상 진행 x
            }
            if(cost[perm[N-1]][start]==0) // 마지막길이 없는경우
                return ;
            sum+=cost[perm[N-1]][start];
            min = Math.min(min,sum);
            return ;
        }

        for(int i=0;i<N;i++){
            if(v[i]) continue;
            v[i] = true;
            perm[depth] = i;
            route(depth+1);
            v[i] = false;
        }


    }

}
