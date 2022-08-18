import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] map;
	static ArrayList<int[]> pos = new ArrayList<>();
	static int[] comb;
	static int ans = Integer.MAX_VALUE;
	static boolean[] visited;
	static int combSize = 0;
	static int call = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		comb = new int[M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) pos.add(new int[] {i, j});
			}
		}
		combSize = pos.size();
		visited = new boolean[combSize];
		findAnswer(0);
		System.out.println(ans);
	}

	static void findAnswer(int k) {
		if (k == M) {
			int min = 0;
			int minSum = 0;
			
			// comb에 저장되어있는 치킨집 좌표 조합을 기반으로 최소 치킨거리 구함
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					if (map[y][x] == 1) {
						min = Integer.MAX_VALUE;
						for (int i = 0; i < M; i++) {
							int[] yx = pos.get(comb[i]);
							min = Math.min(min, Math.abs(y - yx[0]) + Math.abs(x - yx[1]));
						}
						minSum += min;
					}
				}
			}
			ans = Math.min(ans, minSum);
			return;
		}
		
		for (int cand = 0; cand < combSize; cand++) {
			if (k == 0) {
				comb[k] = cand;
				findAnswer(k + 1);
			} else {
				if (comb[k-1] < cand) {
					comb[k] = cand;
					findAnswer(k + 1);
				}
			}
		}
	}
}
