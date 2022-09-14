import java.util.Scanner;

public class Main {
    static long[] arr;
    static long answer = 0;
    static int N;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        int M = s.nextInt();
        long start = 0;
        long end = 0;
        arr = new long[N];
        for(int i=0; i<N; i++) {
            arr[i] = s.nextInt();
            if(arr[i] > end) {
                end = arr[i];
            }
        }
        solve(start, end, M);
        System.out.println(answer);
    }

    public static void solve(long start, long end, int M) {
        if(start > end) {
            if(answer == 0) {
                answer = end;
            }
            return;
        }
        long mid = (start + end) / 2;
        long temp = cal(mid);
        if(temp == M) {
            answer = mid;
            return;
        } else if(temp > M) {
            solve(mid + 1, end, M);
        } else {
            solve(start, mid - 1, M);
        }
        
    }
    
    public static long cal(long height) {
        long sum = 0;
        for(int i=0; i<N; i++) {
            if(arr[i] > height) {
                sum += arr[i] - height;
            }
        }
        return sum;
    }
}
