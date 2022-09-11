class Solution {
    static boolean[] visited;
    static int max;
    static int[] answer;

    public int[] solution(int n, int[] info) {
        visited = new boolean[info.length];
        answer = new int[11];
        max = -1;

        dfs(n, info, 0, new int[11]);

        if(max <= 0){
            answer = new int[]{-1};
        }
        return answer;
    }

    public void dfs(int n, int[] info, int arrowCnt, int[] tmp){
        if (arrowCnt == n){
            int apeach = 0;
            int lion = 0;

          // 어피치와 라이언 점수 계산
            for (int i=0; i<info.length; i++){
                if (info[i] < tmp[i]){
                    lion += (10-i);
                } else if (info[i] != 0){
                    apeach += (10-i);
                }
            }
            
            int sub = lion-apeach;
            if (max < sub){  // 점수차가 max보다 크다면 max갱신, 배열 복사
                max = sub;
                answer = tmp.clone();
            } else if (max == sub){  // 점수차가 max와 같다면 낮은 점수 많은 것을 선택
                int lowerCntTmp = 0;
                int lowerCntAns = 0;
                for (int i=0; i<info.length; i++){
                    if (tmp[i] != 0){
                        lowerCntTmp += i;
                    }
                    if (answer[i] != 0){
                        lowerCntAns += i;
                    }
                }

                if (lowerCntTmp > lowerCntAns){
                    answer = tmp.clone();
                }
            }
            return;
        }
      // 10~0점 다 탐색하기
        for (int i=0; i<info.length; i++){
            if (!visited[i]){
                visited[i] = true;
                
                int[] cpyTmp = tmp.clone();
                if (n-arrowCnt < info[i]){  // 쏠 수 있는 화살 갯수가 apeach가 쏜 과녁의 화살 수 보다 작다면 다 과녁0에 몰빵(테케 4번)
                    cpyTmp[10] = n-arrowCnt;
                    dfs(n, info, arrowCnt+n-arrowCnt, cpyTmp);
                }
                else if (arrowCnt+info[i]+1<=n && tmp[i]<=info[i]){  // 남은 화살 수가 apeach가 쏜 과녁의 화살 수 보다 더 많고, lion이 쏜 화살 개수가 apeach보다 작거나 같다면
                    cpyTmp[i] = info[i]+1;  // lion화살 수= apeach화살 수+1
                    dfs(n, info, arrowCnt+info[i]+1, cpyTmp);
                }

                visited[i] = false;
            }
        }
    }
}
