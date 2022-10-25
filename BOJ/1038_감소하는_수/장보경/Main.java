import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Long> al;
    static String ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        if (N > 1022){ // 9876543210이 1022번째 수이기 때문에 그 이상으로 넘어갈 수 없음
            ans = "-1";
        } else{
          // 가장 큰수가 0~9일때 모든 경우의 수를 구함
            al = new ArrayList<>();
            for (int i=0; i<10; i++){
                search(1, i);
            }

            // 정렬해서 N번째 수 구하기
            Collections.sort(al);
            ans = al.get(N).toString();
        }

        System.out.println(ans);
    }

    static void search(int depth, long num){
        if (depth > 10) return;

      // 10의 자리씩 늘려서 감소하는 수 구한 후 ArrayList에 넣기
        al.add(num);
        for (int i=0; i<num%10; i++){
            search(depth+1, (num*10)+i);
        }
    }
}
