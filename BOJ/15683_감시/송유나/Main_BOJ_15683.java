import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class CCTV {
	int x, y;
	int num;

	public CCTV(int x, int y, int num) {
		super();
		this.x = x;
		this.y = y;
		this.num = num;
	}
}

public class Main_BOJ_15683 {
	static int n, m;
	static int[][] map;
	static int[][] copy;
	static int answer;
	static ArrayList<boolean[]>[] cctvs = new ArrayList[6];
	static ArrayList<CCTV> aryList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		answer = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] != 0 && map[i][j] != 6) {
					aryList.add(new CCTV(i, j, map[i][j]));
				}
			}
		}

		for (int i = 0; i < 6; i++) {
			cctvs[i] = new ArrayList<>();
		}
		
		copy = new int[n][m];
		for(int i=0;i<n;i++) {
			System.arraycopy(map[i], 0, copy[i], 0, m);
		}
		
		cctvs[1].add(new boolean[] { true, false, false, false });// 상 우 하 좌
		cctvs[1].add(new boolean[] { false, true, false, false });
		cctvs[1].add(new boolean[] { false, false, true, false });
		cctvs[1].add(new boolean[] { false, false, false, true });
		cctvs[2].add(new boolean[] { true, false, true, false });
		cctvs[2].add(new boolean[] { false, true, false, true });
		cctvs[4].add(new boolean[] { true, true, true, false });
		cctvs[4].add(new boolean[] { true, true, false, true });
		cctvs[4].add(new boolean[] { true, false, true, true });
		cctvs[4].add(new boolean[] { false, true, true, true });
		cctvs[3].add(new boolean[] { true, true, false, false });
		cctvs[3].add(new boolean[] { false, true, true, false });
		cctvs[3].add(new boolean[] { false, false, true, true });
		cctvs[3].add(new boolean[] { true, false, false, true });
		cctvs[5].add(new boolean[] { true, true, true, true });

		int[] dirs = new int[aryList.size()];
		makeDir(dirs, 0);

		System.out.println(answer);
	}

	static void makeDir(int[] dirs, int depth) {
		if (depth == aryList.size()) {
			for (int i = 0; i < aryList.size(); i++) {
				check(cctvs[aryList.get(i).num].get(dirs[i]), aryList.get(i).x, aryList.get(i).y);
			}

			int cnt = 0;

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 0) {
						cnt++;
					}
				}
			}

			for (int i = 0; i < n; i++) {
				System.arraycopy(copy[i], 0, map[i], 0, m);
			}

			answer = Math.min(cnt, answer);
			return;
		}

		CCTV c = aryList.get(depth);
		for (int i = 0; i < cctvs[c.num].size(); i++) {
			dirs[depth] = i;
			makeDir(dirs, depth + 1);
		}
	}

	static void check(boolean[] dir, int x, int y) {
		int[] dx = { -1, 0, 1, 0 };
		int[] dy = { 0, 1, 0, -1 };

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (dir[i]) {
				while (isIn(nx, ny) && map[nx][ny] != 6) {
					if (map[nx][ny] == 0) {
						map[nx][ny] = -1;
					}
					nx += dx[i];
					ny += dy[i];
				}
			}
		}
	}

	static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < n && y < m;
	}
}
