import java.util.Arrays;

public class PG_양궁대회 {
    static int[] ans = new int[11];
    static int[] pick;
    static int[] result={-1};
    static int c;
    static int max = Integer.MIN_VALUE;
    static int count;
    public static int[] solution(int n,int[] info){
        pick = new int[11];
        bt(1,n,info);
        //System.out.println(count);
        return result;
    }
    static void bt(int depth,int n,int[] info){ //과녁을 맞힐수 있는 경우 백트래킹
        if(depth==n+1){
            int ascore=0;
            int lscore=0;
            for(int i=0;i<info.length;i++){
                if(info[i]!=0||pick[i]!=0){
                    if(pick[i]>info[i])
                        lscore=lscore+(10-i);
                    else ascore=ascore+(10-i);
                }
            }
            int sco = lscore-ascore;
            if(sco>=max&&lscore>ascore){
                //System.out.println("라이언:"+lscore+", 어치피:"+ascore);
                result= pick.clone();
                max = sco;
            }
            return ;
        }
        for(int i=0;i<11&&pick[i]<=info[i];i++){
            // if(pick[i]<=info[i]){
            pick[i]++;
            bt(depth + 1, n, info);
            pick[i]--;
            // }
            // else break;

        }

    }
//    static int getScore(int[] scores,int[] info){
//        int ascore=0;
//        int lscore=0;
//        for(int i=0;i<info.length;i++){
//            if(scores[i]!=0||info[i]!=0){
//                if(scores[i]>info[i])
//                    lscore=lscore+(10-i);
//                else ascore=ascore+(10-i);
//            }
//        }
//        int sco = lscore-ascore;
//        if(sco>=max&&lscore>ascore){
//            //System.out.println("라이언:"+lscore+", 어치피:"+ascore);
//            max = Math.max(max,sco);
//            count++;
//            //System.out.println(Arrays.toString(scores));
//            return 1;
//        }
//        else{
//            return -1;
//        }
//
//    }


}
