class Solution {
    public int solution(int[] stones, int k) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i=0; i<stones.length; i++){
            max = Math.max(max, stones[i]);
            min = Math.min(min, stones[i]);
        }

        int answer = 0;
        while(max >= min){
            int mid = (max+min)/2;  // 건널 수 있는 친구들의 수

            int cnt = 0;  // 밟지 못하는 디딤돌의 수
            boolean cantGo = false;
            for (int i=0; i<stones.length; i++){
                if (stones[i]-mid < 0){  // 디딤돌에 적힌 수 - 친구 수가 음수라면
                    cnt++;
                } else{  // 디딤돌을 밟을 수 있으면 cnt 초기화
                    cnt = 0;
                }

                if (cnt == k){  // 밟지 못하는 디딤돌의 수가 k라면
                    cantGo = true;
                }
            }

            if (!cantGo){  // 징검다리 건널 수 있을 때
                min = mid+1;
                answer = Math.max(answer, mid);
            } else{  // 징검다리 못 건널 때
                max = mid-1;
            }
        }

        return answer;
    }
}
