import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class BOJ_2023 {

    private static String[] first = {"2","3","5","7"};
    private static String[] second = {"1","3","7","9"};
    private static int N;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i=0;i< first.length;i++)
            bt(first[i],1);

        System.out.println(sb);

    }
    private static void bt(String str,int depth){
        if(N==depth){
            sb.append(str).append("\n");
            return ;
        }
        for(int i=0;i< second.length;i++){
            String nextStr = str+second[i];
            if(isPrime(Integer.parseInt(nextStr)))
                bt(nextStr,depth+1);

        }
    }
    private static boolean isPrime(int num){
        int sqrt = (int)Math.sqrt(num);
        for(int i=2;i<sqrt+1;i++){
            if(num%i==0){
                return false;
            }
        }
        return true;
    }




}
