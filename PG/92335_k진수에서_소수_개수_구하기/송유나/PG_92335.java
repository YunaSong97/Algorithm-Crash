public class PG_92335 {
    class Solution {
        public int solution(int n, int k) {
            int answer = 0;

            String s = Integer.toString(n, k);
            String[] tokens = s.split("0+");

            if (tokens.length == 0) {
                return 0;
            }
            for (String t : tokens) {
                if (isPrime(Long.parseLong(t))) {
                    answer++;
                }
            }

            return answer;
        }

        static boolean isPrime(long num) {
            if(num == 1){
                return false;
            }

            if(num == 2){
                return true;
            }

            for (long i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    return false;
                }
            }

            return true;
        }
    }
}
