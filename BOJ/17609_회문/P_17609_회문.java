package baekjoon.prob17609;

import java.util.Scanner;

public class P_17609_회문 {

    static int T;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(sc.nextLine());

        for (int t = 0; t < T; t++) {
            String input = sc.nextLine();

            sb.append(findAnswer(input, 0, input.length() - 1, 0)).append("\n");
        }

        System.out.println(sb);
    }

    static int findAnswer(String str, int left, int right, int cnt) {
        while(left < right) {
            // str[left] == str[right]
            char leftCh = str.charAt(left);
            char rightCh = str.charAt(right);
            if (leftCh == rightCh) {
                left++;
                right--;
                continue;
            }
            // left right 문자가 다르다면 두가지 경우가 존재할 수 있다
            // input : xabba
            // case 1:
            //  x를 제거하고 그 다음 문자들을 비교하는경우
            // case 2:
            //  a를 제거하고 그 다음 문자들을 비교하는경우
            // 케이스가 두가지로 항상 나뉘어서 탐색범위가 매우 큰것 같지만 문제 조건에서 단 한번 문자 제거를 가능하게 하기때문에(유사 회문)
            // 탐색범위가 생각보다 작다
            else {
                cnt++;
                char next = str.charAt(left + 1);
                char pre = str.charAt(right - 1);

                if (next != rightCh && pre != leftCh) return 2;
                if (next == rightCh && pre == leftCh) {
                    // left에 있는 문자를 제거하는 경우
                    int res1 = findAnswer(str, left + 1, right, cnt);
                    // right에 있는 문자를 제거하는 경우
                    int res2 = findAnswer(str, left, right - 1, cnt);
                    return Math.min(res1, res2);
                }
                if (next == rightCh) {
                    left++;
                    continue;
                }
                if (pre == leftCh) {
                    right--;
                    continue;
                }
            }
        }

        // 문자를 두번이상 제거했으니 더이상 유사회문도, 회문도 아니다
        if (cnt > 1) return 2;
        // 문자를 단한번만 제거하고 while loop을 빠져나왔으므로 유사회문
        if (cnt == 1) return 1;

        // while loop을 돌면서 cnt가 0이기 때문에 회문
        return 0;
    }
}

