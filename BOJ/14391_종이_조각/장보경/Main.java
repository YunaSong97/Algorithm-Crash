import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static boolean[][] width;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i=0; i<N; i++){
          String[] tmp = br.readLine().split("");
          for (int j=0; j<M; j++){
            arr[i][j] = Integer.parseInt(tmp[j]);
          }
        }

        ans = Integer.MIN_VALUE;
        width = new boolean[N][M];
        cut(0, 0);

        System.out.println(ans);
    }

    static void cut(int x, int y){
      // 모든 배열을 돌았다면 자른 조각의 합을 구한다.
        if (x>=N){
            getSum();
            return;
        }

      // 가로가 M까지 도달했다면 다음행으로
        if (y>=M){
            cut(x+1, 0);
            return;
        }

      // 가로로 자른다고 판단
        width[x][y] = true;
        cut(x, y+1);
      // 세로로 자른다고 판단
        width[x][y] = false;
        cut(x, y+1);
    }

    static void getSum(){
        int sum = 0;
      // 가로 합 구하기
        for (int i=0; i<N; i++){
            String s = "0";
            for (int j=0; j<M; j++){
                if (width[i][j]){
                    s += arr[i][j];
                } else{  // 세로 조각이 껴있으면 여태까지의 숫자를 sum에 더하고 문자열 초기화
                    sum += Integer.parseInt(s);
                    s = "0";
                }
            }
            sum += Integer.parseInt(s);
        }

      // 세로 합 구하기
        for (int i=0; i<M; i++){
            String s = "0";
            for (int j=0; j<N; j++){
                if (!width[j][i]){
                    s += arr[j][i];
                } else{  // 가로 조각이 껴있으면 여태까지의 숫자를 sum에 더하고 문자열 초기화
                    sum += Integer.parseInt(s);
                    s = "0";
                }
            }
            sum += Integer.parseInt(s);
        }

        ans = Math.max(ans, sum);
    }
}
