import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class BOJ_9663 {

    private static int[] matrix;
    private static int N;
    private static int result=0;

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        matrix = new int[N];

        bt(0);
        System.out.println(result);

    }

    public static void bt(int depth){

        if(depth==N){
            result++;
            return ;

        }
        for(int i=0;i<N;i++){
            matrix[depth] = i;

            if(isOk(depth)){
                bt(depth+1);
            }
        }

    }
    public static boolean isOk(int col){
        for(int i=0;i<col;i++){
            if(matrix[col] == matrix[i]){
                return false;
            }
            else if(Math.abs(col-i) == Math.abs(matrix[col]-matrix[i])){
                return false;
            }
        }

        return true;



    }




}
