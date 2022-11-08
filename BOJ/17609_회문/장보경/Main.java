import java.io.*;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++){
            String str = br.readLine();
        	
          // 회문이라면 0
        	if(chk(str, 0, str.length()-1)) {
        		sb.append(0).append("\n");
        		continue;
        	}
        	
          // 유사회문이라면 1, 아니면 2
        	if(del(str)) {
                sb.append(1).append("\n");
        	} else{
                sb.append(2).append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    static boolean chk(String s, int start, int end){
        for (int i=start; i<s.length(); i++,end--){
            if (i > end) break; // 양쪽 문자를 다 비교 했으면 break

            if (s.charAt(i) != s.charAt(end)){ // 양쪽 문자가 다르면 false
                return false;
            }
        }

        return true;
    }

    static boolean del(String s){
        int sLen = s.length();
        int end = sLen-1;

        for (int start=0; start<sLen; start++, end--){
            if (start > end) break; // 양쪽 문자를 다 비교 했으면 break

            // 양쪽 문자가 다르면 둘 중 하나를 빼서 유사회문 되는지 체크
            if (s.charAt(start) != s.charAt(end)){ 
                if (chk(s, start+1, end) || chk(s, start, end-1)){
                    return true;
                }
                break;
            }
        }

        return false;
    }
}
