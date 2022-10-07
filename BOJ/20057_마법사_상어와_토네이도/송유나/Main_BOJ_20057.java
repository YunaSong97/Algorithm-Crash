import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_20057 {
	static int n;
	static int[][] map;
	static int[][][] filter = {
			{ { 0, 0, 2, 0, 0 }, { 0, 10, 7, 1, 0 }, { 5, -1, 0, 0, 0 }, { 0, 10, 7, 1, 0 }, { 0, 0, 2, 0, 0 } },
			{ { 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 0 }, { 2, 7, 0, 7, 2 }, { 0, 10, -1, 10, 0 }, { 0, 0, 5, 0, 0 } },
			{ { 0, 0, 2, 0, 0 }, { 0, 1, 7, 10, 0 }, { 0, 0, 0, -1, 5 }, { 0, 1, 7, 10, 0 }, { 0, 0, 2, 0, 0 } },
			{ { 0, 0, 5, 0, 0 }, { 0, 10, -1, 10, 0 }, { 2, 7, 0, 7, 2 }, { 0, 1, 0, 1, 0 }, { 0, 0, 0, 0, 0 } } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		int initSand = 0;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				initSand += map[i][j];
			}
		}

		moveTornado(n / 2, n / 2);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				initSand -= map[i][j];
			}
		}

		System.out.println(initSand);
	}

	static void moveTornado(int x, int y) {
		int[] dx = { 0, 1, 0, -1 };// 좌 하 우 상
		int[] dy = { -1, 0, 1, 0 };
		int cnt = 1;
		int dir = 0;

		while (cnt < n - 1) {

			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < cnt; j++) {
					x += dx[dir % 4];
					y += dy[dir % 4];

					if (map[x][y] != 0) {
						moveSand(dir, x, y);
					}
				}

				dir++;
			}

			cnt++;
		}

		if (cnt == n - 1) {

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < cnt; j++) {
					x += dx[dir % 4];
					y += dy[dir % 4];

					if (map[x][y] != 0) {
						moveSand(dir, x, y);
					}
				}

				dir++;
			}
		}
	}

	static void moveSand(int dir, int x, int y) {
		int alphaX = -1;
		int alphaY = -1;
		int init = map[x][y];

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {

				if (filter[dir % 4][i][j] != 0) {
					if (isIn(x + i - 2, y + j - 2)) {
						if (filter[dir % 4][i][j] == -1) {
							alphaX = x + i - 2;
							alphaY = y + j - 2;
						} else {
							int move = (int) (map[x][y] * filter[dir % 4][i][j] * 0.01);
							map[x + i - 2][y + j - 2] += move;
							init -= move;
						}
					} else {
						int move = (int) (map[x][y] * filter[dir % 4][i][j] * 0.01);
						init -= move;
					}
				}
			}
		}

		if (alphaX != -1) {
			map[alphaX][alphaY] += init;
		}

		map[x][y] = 0;
	}

	static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < n && y < n;
	}
}
