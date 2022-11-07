import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15661 {

    static int N;
    static boolean[] v;
    static int[][] ability;
    static int min;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        min = Integer.MAX_VALUE;
        ability = new int[N][N];
        v = new boolean[N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                ability[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1;i<=N/2;i++){      //ex N = 6이면 (1,5)(2,4),(3,3)
            solve(0,0,i);
        }
        System.out.println(min);
    }
    static void solve(int depth,int start,int R){   //팀구성을 할수 있는 경우
        if(depth==R){
            int res = calc();
            if(res==0){ //0이면 무조건 최소값
                System.out.println(res);
                System.exit(0);
            }
            min = Math.min(min,res);
            return ;
        }
        for(int i=start;i<N;i++){
            v[i] = true;
            solve(depth+1,i+1,R);
            v[i] = false;
        }


    }
    static int calc(){  //4명중에서 2명 뽑아
        int start = 0 ; //visited
        int link = 0;   // not visited

        for(int i=0;i<N-1;i++){
            for(int j=i+1;j<N;j++){
                if(v[i]&&v[j]){
                    start+=ability[i][j]+ability[j][i];
                }else if(!v[i]&&!v[j]){
                    link+=ability[i][j]+ability[j][i];
                }
            }
        }
        return Math.abs(start-link);
    }

}

