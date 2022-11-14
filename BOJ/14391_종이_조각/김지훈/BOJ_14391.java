import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14391 {

    static int N,M;
    static int c;
    static int[][] papers;
    static boolean[][] v;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        papers = new int[N][M];
        v = new boolean[N][M];
        max = Integer.MIN_VALUE;

        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<M;j++){
                papers[i][j] = str.charAt(j)-'0';
            }
        }

        solve(0,0);

        System.out.println(max);
    }
    static void solve(int x, int y){

        if(x>=N){  //N까지 갔으면 모은 경우를 다 탐색한 것
            check();
            return ;
        }
        if(y>=M){    //한 행이 끝났으니깐 다음행으로 넘어간다
            solve(x+1,0);
            return ;
        }
        v[x][y] = true; //true = 가로 , false = 세로
        solve(x,y+1);
        v[x][y] = false;
        solve(x,y+1);

    }

    static void check(){

        int sum=0;
        int x=0;
        int y=0;
        boolean[][] confirm = new boolean[N][M];
        while(x<N){
            if(v[x][y]&&!confirm[x][y]){ //가로일경우
                int sy = y;
                int count = 0;
                for(int i=y;i<M;i++){
                    if(!v[x][i])  //세로를 만나면 종료
                        break;  //세로는 방문처리 안함
                    else {
                        confirm[x][i] = true;
                        count++;
                    }
                }
                for(int i=count-1;i>=0;i--){
                    sum+=papers[x][sy++] * Math.pow(10,i);
                }

            }else if(!v[x][y]&&!confirm[x][y]) { //세로일경우우
                int sx = x;
                int count = 0;

                for(int i=x;i<N;i++){
                    if(v[i][y])  //가로를 만나면 종료
                        break;  //세로는 방문처리 안함
                    else {
                        confirm[i][y] = true;
                        count++;
                    }
                }
                for(int i=count-1;i>=0;i--){
                    sum+=papers[sx++][y] * Math.pow(10,i);
                }
            }
            y++;
            if(y>=M){  //한줄 검색을 다 한경우
                x++;
                y=0;
            }

        }
        max = Math.max(max,sum);


    }


}
