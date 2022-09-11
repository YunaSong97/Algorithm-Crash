import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long qSum = 0;
        long sum1 = 0, sum2 = 0;
        for(int i=0; i<queue1.length; i++){
            q1.add(queue1[i]);
            q2.add(queue2[i]);

            sum1 += queue1[i];
            sum2 += queue2[i];
            qSum += queue1[i]+queue2[i];
        }

        // 두 큐에 담긴 모든 원소들의 합이 2로 나누어떨어지지 않는다면 -1
        if(qSum%2 != 0){
            return -1;
        }

        int limit = queue1.length * 3;
        while (sum1 != sum2){
            if (sum1<sum2 && q2.size()>0){
                // q2에 있는 원소들의 합이 더 크면 q2의 값을 q1로 보내기
                int tmp = q2.poll();
                sum1 += tmp;
                sum2 -= tmp;
                q1.add(tmp);

                answer++;
            } else if (sum1>sum2 && q1.size()>0) {
                // q1에 있는 원소들의 합이 더 크면 q1의 값을 q2로 보내기
                int tmp = q1.poll();
                sum2 += tmp;
                sum1 -= tmp;
                q2.add(tmp);

                answer++;
            }

            // 무한반복 하지 않도록 조건 달기. limit의 수를 넘겨 카운팅 되면 -1
            if(answer > limit){
                return -1;
            }
        }
        
        return answer;
    }
}
