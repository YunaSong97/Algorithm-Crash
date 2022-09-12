public class PG_118667 {
    class Solution {
        public int solution(int[] queue1, int[] queue2) {
            int answer = 0;
            int n1 = queue1.length;
            int n = n1 * 2;
            int[] queue = new int[n];

            long sum = 0;
            for (int i = 0; i < n1; i++) {
                queue[i] = queue1[i];
                sum += queue[i];
            }
            for (int i = n1; i < n; i++) {
                queue[i] = queue2[i - n1];
                sum += queue[i];
            }

            int l = 0;
            int h = n1 - 1;
            long S = 0;
            for (int i = 0; i < n1; i++) {
                S += queue[i];
            }
            while (l < n) {
                if (S == sum / 2) {
                    break;
                }

                if (S > sum / 2) {
                    S -= queue[l];
                    l++;
                } else {
                    h++;
                    S += queue[h % n];
                }
                answer++;
            }

            if (S != sum / 2) {
                return -1;
            }
            return answer;
        }
    }
}
