import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Problem9663 {
    static int N;
    static int count = 0;
    static int[] arr;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        arr = new int[N];
        dfs(0);
        System.out.println(count);
    }
    
    public static void dfs(int depth) {
        if(depth == N) {
            count++;
            return;            
        }
        for (int i = 1; i <= N; i++) {
            boolean condition = true;
            for (int j = 0; j < depth; j++) {
                int col = arr[j];
                if(i == col) {
                    condition = false;
                    break;
                } else {
                    if(col == depth - j + i || col == i - (depth - j)) {
                        condition = false;
                        break;
                    }
                }
            }
            if(condition) {
                arr[depth] = i;
                dfs(depth + 1);
            }
        }
    }

}
