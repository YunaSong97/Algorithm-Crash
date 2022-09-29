import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int population;
	int idx;
	ArrayList<Integer> nodes = new ArrayList<>();

	public Node(int idx, int population) {
		this.idx = idx;
		this.population = population;
	}

	public Node(int idx) {
		this.idx = idx;
	}
}

public class Main {
	static int n;
	static Node[] nodes;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		nodes = new Node[n];
		answer = Integer.MAX_VALUE;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nodes[i] = new Node(i + 1, Integer.parseInt(st.nextToken()));
		}

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int len = Integer.parseInt(st.nextToken());

			for (int j = 0; j < len; j++) {
				nodes[i].nodes.add(Integer.parseInt(st.nextToken()));
			}
		}

		for (int i = 1; i <= n / 2; i++) {
			boolean[] res = new boolean[n];
			comb(res, 0, 0, i);
		}

		if(answer==Integer.MAX_VALUE) {
			answer = -1;
		}
		
		System.out.println(answer);
	}

	static void comb(boolean[] res, int start, int depth, int cnt) {
		if (depth == cnt) {

			if (countArea(res)) {
				int a = 0;
				int b = 0;

				for (int i = 0; i < n; i++) {
					if (res[i]) {
						a += nodes[i].population;
					} else {
						b += nodes[i].population;
					}
				}

				answer = Math.min(Math.abs(a - b), answer);
			}

			return;
		}
		for (int i = start; i < n; i++) {

			res[i] = true;
			comb(res, i + 1, depth + 1, cnt);
			res[i] = false;
		}
	}

	static boolean countArea(boolean[] res) {
		boolean[] visit = new boolean[n + 1];
		int cnt = 0;

		for (int i = 1; i <= n; i++) {
			if (!visit[i]) {
				visit[i] = true;
				cnt++;
                if(cnt>2){
                    return false;
                }
                bfs(res, visit, nodes[i - 1]);
			}
		}
        if(cnt==2){
            return true;
        }
        return false;
	}

	static void bfs(boolean[] res, boolean[] visit, Node start) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(start);

		while (!queue.isEmpty()) {

			Node now = queue.poll();
			for (int n : now.nodes) {
				if (!visit[n] && res[n - 1] == res[now.idx - 1]) {
					queue.add(nodes[n - 1]);
					visit[n] = true;
				}
			}
		}
	}
}
