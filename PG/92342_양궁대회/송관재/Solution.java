class Solution {
    static int[] answer;
    static int[] info2 = new int[11];
    static int maxDiff = -1;
    
    public int[] solution(int n, int[] info) {
        dfs(0, n, info);
        if(maxDiff <= 0){
            answer = new int[] {-1};
        }
        return answer;
    }
    
    public static void dfs(int depth, int n, int[] info){
        if(n == 0){
            int apeach = 0;
            int lion = 0;
            for(int i=0; i<11; i++){
                if(info2[i] > info[i]){
                    lion += 10 - i;
                } else if (info[i] != 0){
                    apeach += 10 - i;
                }
            }
            int diff = lion - apeach;
            if(maxDiff < diff){
                maxDiff = diff;
                answer = info2.clone();
            }
            if(maxDiff == diff && answer != null){
                for(int i=10; i>=0; i--){
                    if(info2[i] > answer[i]){
                        answer = info2.clone();
                        break;
                    } else if(answer[i] > info2[i]){
                        break;
                    }
                }
            }
            return;
        }else if(depth == 11){
            info2[10] += n;
            int apeach = 0;
            int lion = 0;
            for(int i=0; i<11; i++){
                if(info2[i] > info[i]){
                    lion += 10 - i;
                } else if (info[i] != 0){
                    apeach += 10 - i;
                }
            }
            int diff = lion - apeach;
            if(maxDiff < diff){
                maxDiff = diff;
                answer = info2.clone();
            }
            if(maxDiff == diff && answer != null){
                for(int i=10; i>=0; i--){
                    if(info2[i] > answer[i]){
                        answer = info2.clone();
                        break;
                    } else if(answer[i] > info2[i]){
                        break;
                    }
                }
            }
            info2[10] -= n;
            return;
        }
        if(n > info[depth]){
            info2[depth] = info[depth] + 1;
            dfs(depth+1, n - (info[depth] + 1), info);
            info2[depth] = 0;
        }
        dfs(depth+1, n, info);
    }
}