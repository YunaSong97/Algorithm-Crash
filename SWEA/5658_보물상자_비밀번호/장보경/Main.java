import java.util.*;
import java.io.*;

public class Solution {
    static int N, K, len;
    static String[][] arr;
    static TreeSet<String> ts;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int tNum = 1;

        while(T >= tNum) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            String[] rotate = br.readLine().split("");

            len = N/4;
            arr = new String[4][len];

            int idx = 0;
            // 받아온 문자열을 2차원 배열로 만들기
            for (int i=0; i<4; i++){
                for (int j=0; j<len; j++){
                    arr[i][j] = rotate[idx++];
                }
            }

            rotate();

            ArrayList<String> al = new ArrayList<>(ts);
            int ans = Integer.parseInt(al.get(al.size()-K), 16);

            sb.append("#").append(tNum).append(" ").append(ans).append("\n");
            tNum++;
        }
        System.out.println(sb.toString());
    }

    static void rotate(){
        ts = new TreeSet<>();

        // i는 회전 수, j는 행, k(kk)는 열
        /**
         * ex) 6열이라면
         * 1회전: [0][0], [0][1], [0][2], ... , [3][5]
         * 2회전: [3][5], [0][0], [0][1], ... , [3][4]
         * 3회전: [3][4], [3][5], [0][0], ...., [3][3]
         * ...
         * 6회전: [3][1], [3][2], [3][3], ...., [3][0]
         **/
        for (int i=0; i<len; i++){
            StringBuilder s = new StringBuilder();
            int j = 3, k = len-i;
            for (;k<len; k++){
                s.append(arr[j][k]);
            }

            for (j=0; j<4-i; j++){
                for (int kk=0; kk<len; kk++){
                    // StringBuilder s의 길이가 N/4와 같다면, 문자열이 만들어진 것이므로 TreeSet에 추가
                    if (s.length() == len){
                        ts.add(s.toString());
                        s = new StringBuilder();
                    }
                    s.append(arr[j][kk]);
                }
            }

            if (s.length() == len){
                ts.add(s.toString());
                s = new StringBuilder();
            }

            for (; j<4; j++){
                for (k=0; k<len; k++){
                    if (s.length() == len){
                        ts.add(s.toString());
                        s = new StringBuilder();
                    }
                    if (j==3 && k==len-i){
                        break;
                    }
                    s.append(arr[j][k]);
                }
            }
        }
    }
}
