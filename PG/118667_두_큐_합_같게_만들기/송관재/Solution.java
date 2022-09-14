import java.util.ArrayDeque;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        int max = 0;
        long sum1 = 0;
        for(int i=0; i<queue1.length; i++){
            sum1 += queue1[i];
            if(max < queue1[i]){
                max = queue1[i];
            }
        }
        long sum2 = 0;
        for(int i=0; i<queue2.length; i++){
            sum2 += queue2[i];
            if(max < queue2[i]){
                max = queue2[i];
            }
        }
        long sum = sum1 + sum2;
        long half = sum / 2;
        if(max > half){
            answer = -1;
        } else {
            ArrayDeque<Integer> q1 = new ArrayDeque<>();
            ArrayDeque<Integer> q2 = new ArrayDeque<>();
            for(int i=0; i<queue1.length; i++){
                q1.offer(queue1[i]);
            }
            for(int i=0; i<queue2.length; i++){
                q2.offer(queue2[i]);
            }
            while(sum1 != sum2) {
                if(sum1 > sum2) {
                    int temp = q1.poll();
                    sum1 -= temp;
                    q2.offer(temp);
                    sum2 += temp;
                } else {
                    int temp = q2.poll();
                    sum2 -= temp;
                    q1.offer(temp);
                    sum1 += temp;
                }
                answer++;
            }
        }
        return answer;
    }
}