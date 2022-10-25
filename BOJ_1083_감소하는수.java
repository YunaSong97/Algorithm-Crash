import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_1083_감소하는수 {

    static int N;
    static boolean[] v;
    static int[] list;
    static int count=9;
    static int now_depth=0;
    static int max_depth=0;
    static ArrayList<Integer> result;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        result = new ArrayList<>();
        if(N<10) {
            System.out.println(N);
        }
        else{
            for(int i=2;i<=11;i++){
                now_depth = i;
                v = new boolean[10];
                list = new int[i];
                per(0);
            }
            System.out.println(-1);
        }

    }

    static void per(int depth){
        if(depth==now_depth){
            long num=0;
            boolean flag = true;
            for(int i=0;i<list.length;i++){
                num+=list[i]*Math.pow(10,depth-1-i);
            }
            for(int i=0;i<list.length-1;i++){
                if(list[i]<=list[i+1]){
                    flag = false;
                    break;
                }
            }
            if(flag) { //감소하는 수인경우
                count++;
            }
            if(count==N){ // 값을 찾은 경우
                System.out.println(num);
                System.exit(0);
            }
            return ;
        }
        if(depth==0){  //맨 앞자리는 0안됨
            for(int i=1;i<=9;i++){
                if(v[i])continue;
                v[i] = true;
                list[depth] = i;
                per(depth+1);
                v[i] = false;
            }
        }
        else{// 2번쨰 자리부터
            for(int i=0;i<9;i++) { //뒷자리에는 9못옴
                if (v[i]) continue;
                v[i] = true;
                list[depth] = i;
                per(depth + 1);
                v[i] = false;
            }
        }

    }

}
