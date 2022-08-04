import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_10026 {
    private static char[][] matrix;
    private static boolean[][] visited;
    private static int weakRedGreen=0;
    private static int noWeakRedGreen=0;
    private static int[] dx = {1,-1,0,0};
    private static int[] dy = {0,0,1,-1};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        matrix = new char[N][N];
        visited = new boolean[N][N];

        for(boolean a[]:visited)
            Arrays.fill(a,false);

        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<N;j++){
                matrix[i][j] = str.charAt(j);
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!visited[i][j]) {
                    dfs(i, j);
                    noWeakRedGreen ++;
                }
            }
        }


        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(matrix[i][j] == 'G')
                    matrix[i][j] = 'R';
            }
        }

        for(boolean a[]:visited)
            Arrays.fill(a,false);

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!visited[i][j]) {
                    dfs(i, j);
                    weakRedGreen++;
                }
            }
        }

        System.out.println(noWeakRedGreen+" "+weakRedGreen);
    }
    private static void dfs(int x,int y){
        visited[x][y] = true;
        char c = matrix[x][y];

        for(int i=0;i<4;i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(nx>=0 && ny>=0 && nx< matrix.length && ny< matrix.length){
                if(matrix[nx][ny] == c && !visited[nx][ny]){
                    dfs(nx,ny);
                }
            }
        }


    }

}
