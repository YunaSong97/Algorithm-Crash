import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17281 {

    static int N;
    static int[][] players;
    static boolean[] v;
    static int[] order;
    static int max;
    static int c=0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        players = new int[N+1][10];
        order = new int[10];
        v = new boolean[10];
        max = Integer.MIN_VALUE;

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<10;j++){
                players[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(1);
        System.out.println(max);
        //System.out.println(c);

    }
    static void solve(int depth){
        if(depth==10){
            check();
            return ;
        }
        for(int i=2;i<=9;i++){
            if(depth==4){
                order[depth] = 1;
                v[1] = true;
                depth++;
            }
            if(v[i]) continue;
            v[i] = true;
            order[depth] = i;
            solve(depth+1);
            v[i] = false;
        }


    }
    static void check(){
        //N*N
        ArrayList<Integer> list = new ArrayList();
        int inning = 1;
        int score = 0;
        int out =0;
        int pos = 1;
        while(inning<=N){
            if(players[inning][order[pos]]==0){  //아웃인 경우
                out++;
                if(out==3){
                    list.clear(); //주자 제거
                    inning++; //이닝 증가
                    out = 0;
                }
            }
            else if(players[inning][order[pos]]==4){ //홈런인 경우
                if(list.isEmpty()){
                    score++;   //솔로홈런
                }else{
                    score += list.size() + 1;
                    list.clear();  //주자 초기화
                }
            }
            else{
                if(list.isEmpty()){  // 주자가 없는 경우
                    list.add(players[inning][order[pos]]);
                }else {  //이미 루 상에 주자가 있는 경우
                    int index=0;
                    while(index<list.size()){ //주자들을 이동시킨다
                        list.set(index,list.get(index) + players[inning][order[pos]]);
                        if (list.get(index) >= 4) {
                            score++;
                            list.remove(index);
                        }else {
                            index++;
                        }
                    }
                    list.add(players[inning][order[pos]]);
                }
            }
            pos++;
            if(pos==10)
                pos = 1;
        }
        max = Math.max(max,score);
    }
}


