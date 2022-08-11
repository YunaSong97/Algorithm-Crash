import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static ArrayList<Integer> arr = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	static int[] permutation;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		permutation = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(arr);
		dfs(0, 0);
		System.out.println(sb);
	}
	static void dfs(int depth, int next) {
		if (depth == M) {
			for (int i = 0; i < M; i++) {
				sb.append(permutation[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int cand = next; cand < N; cand++) {
			permutation[depth] = arr.get(cand);
			dfs(depth + 1, cand + 1);
			permutation[depth] = 0;
		}
	}
}
