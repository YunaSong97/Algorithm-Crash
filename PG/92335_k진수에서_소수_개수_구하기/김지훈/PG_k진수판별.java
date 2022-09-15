import java.util.*;

public class PG_k진수판별 {
    static String str="";
    static List<Long> list = new ArrayList<>();
    public  int solution(int n, int k) {
        int answer = 0;
        Stack<Character> stack = new Stack<>();
        while(n>=k){
            stack.push((char) ((n%k)+'0'));
            n = n/k;
        }
        stack.push((char)(n+'0'));
        while(!stack.isEmpty()){
            char ch = stack.pop();
            if(ch=='0'){
                if(!str.equals("1")&&str.length()!=0)
                    list.add(Long.parseLong(str));
                str="";

            }else{
                str+=ch;
            }
        }
        if(str.length()!=0&&!str.equals("1"))
            list.add(Long.parseLong(str)); //마지막 스택에 남은 수
        for(int i=0;i<list.size();i++){
            if(list.get(i)<10){
                if(list.get(i)==2||list.get(i)==3||list.get(i)==5||list.get(i)==7||list.get(i)==9)
                    answer++;
                else
                    continue;
            }
            else{
                if(isPrime(list.get(i)))
                    answer++;
            }
        }


        return answer;
    }

    private static boolean isPrime(Long num){ //Long 타입으로 안하면 런타임 에러 or int overflow
        for(long i = 2; i*i <= num; i++)
            if(num % i == 0) return false;
        return true;
    }
}
