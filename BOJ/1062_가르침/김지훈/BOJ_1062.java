import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1062 {

    static int N,K;

    static boolean[] v;
    static String[] words;
    static ArrayList<Character> combi;
    static char[] alpha = {'b','d','e','f','g','h','j','k','l','m','o','p','q','r','s','u','v','w','x','y','z'};
    static int max;
    static int c=0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        combi = new ArrayList<>(21);
        words = new String[N];
        for(int i=0;i<N;i++){
            words[i] = br.readLine();
        }
        max = Integer.MIN_VALUE;

        if(K<5){
            max = 0;
        }else{
            for(int i=0;i<21;i++)
                combi.add('-');
            solve(0,0);
        }
        System.out.println(max);
        //System.out.println(c);
    }
    static void solve(int depth,int start){
        if(depth==K-5){
            c++;
            check();
            return ;
        }
        for(int i=start;i<21;i++){
            combi.set(depth,alpha[i]);
            solve(depth+1,i+1);
        }
    }
    static void check(){
        int count=words.length;

        for(int i=0;i<words.length;i++){
            for(int j=0;j<words[i].length();j++) {
                if (j > 3 && j < words[i].length() - 4 && isRange(words[i].charAt(j))) {
                    if(!combi.contains(words[i].charAt(j))) {
                        count--;
                        break;
                    }
                }
            }


        }
        max = Math.max(max,count);

    }
    static boolean isRange(char ch){
        if(ch=='a'||ch=='n'||ch=='t'||ch=='i'||ch=='c')
            return false;
        return true;
    }


}
