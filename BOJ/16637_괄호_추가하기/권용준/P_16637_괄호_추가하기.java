package baekjoon.prob16637;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class P_16637_괄호_추가하기 {

    static int N;
    static int ans = Integer.MIN_VALUE;
    static ArrayList<Character> operators = new ArrayList<>();
    static ArrayList<Integer> nums = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        String input = br.readLine();

        for (int i = 0; i < input.length(); i++) {
            Character c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*')
                operators.add(c);
            else
                nums.add(c - '0');
        }

        // 처음엔 가장 앞쪽에 있는 숫자와 현재 연산해야할 연산자 idx를 넘긴다.
        dfs(nums.get(0), 0);
        System.out.println(ans);
    }

    static int calculation(Character op, int left, int right) {
        if (op == '+') {
            return left + right;
        } else if(op == '-') {
            return left - right;
        } else {
            return left * right;
        }
    }
    static void dfs(int res, int opIdx) {
        if (operators.size() <= opIdx) {
            ans = Math.max(ans, res);
            return;
        }

        // case1 : 괄호 안친 경우
        int result1 = calculation(operators.get(opIdx), res, nums.get(opIdx + 1));
        dfs(result1, opIdx + 1);
        // case2 : 괄호 친 경우
        if (opIdx + 1 < operators.size()) { // 3 * (1 + 2)
            int rightResult = calculation(operators.get(opIdx + 1), nums.get(opIdx + 1), nums.get(opIdx + 2)); // (1+2)
            dfs(calculation(operators.get(opIdx), res, rightResult), opIdx + 2); // 현재 연산자와 그 다음 연산자를 이용해서 연산했으니 index + 2
        }
    }
}
