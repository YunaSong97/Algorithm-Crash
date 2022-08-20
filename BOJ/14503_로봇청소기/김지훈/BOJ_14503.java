package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class BOJ_14503{


    static int moving;
    static int[] dx={-1,0,1,0};//북,동,남,서
    static int[] dy={0,1,0,-1};
    static int[][] map;
    static int cleaning;
    static int change;
    static int N,M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(st.nextToken());
        int startY = Integer.parseInt(st.nextToken());
        moving = Integer.parseInt(st.nextToken());
        cleaning = 1;
        change = 0;
        map = new int[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clean(startX,startY);

        System.out.println(cleaning);

    }
    static void clean(int x,int y){
        map[x][y] = 2;

        for(int i=0;i<4;i++){
            moveLeft();
            int nx = x+dx[moving];
            int ny = y+dy[moving];
            if(nx>=0 && ny>=0 && nx<N && ny<M && map[nx][ny]==0) {
                change = 0;
                cleaning++;
                clean(nx, ny);
                return;
            }else{
                change++;
            }

        }
        if(change==4) {
            int nx = x - dx[moving];
            int ny = y - dy[moving];
            if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] != 1) {
                change = 0;
                clean(nx, ny);
            }
        }
    }
    static void moveLeft(){
        moving--;
        if(moving<0)
            moving = 3;
    }
}
