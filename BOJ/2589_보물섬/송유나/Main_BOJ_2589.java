import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int x, y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int n, m;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j);
				
			}
		}

		int max = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 'L') {
					max = Math.max(max, bfs(new Node(i, j)));
				
				}
			}
		}
		
		System.out.println(max - 1);
	}

	static int bfs(Node start) {
		boolean[][] visit = new boolean[n][m];
		Queue<Node> queue = new ArrayDeque<>();
		int depth = 0;

		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };

		queue.add(start);
		visit[start.x][start.y] = true;

		while (!queue.isEmpty()) {

			int size = queue.size();
			while (size-- > 0) {
				Node now = queue.poll();

				for (int i = 0; i < 4; i++) {
					int nx = now.x + dx[i];
					int ny = now.y + dy[i];

					if (isIn(nx, ny) && map[nx][ny] == 'L' && !visit[nx][ny]) {
						queue.add(new Node(nx, ny));
						visit[nx][ny] = true;
					}
				}
			}

			depth++;
		}

		return depth;
	}

	static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < n && y < m;
	}
}
