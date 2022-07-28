import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class JIHUN17070 {
    private static int [][] map;
    private static int N;
    private static int count;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        count = 0;
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];


        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                map[i][j] = (Integer.parseInt(st.nextToken()));
            }
        }
        dfs(1,2,1);
        System.out.println(count);
        br.close();
    }
    public static void dfs(int x,int y,int d){
        if(x==N & y==N) {
            count++;
            return ;
        }
        if(d==1){
            if(y+1<=N && map[x][y+1]==0){
                dfs(x,y+1,1);
            }
            if (x + 1 <= N && y + 1 <= N && map[x][y + 1] == 0 && map[x + 1][y] == 0 && map[x + 1][y + 1] == 0) {
                dfs(x + 1, y + 1, 3);
            }
        }
        else if(d==2){
            if(x+1<=N && map[x+1][y]==0){
                dfs(x+1,y,2);
            }
            if (x + 1 <= N && y + 1 <= N && map[x][y + 1] == 0 && map[x + 1][y] == 0 && map[x + 1][y + 1] == 0) {
                dfs(x + 1, y + 1, 3);
            }

        }else{
            if(y+1<=N && map[x][y+1]==0){
                dfs(x,y+1,1);
            }
            if(x+1<=N && map[x+1][y]==0) {
                dfs(x + 1, y, 2);
            }
            if(x+1<=N && y+1<=N && map[x][y+1] == 0 && map[x+1][y] ==0 & map[x+1][y+1]==0){
                dfs(x+1,y+1,3);
            }
        }



    }

}
