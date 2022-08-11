import java.util.Scanner;
public class Problem2023 {
    static int N;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        dfs(1, 2);
        dfs(1, 3);
        dfs(1, 5);
        dfs(1, 7);
    }
    
    public static void dfs(int depth, int x) {
        if(depth == N) {
            System.out.println(x);
            return;
        }
        for(int i = 1; i < 10; i+=2) {
            int temp = x * 10;
            temp += i;
            if(isPrime(temp)) {
                dfs(depth + 1, temp);
            }
        }
    }

    public static boolean isPrime(int x) {
        int n = (int) Math.ceil(Math.sqrt((double)x));
        for(int i=2; i<=n; i++) {
            if(x % i == 0) {
                return false;
            }
        }
        return true;
    }
}
