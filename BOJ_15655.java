import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15655{
    private static int N,M;
    private static int[] arr;
    private static int[] tArr;
    private static boolean[] visited;
    private static StringBuilder sb ;
    private static boolean flag = true;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        tArr = new int[M];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());

        }
        Arrays.sort(arr);
        dfs(0);
        System.out.println(sb);
    }
    private static void dfs(int depth){
        if(depth==M){
            for(int i=0;i< tArr.length;i++){
                if((i!=tArr.length-1)){
                    if(tArr[i]>tArr[i+1]){
                        flag= false;
                    }
                }
            }
            if(flag) {
                for (int val : tArr) {
                    sb.append(val).append(" ");
                }
                sb.append("\n");
            }
            flag = true;
            return ;
        }

        for(int i=0;i<N;i++){
            if(!visited[i]){
                visited[i]=true;
                tArr[depth] = arr[i];
                dfs(depth+1);
                visited[i]=false;
            }


        }


    }
}