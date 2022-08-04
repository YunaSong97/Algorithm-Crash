import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888 {

    private static int N;
    private static int[] nums,operation;
    static int Max = Integer.MIN_VALUE;
    static int Min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int start=0;

        nums = new int[N];
        st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        operation = new int[4];
        st = new StringTokenizer(br.readLine());

        for(int i=0;i< 4;i++){
            operation[i] = Integer.parseInt(st.nextToken());
        }

        start = nums[0];
        dfs(start,1);

        System.out.println(Max);
        System.out.println(Min);
    }
    private static void dfs(int sum,int index){
        if(index==N){
            Max = Math.max(Max,sum);
            Min = Math.min(Min,sum);
            return ;
        }
        for(int i=0;i<4;i++){
            if(operation[i]>0){
                operation[i]--;
                switch(i) {
                    case 0:
                        dfs(sum + nums[index], index + 1);
                        break;
                    case 1:
                        dfs(sum - nums[index], index + 1);
                        break;
                    case 2:
                        dfs(sum * nums[index], index + 1);
                        break;
                    case 3:
                        dfs(sum / nums[index], index + 1);
                        break;
                }

                operation[i]++;
            }

        }

    }

}
