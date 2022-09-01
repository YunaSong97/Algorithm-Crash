
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2531 {



    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int start=1;
        int end=k;
        int max = Integer.MIN_VALUE;
        int[] belt = new int[n];
        int check[] = new int[d+1];
        int count=0;
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            belt[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0;i<k;i++){
            if(check[belt[i]]==0)
                count++;
            check[belt[i]]++;
        }
        max = count;

        while(true){
            if(check[belt[start-1]]==1)
                count--;
            check[belt[start-1]]--;

            if(check[belt[end]]==0)
                count++;
            check[belt[end]]++;

            if(check[c]==0)
                max = Math.max(max,count+1);
            else{
                max = Math.max(max,count);
            }
            start++;
            end++;
            if(end==n) // 끝지점 일경우 0부터 다시확인
                end=0;
            if(start==n)
                break;
        }




        System.out.println(max);
    }
}