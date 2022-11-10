import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_16234 {
	static int N, L, R;
	static int map[][];
	static int temp[][];
	static boolean isOpen = true;
	static boolean[][] visited;

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int day = 0;

		while (true) {
			isOpen = false;
			visited = new boolean[N][N];
			temp = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j])
						open(i, j);
				}
			}

			if (isOpen) {// 인구이동이 있으면
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (temp[i][j] != 0)
							map[i][j] = temp[i][j];
					}
				}
			} else
				break;

			day++;
		}

		System.out.println(day);
	}

	static void open(int x, int y) {
		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };
		int people = map[x][y];
		List<Node> nodes = new ArrayList<>();
		Queue<Node> queue = new ArrayDeque<>();

		queue.add(new Node(x, y));
		nodes.add(new Node(x, y));
		visited[x][y] = true;

		while (!queue.isEmpty()) {
			Node now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (!isIn(nx, ny))
					continue;

				int diff = Math.abs(map[now.x][now.y] - map[nx][ny]);

				if (diff <= R && diff >= L && !visited[nx][ny]) {
					visited[nx][ny] = true;
					isOpen = true;
					queue.add(new Node(nx, ny));
					nodes.add(new Node(nx, ny));
					people += map[nx][ny];
				}
			}
		}

		int cnt = nodes.size();
		if (cnt != 1 && isOpen) {
			for (Node n : nodes) {
				temp[n.x][n.y] = people / cnt;
			}
		}
	}

	static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}
}
