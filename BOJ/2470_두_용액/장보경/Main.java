import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<Integer> al = new ArrayList<>();
        for (int i=0; i<N; i++){
            al.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(al);

        int start = 0;
        int end = al.size()-1;
        int before = Integer.MAX_VALUE;
        int[] ans = new int[2];
        while(start < end){
            int sum = al.get(start)+al.get(end);
            int abs = Math.abs(sum);  //절댓값을 안하면? 밑에 조건문에서 0에 근접한 값이 아니라 최저값을 구하게 됨
            if (before > abs){
                ans[0] = al.get(start);
                ans[1] = al.get(end);
                before = abs;
            }

            // sum이 음수면 0에 근접할 수 있도록 더 큰 음수 선택(양수값이 더 커지도록)
            // sum이 양수면 0에 근접할 수 있도록 더 작은 양수 선택(음수값이 더 커지도록)
            if (0 > sum){
                start++;
            } else{
                end--;
            }
        }

        System.out.println(ans[0] + " " + ans[1]);
    }
}
