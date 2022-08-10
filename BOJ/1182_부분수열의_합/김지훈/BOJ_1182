import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1182 {
    private static int [] nums;
    private static List<List<Integer>> list = new ArrayList<>();
    private static boolean[] visited;
    private static int N;
    private static int S;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        nums = new int[N];
        visited = new boolean[N];
        int count =0;
        Arrays.fill(visited,false);
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        for(int r=1;r<=N;r++) {
            combination(nums, visited, 0, r);
        }
        for(int i=0;i<list.size();i++){
            int sum=0;
            for (int j=0;j<list.get(i).size();j++){
                sum+=list.get(i).get(j);
            }
            if(sum==S)
                count++;
        }
        System.out.println(count);
    }

    private static void combination(int[] arr, boolean[] visited, int start, int r) {
        if(r == 0) {
            commit(arr,visited,N);
        }

        else {
            for(int i = start; i < arr.length; i++) {
                visited[i] = true;
                combination(arr, visited, i + 1, r - 1);
                visited[i] = false;
            }
        }
    }
    static void commit(int[] arr, boolean[] visited, int n) {
        List<Integer> tL = new ArrayList<>();
        for (int i = 0; i < n; i++) {

            if (visited[i]) {
                tL.add(arr[i]);
            }
        }
        list.add(tL);

    }
}
