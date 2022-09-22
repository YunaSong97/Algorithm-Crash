import java.util.Arrays;

class Solution {
    public int solution(int[] stones, int k) {
        int[] temp = stones.clone();
        Arrays.sort(temp);
        int answer = 0;
        int start = temp[0];
        int end = temp[temp.length - 1];
        int mid = (start + end) / 2;
        boolean flag = true;
        while (start <= end) {
            flag = true;
            int count = 0;
            int idx = 0;
            while (idx < stones.length) {
                if (count == k) {
                    flag = false;
                    break;
                }
                if (stones[idx] >= mid) {
                    idx++;
                    count = 0;
                } else {
                    idx++;
                    count++;
                }
            }
            if (flag) {
                if (count == k) {
                    end = mid - 1;
                } else {
                    answer = mid;
                    start = mid + 1;
                }
            } else {
                end = mid - 1;
            }
            mid = (start + end) / 2;
        }
        return answer;
    }
}