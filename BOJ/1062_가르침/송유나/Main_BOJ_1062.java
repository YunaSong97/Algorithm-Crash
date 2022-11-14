import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_1062 {
	static int N, K;
	static List<String> words = new ArrayList<>();
	static int cnt = Integer.MIN_VALUE;
	static boolean[] isUsed = new boolean[26];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		isUsed['a' - 97] = true;
		isUsed['n' - 97] = true;
		isUsed['t' - 97] = true;
		isUsed['i' - 97] = true;
		isUsed['c' - 97] = true;

		if (K < 5) {
			System.out.println(0);

			return;
		}

		for (int i = 0; i < N; i++) {
			words.add(br.readLine());
		}

		getAlphabat(0, 0);

		System.out.println(cnt);
	}

	static void getAlphabat(int start, int r) {
		if (r == K - 5) {
			int tempCnt = 0;

			for (String word : words) {
				boolean readable = true;

				for (int i = 0; i < word.length(); i++) {
					if (!isUsed[word.charAt(i) - 97]) {
						readable = false;
						break;
					}
				}

				if (readable)
					tempCnt++;
			}

			cnt = Math.max(cnt, tempCnt);

			return;
		}

		for (int i = start; i < 26; i++) {
			if (!isUsed[i]) {
				isUsed[i] = true;
				getAlphabat(i + 1, r + 1);
				isUsed[i] = false;
			}
		}
	}
}
