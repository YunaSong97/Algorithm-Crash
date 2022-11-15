import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static char[][] arr;
    static boolean[] alpha;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (K < 5){  // 무조건 a. n. t, i, c가 포함되어야 하기 때문에 K가 5 미만이면 0
            System.out.println(0);
            return;
        } else if (K == 26){
            System.out.println(N);
            return;
        }

        arr = new char[N][15];
        for (int i=0; i<N; i++){
            arr[i] = br.readLine().toCharArray();
        }

        alpha = new boolean[26];
		    alpha['a'-'a'] = true;
        alpha['n'-'a'] = true;
        alpha['t'-'a'] = true;
        alpha['i'-'a'] = true;
        alpha['c'-'a'] = true;

        ans = 0;
        dfs(0, 0);

        System.out.println(ans);
    }

    static void dfs(int cnt, int start){
      // 가르쳐야할 글자수를 채우면, 학생들이 몇 개의 단어를 읽을 수 있는지 확인
        if (cnt == K-5){
            teachCnt();
            return;
        }

        for (int i=start; i<26; i++){
            if (!alpha[i]){
                alpha[i] = true;
                dfs(cnt+1, i+1);
                alpha[i] = false;
            }
        }
    }

    static void teachCnt(){
        int cnt = 0;

        for (int i=0; i<N; i++){
            boolean read = true;

            for (int j=0; j<arr[i].length; j++){
              // 단어가 끝나면 for문을 더 돌아도 null값이므로 break
                if (arr[i][j] == 0){
                    break;
                }

              // 만약 단어에 포함된 문자를 배우지 않았다면 
                int idx = arr[i][j]-'a';
                if (!alpha[idx]){
                    read = false;
                    break;
                }
            }

          // 단어의 모든 문자를 읽을 수 있으면 cnt++
            if (read){
                cnt++;
            }
        }
        ans = Math.max(ans, cnt);
    }
}
