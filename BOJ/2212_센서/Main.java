import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        int[] sensor = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            sensor[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sensor);
        int answer = sensor[N-1] - sensor[0];
        int[] gap = new int[N-1];
        for(int i=0; i<N-1; i++) {
            gap[i] = sensor[i+1] - sensor[i];
        }
        Arrays.sort(gap);
        for(int i=N-2; i>N-2-K+1; i--) {
            if(i<0) {
                break;
            }
            answer -= gap[i];
        }
        System.out.println(answer);
    }

}
