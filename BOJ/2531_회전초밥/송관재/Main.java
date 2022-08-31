import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int d = s.nextInt();
        int k = s.nextInt();
        int c = s.nextInt();
        int[] sushi = new int[N];
        int[] count = new int[d+1];
        int current = 0;
        int answer = 0;
        int left = 0;
        int right = left;
        for(int i=0; i<N; i++) {
            sushi[i] = s.nextInt();
        }
        for(right = left; right < k; right++) {
            count[sushi[right]]++;
        }
        right--;
        for(left = 0; left < N; left++) {
            current = 0;
            if(count[c] == 0) {
                current = 1;
            }
            for(int i=1; i<=d; i++) {
                if(count[i] > 0) {
                    current++;
                }
            }
            if(current > answer) {
                answer = current;
            }
            right++;
            right %= N;
            count[sushi[right]]++;
            count[sushi[left]]--;
        }
        System.out.println(answer);
    }

}
