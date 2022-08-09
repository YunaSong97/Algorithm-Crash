package backjoon;

import java.util.Scanner;

public class P_2023_신기한_소수 {
	static int N;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		dfs(1, 2);
		dfs(1, 3);
		dfs(1, 5);
		dfs(1, 7);
		
		System.out.println(sb);
	}
	
	static void dfs(int depth, int num) {
		if (depth == N) {
			String strNum = String.valueOf(num);
			if (isPrime(Integer.parseInt(strNum))) {
				sb.append(strNum).append("\n");
			}
			return;
		}
		
		for (int i = 0; i < 10; i++) {
			String strNum = String.valueOf(num) + String.valueOf(i);
			if (isPrime(Integer.parseInt(strNum))) {
				dfs(depth + 1, Integer.parseInt(strNum));
			}
		}
	}
	
	static boolean isPrime(int num) {
		int i = 2;
		
		while (i * i <= num) {
			if (num % i == 0) return false;
			i += 1;
		}
		return true;
	}
}
