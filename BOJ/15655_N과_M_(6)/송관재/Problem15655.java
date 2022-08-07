import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Problem15655 {
    static List<Integer> list;
    static int N;
    static int M;
    static int[] arr;
    static int[] visit;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        list = new ArrayList<>();
        N = s.nextInt();
        M = s.nextInt();
        arr = new int[M];
        visit = new int[N];
        for(int i=0; i < N; i++) {
            list.add(s.nextInt());
        }
        Collections.sort(list);
        dfs(0, 0);
    }

    public static void dfs(int start, int depth) {
        if(depth == M) {
            System.out.print(arr[0]);
            for(int i=1; i<M; i++) {
                System.out.print(" " + arr[i]);
            }
            System.out.println();
            return;
        }
        for(int i=start; i<N; i++) {
            if(visit[i] == 0) {
                arr[depth] = list.get(i);
                visit[i]++;
                dfs(i + 1, depth + 1);
                visit[i]--;
            }
        }
    }
}
