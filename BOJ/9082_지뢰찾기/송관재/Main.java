import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for(int t=0; t<T; t++) {
            int answer = 0;
            int N = Integer.parseInt(br.readLine());
            int[] num = new int[N];
            char[] mine = new char[N];
            String str = br.readLine();
            for(int i=0; i<N; i++) {
                num[i] = Integer.parseInt(str.charAt(i) + "");
            }
            str = br.readLine();
            for(int i=0; i<N; i++) {
                mine[i] = str.charAt(i);
            }
            if(num[0] == 1) {
                if(mine[0] == '*' || mine[1] == '*') {
                    answer++;
                } else {
                    mine[0] = '*';
                    answer++;
                }
            } else if(num[0] == 2) {
                mine[0] = '*';
                mine[1] = '*';
                answer += 2;
            }
            for(int j=2; j<N; j++) {
                if(mine[j-1] == '*' && mine[j-2] == '*') {
                    if(num[j-1] > 2 && num[j] > 1) {
                        mine[j] = '*';
                        answer++;
                    }
                } else if(mine[j-1] == '*') {
                    if(num[j-1] > 1 && num[j] > 1) {
                        mine[j] = '*';
                        answer++;
                    }
                } else if(mine[j-2] == '*'){
                    if(num[j-1] > 1 && num[j] > 0) {
                        mine[j] = '*';
                        answer++;
                    }
                } else {
                    if(num[j-1] > 0 && num[j] > 0) {
                        mine[j] = '*';
                        answer++;
                    }
                }
            }
            System.out.println(answer);
        }
    }

}
