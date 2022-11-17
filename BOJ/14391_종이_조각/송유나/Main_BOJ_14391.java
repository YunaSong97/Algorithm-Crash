package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean[][] dir;
	static int[][] paper;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		paper = new int[N][M];
		dir = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();

			for (int j = 0; j < M; j++) {
				paper[i][j] = Character.getNumericValue(str.charAt(j));
			}
		}

		makeDir(0, 0);
		System.out.println(answer);
	}

	static int answer = Integer.MIN_VALUE;

	static void makeDir(int x, int y) {
		if (y >= M) {
			y = 0;
			x++;
		}

		if (x >= N) {// 배열 다 만듦 합구하기
			int sum = 0;
			int unitSum = 0;
			// 가로 계산
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (dir[i][j]) {// 연결
						unitSum *= 10;
						unitSum += paper[i][j];
					} else {// 끊김
						sum += unitSum;
						unitSum = 0;
					}
				}
				sum += unitSum;
				unitSum = 0;
			}

			unitSum = 0;
			// 세로 계산
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (!dir[j][i]) {// 연결
						unitSum *= 10;
						unitSum += paper[j][i];
					} else {// 끊김
						sum += unitSum;
						unitSum = 0;
					}
				}
				sum += unitSum;
				unitSum = 0;
			}

			answer = Math.max(sum, answer);
			return;
		}

		dir[x][y] = true;
		makeDir(x, y + 1);
		dir[x][y] = false;
		makeDir(x, y + 1);
	}
}
