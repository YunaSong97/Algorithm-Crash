import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main_BOJ_2531 {
	static ArrayList<Integer> liqList;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int d = sc.nextInt();
		int k = sc.nextInt();
		int c = sc.nextInt();
		int[] belt = new int[n];
		int ans = 0;

		HashMap<Integer, Integer> hashMap = new HashMap();

		for (int i = 0; i < n; i++) {
			belt[i] = sc.nextInt();
		}

		for (int i = 0; i < k; i++) {// 초기
			hashMap.put(belt[i], hashMap.getOrDefault(belt[i], 0) + 1);
		}

		if (hashMap.containsKey(c)) {
			ans = hashMap.size();
		} else {
			ans = hashMap.size() + 1;
		}

		for (int i = 1; i < n; i++) {
			if (ans == k + 1) {
				break;
			}

			hashMap.put(belt[(i + k - 1) % n], hashMap.getOrDefault(belt[(i + k - 1) % n], 0) + 1);

			hashMap.put(belt[i % n - 1], hashMap.get(belt[i % n - 1]) - 1);

			if (hashMap.get(belt[i % n - 1]) == 0) {
				hashMap.remove(belt[i % n - 1]);
			}

			if (hashMap.containsKey(c)) {
				ans = Math.max(ans, hashMap.size());
			} else {
				ans = Math.max(ans, hashMap.size() + 1);
			}
		}

		System.out.println(ans);
	}
}
