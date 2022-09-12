public class PG_92342 {
    class Solution {
        static int[] res;
        static int[] ans = {-1};
        static int max = 0;

        public int[] solution(int n, int[] info) {
            res = new int[11];

            combination(info, 0, n);

            if (max == 0) {
                return new int[]{-1};
            }
            return ans;
        }

        private void dfs(int[] info, int depth, int n) {
            if (depth == n) {
                int apeach = 0;
                int lion = 0;

                for (int i = 0; i < 10; i++) {
                    if (info[i] == 0 && res[i] == 0) {
                        continue;
                    }
                    if (info[i] >= res[i]) {
                        apeach += 10 - i;
                    } else if (info[i] < res[i]) {
                        lion += 10 - i;
                    }
                }

                if (lion - apeach >= max) {
                    max = lion - apeach;
                    ans = res.clone();
                }

                return;
            }

            for (int i = 0; i < 11 && res[i] <= info[i]; i++) {//어피치 점수보다 작거나 같을때까지만 실행 왜냐하면 어피치 점수보다 크면 어차피 점수를 따기 때문에
                res[i]++;
                dfs(info, depth + 1, n);
                res[i]--;
            }
        }
    }
}
