import java.util.ArrayList;
import java.util.Scanner;

class Main_BOJ_15661 {
	static int[][] map;
	static ArrayList<Integer> start;
	static ArrayList<Integer> link;
	static int ans;

	public static void main(String args[]) {

		Scanner sc = new Scanner(System.in);

		ans = Integer.MAX_VALUE;
		int n = sc.nextInt();
		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		boolean[] visit = new boolean[n];

		for (int i = 1; i <= n / 2; i++) {
			comb(visit, 0, n, i);
		}

		System.out.println(ans);
	}

	static void comb(boolean[] visit, int idx, int n, int r) {
		if (r == 0) {
			start = new ArrayList<>();
			link = new ArrayList<>();

			for (int j = 0; j < n; j++) {
				if (visit[j]) {
					start.add(j);
				} else {
					link.add(j);
				}
			}

			int startSum = 0;
			for (int i = 0; i < start.size() - 1; i++) {
				for (int j = i + 1; j < start.size(); j++) {
					startSum += map[start.get(i)][start.get(j)];
					startSum += map[start.get(j)][start.get(i)];
				}
			}

			int linkSum = 0;
			for (int i = 0; i < link.size() - 1; i++) {
				for (int j = i + 1; j < link.size(); j++) {
					linkSum += map[link.get(i)][link.get(j)];
					linkSum += map[link.get(j)][link.get(i)];
				}
			}

			ans = Math.min(ans, Math.abs(linkSum - startSum));
			return;
		}

		if (idx < n) {
			visit[idx] = true;
			comb(visit, idx + 1, n, r - 1);
      
			visit[idx] = false;
			comb(visit, idx + 1, n, r);
		}
	}
}
