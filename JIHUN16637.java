import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JIHUN16637 {
    private static int N;
    private static int max=0;
    private static  ArrayList<Integer> nums;
    private static ArrayList<Character> chars;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        nums = new ArrayList<Integer>();
        chars = new ArrayList<Character>();
        String sik = br.readLine();
        for(int i=0;i<sik.length();i++){
            if(i%2==0){
                nums.add(sik.charAt(i)-'0');
            }else{
                chars.add(sik.charAt(i));
            }
        }

            dfs(0,nums.get(0));


        System.out.println(max);

    }
    public static void dfs(int ch_index,int now_max){

        if(ch_index>=chars.size()){
            max = Math.max(now_max,max);
            return ;
        }

        int no_gual = calcu(now_max,nums.get(ch_index+1),ch_index);
        dfs(ch_index+1,no_gual);

        if(ch_index<chars.size()-1){
        int gual = calcu(nums.get(ch_index+1),nums.get(ch_index+2),ch_index+1);
        int res = calcu(now_max,gual,ch_index);
        dfs(ch_index+2,res);
        }
    }


    public static int calcu(int num1,int num2,int ch_index){

        if(chars.get(ch_index)=='+'){
            return num1+num2;

        }else if(chars.get(ch_index) =='-'){
            return num1-num2;
        }else
            return num1*num2;

    }


}
