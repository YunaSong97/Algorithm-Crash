import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        int temp = 0;
        int sum = 0;
        int left = 0;
        int right = N - 1;
        int[] answer = new int[2];
        while (left < right) {
            sum = arr[left] + arr[right];
            temp = Math.abs(sum);
            if (temp < min) {
                answer[0] = arr[left];
                answer[1] = arr[right];
                min = temp;
            }
            if (sum > 0) {
                right--;
            } else {
                left++;
            }
        }
        System.out.println(answer[0] + " " + answer[1]);
    }
}
