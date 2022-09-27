import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Consulting {
	int time;
	int pay;

	public Consulting(int time, int pay) {
		this.time = time;
		this.pay = pay;
	}
}

public class Main {

	static Consulting[] consults;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		consults = new Consulting[n];
		answer = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			consults[i] = new Consulting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		dfs(n, 0, 0);

		System.out.println(answer);
	}

	static void dfs(int n, int idx, int pay) {
		if (idx >= n) {
			if (idx == n) {
				answer = Math.max(answer, pay);
			}
			return;
		}

		dfs(n, idx + consults[idx].time, consults[idx].pay + pay);
		dfs(n, idx + 1, pay);
	}
}
