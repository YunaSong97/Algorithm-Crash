import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_21278 {
	static int N, M;
	static List<List<Integer>> adjList = new ArrayList<>();
	static int[][] timeMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		timeMap = new int[N + 1][N + 1];
		for (int i = 0; i < N + 1; i++) {
			adjList.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adjList.get(a).add(b);
			adjList.get(b).add(a);
		}

		int timeSum = 0;
		Answer answer = new Answer(0, 0, Integer.MAX_VALUE);

		for (int i = 1; i < N + 1; i++) {
			for (int j = i + 1; j < N + 1; j++) {
				getTime(i, j);
			}
		}
		
		for (int i = 1; i < N + 1; i++) {
			for (int j = i; j < N + 1; j++) {
				for (int k = 1; k < N+1; k++) {
					if (k != i && k != j) {
						timeSum += Math.min(timeMap[i][k], timeMap[j][k]);
						if (timeSum > answer.time) break;
					}
				}

				if (answer.time > timeSum) {
					answer.time = timeSum;
					answer.buildingNum1 = i;
					answer.buildingNum2 = j;
				}

				timeSum = 0;
			}
		}

		System.out.println(answer.toString());
	}

	static int getTime(int x, int y) {
		int time = 1;
		boolean[] visited = new boolean[N + 1];
		Queue<Integer> queue = new ArrayDeque<>();
		
		queue.add(x);
		visited[x] = true;

		while (!queue.isEmpty()) {
			int qSize = queue.size();

			while (qSize-- > 0) {
				int now = queue.poll();

				if (now == y) {
					timeMap[x][y] = time;
					timeMap[y][x] = time;
					return time;
				}

				for (int child : adjList.get(now)) {
					if (!visited[child]) {
						queue.add(child);
						visited[child] = true;
					}
				}
			}

			time++;
		}

		return time;
	}

	static class Answer {
		int buildingNum1, buildingNum2, time;

		public Answer(int buildingNum1, int buildingNum2, int time) {
			this.buildingNum1 = buildingNum1;
			this.buildingNum2 = buildingNum2;
			this.time = time;
		}
		
		public String toString() {
			
			return this.buildingNum1 + " " + this.buildingNum2 + " " + this.time;
		}
	}
}
