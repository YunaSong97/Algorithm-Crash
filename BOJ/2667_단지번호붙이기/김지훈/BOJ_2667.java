import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_2667 {

    private static int[][] map;
    private static int count=0;
    private static int[] dx = {1,-1,0,0};
    private static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> results = new ArrayList<>();
        map = new int[N][N];
        for(int i=0;i<N;i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j)-'0';
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(dfs(i,j)){
                    results.add(count);
                    count=0;
                }
            }
        }

        Collections.sort(results);
        System.out.println(results.size());
        for(int i:results)
            System.out.println(i);
    }
    public static boolean dfs(int x,int y){
        if(x<0||y<0||x>=map.length||y>=map.length){
            return false;
        }
        if(map[x][y]==1){
            map[x][y]=0;
            count++;
            for(int i=0;i<dx.length;i++)
                dfs(x+dx[i],y+dy[i]);
            return true;
        }
        return false;
    }
}
