import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        
        long sumA = 0;
        long sumB = 0;
        int maxNumA = Integer.MIN_VALUE;
        int maxNumB = Integer.MIN_VALUE;
        int[] queueA = new int[queue1.length * 3];
        int[] queueB = new int[queue1.length * 3];
        
        for (int i = 0; i < queue1.length; i++) {
            if (maxNumA < queue1[i])
                maxNumA = queue1[i];
                
            if (maxNumB < queue2[i])
                maxNumB = queue2[i];
                
            sumA += queue1[i];
            sumB += queue2[i];
            
            queueA[i] = queue1[i];
            queueB[i] = queue2[i];
        }
        
        if ((sumA + sumB) / 2 < maxNumA || (sumA + sumB) / 2 < maxNumB)
            return -1;
        
        if ((sumA + sumB) % 2 == 1) return -1;
        // sumA, sumB를 관리해서 sumA < sumB이면 sumB -> subA 원소이동
        // sumA > sumB이면 subA -> sumB 원소 이동시키며 두 큐의 합이 같을때까지 이동
        
        int cnt = 0;
        int rearA = queue1.length - 1, rearB = queue2.length-1;
        int frontA = -1, frontB = -1;
        
        int q1Len = queueA.length;
        int q2Len = queueB.length;
        
        long preSumA = 0;
        long preSumB = 0;
        
        while(sumA != sumB) {
            if(cnt >= queue1.length * 3)
    			return -1;
            preSumA = sumA;
            preSumB = sumB;
            if (sumA < sumB) {
                frontB = (frontB + 1) % q2Len;
                int num = poll(queueB, q2Len, frontB);
                sumA += num;
                sumB -= num;
                rearA = (rearA + 1) % q1Len;
                offer(queueA, num, q1Len, rearA);
//System.out.println("sumA < sumB - " + "frontB : " + frontB + " " + "rearA : " + rearA + " num : " + num);
            } else {
                frontA = (frontA + 1) % q1Len;
                int num = poll(queueA, q1Len, frontA);
                sumB += num;
                sumA -= num;
                rearB = (rearB + 1) % q2Len;
                offer(queueB, num, q2Len, rearB);
                //System.out.println("sumA > sumB - " + "frontA : " + frontA + " " + "rearB : " + rearB + " num : " + num);
            }
            //if (preSumA == sumB && preSumB == sumA) return -1;
            cnt++;
        }
    
        return cnt;
    }
    
    static void offer(int[] queue, int num, int size, int rear) {
        queue[rear] = num;
    }
    
    static int poll(int[] queue, int size, int front) {
        return queue[front];
    }
}
