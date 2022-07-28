package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_BOJ_16637 {
    static ArrayList<Character> op = new ArrayList<>();
    static ArrayList<Integer> num = new ArrayList<>();
    static int max = -99999;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String input = br.readLine();

        if (n == 1) {
            System.out.println(input);
            return;
        }

        for (int i = 0; i < n; i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                num.add(Character.getNumericValue(c));
            } else {
                op.add(c);
            }
        }

        if (n == 3) {
            System.out.println(cal(num.get(0), num.get(1), op.get(0)));
            return;
        }

        boolean[] visited = new boolean[op.size()];
        for (int i = 1; i <= op.size() / 2; i++) {
            getOpList(visited, 0, i);
        }

        System.out.println(max);
    }

    static int cal(int a, int b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        return 0;
    }

    static void getOpList(boolean[] visited, int n, int r) {

        if (r == 0) {
            ArrayList<Integer> newNumList = new ArrayList<>();
            ArrayList<Integer> numCopy = new ArrayList<>(num);
            ArrayList<Character> newOpList = new ArrayList<>();
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    newNumList.add(cal(numCopy.get(i), numCopy.get(i + 1), op.get(i)));
                    numCopy.set(i, -1);
                    numCopy.set(i + 1, -1);
                } else {
                    if (numCopy.get(i) != -1) {
                        newNumList.add(numCopy.get(i));
                        numCopy.set(i, -1);
                    }
                    if (i == visited.length - 1) {
                        newNumList.add(numCopy.get(i+1));
                    }
                    newOpList.add(op.get(i));
                }
            }
            int res = 0;
            for (int i = 0; i < newOpList.size(); i++) {
                if (i == 0) {
                    res = cal(newNumList.get(i), newNumList.get(i + 1), newOpList.get(i));
                } else {
                    res = cal(res, newNumList.get(i+1), newOpList.get(i));
                }
            }
            max = Math.max(max, res);
            return;
        }
        if (n >= op.size()) {
            return;
        } else {
            visited[n] = true;
            getOpList(visited, n + 2, r - 1);

            visited[n] = false;
            getOpList(visited, n + 1, r);

        }
    }
}
