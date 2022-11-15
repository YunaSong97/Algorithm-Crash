import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] scores;
	static int N;
	static int outCnt = 0;
	static int turn = 0;
	static int score = 0;
	static int answer = 0;
	static int[] order = new int[9];
	static boolean[] base = new boolean[3];
	static boolean[] visit = new boolean[9];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		scores = new int[N][9];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < 9; j++) {
				scores[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		order[3] = 0;
		visit[0] = true;

		makeOrder(0);

		System.out.println(answer);
	}

	static void play() {
		score = 0;
		turn = 0;

		for (int i = 0; i < N; i++) {
			outCnt = 0;
			base = new boolean[3];

			while (outCnt < 3) {
				int player = order[turn % 9];

				switch (scores[i][player]) {
				case 0:
					out();
					break;
				case 1:
					safety();
					break;
				case 2:
					two();
					break;
				case 3:
					three();
					break;
				case 4:
					homerun();
					break;
				}

				turn++;
			}
		}
	}

	static void makeOrder(int depth) {
		if (depth == 9) {
			play();

			answer = Math.max(answer, score);

			return;
		}

		for (int i = 1; i < 9; i++) {
			if (visit[i]) {
				continue;
			}

			if (depth == 3) {
				depth++;
			}

			visit[i] = true;
			order[depth] = i;
			makeOrder(depth + 1);
			visit[i] = false;

		}
	}

	static void safety() {
		for (int i = 2; i >= 0; i--) {
			if (base[i]) {
				if (i + 1 > 2) {
					score++;
					base[i] = false;
				} else {
					base[i + 1] = true;
					base[i] = false;
				}
			}
		}

		base[0] = true;
	}

	static void two() {
		for (int i = 2; i >= 0; i--) {
			if (base[i]) {
				if (i + 2 > 2) {
					score++;
					base[i] = false;
				} else {
					base[i + 2] = true;
					base[i] = false;
				}
			}
		}

		base[1] = true;
	}

	static void three() {
		for (int i = 2; i >= 0; i--) {
			if (base[i]) {
				score++;
				base[i] = false;
			}
		}

		base[2] = true;
	}

	static void homerun() {
		score++;

		for (int i = 0; i < 3; i++) {
			if (base[i]) {
				score++;
				base[i] = false;
			}
		}
	}

	static void out() {
		outCnt++;
	}
}
