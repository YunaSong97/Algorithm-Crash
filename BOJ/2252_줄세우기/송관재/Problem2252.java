import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class Problem2252 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
        int v = s.nextInt();
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0; i<=v; i++) {
        	list.add(new ArrayList<>());
        }
        int e = s.nextInt();
        int[] count = new int[v+1];
        for(int i=0; i<e; i++){
            int v1 = s.nextInt();
            int v2 = s.nextInt();
            list.get(v1).add(v2);
            count[v2]++;
        }
        int[] answer = new int[v];
        int idx = 0;
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= v; i++) {
            if(count[i] == 0) {
                q.add(i);
            }
        }
        while(!q.isEmpty()) {
            int t = q.poll();
            answer[idx] = t;
            idx++;
            for(int tmp : list.get(t)) {
                count[tmp]--;
                if(count[tmp]==0) {
                    q.add(tmp);
                }
            }
        }
        System.out.print(answer[0]);
        for(int i=1; i<v; i++){
            System.out.print(" " + answer[i]);
        }
	}

}
