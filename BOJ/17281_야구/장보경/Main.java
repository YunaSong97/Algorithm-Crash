import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    static int[] player;
    static boolean[] visited;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][9];
        for (int i=0; i<N; i++){
          StringTokenizer st = new StringTokenizer(br.readLine());
          for (int j=0; j<9; j++){
            arr[i][j] = Integer.parseInt(st.nextToken());
          }
        }

        player = new int[9];
        player[3] = 0;  // 1번타자 4번째 고정
        visited = new boolean[9];
        visited[3] = true;  // 1번타자 4번째 고정

        perm(1);

        System.out.println(ans);
    }

    static void perm(int cnt){
      // 선수의 순서가 다 정해졌다면 play()한 후 최대 득점 구하기
        if (cnt == 9){
            int score = play();
            ans = Math.max(ans, score);

            return;
        }

        for (int i=0; i<9; i++){
            if (!visited[i]){
                visited[i] = true;
                player[i] = cnt;
                perm(cnt+1);
                visited[i] = false;
            }
        }
    }

    static int play(){
        int idx = 0;
        int score = 0;

      // 이닝 수 만큼 반복
        for (int i=0; i<N; i++){
            int out = 0;
            int[] base = new int[3];

          // 아웃이 아니라면 끝날때까지 무한 반복
            while (true){
                if (out == 3){
                    break;
                }

                switch (arr[i][player[idx]]){
                    case 0:  // 아웃
                        out++;
                        break;
                    case 1:  // 안타
                        if (base[2] == 1){
                            base[2] = 0;
                            score++;
                        }
                        for (int j=1; j>=0; j--){
                            if (base[j] != 0){
                                base[j] = 0;
                                base[j+1] = 1;
                            }
                        }
                        base[0] = 1;
                        break;
                    case 2:  // 2루타
                        for (int j=1; j<3; j++){
                            if (base[j] != 0){
                                base[j] = 0;
                                score++;
                            }
                        }
                        if (base[0] != 0){
                            base[0] = 0;
                            base[2] = 1;
                        }
                        base[1] = 1;
                        break;
                    case 3:  // 3루타
                        for (int j=0; j<3; j++){
                            if (base[j] != 0){
                                base[j] = 0;
                                score++;
                            }
                        }
                        base[2] = 1;
                        break;
                    case 4:  // 홈런
                        for (int j=0; j<3; j++){
                            if (base[j] != 0){
                                score++;
                            }
                        }
                        score++;
                        base = new int[4];
                        break;
                }
                idx = (idx+1)%9;  // 무한 반복하게 되면 결과 배열의 인덱스를 초과하게되므로 %9해준다.
            }
        }
        return score;
    }
}
