import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] population;
	static int[] selected;
	static int[] visit;
	static ArrayList<ArrayList<Integer>> adjList;
	static int answer = -1;
	static int tempAnswer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		population = new int[N+1];
		selected = new int[N+1];
		visit = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		adjList = new ArrayList<>();
		adjList.add(new ArrayList<>());
		for(int i=1; i<=N; i++) {
			adjList.add(new ArrayList<>());
		}
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			for(int j=0; j<k; j++) {
				int u = Integer.parseInt(st.nextToken());
				adjList.get(i).add(u);
				adjList.get(u).add(i);
			}
		}
		solve(1);
		if(tempAnswer != Integer.MAX_VALUE) {
			answer = tempAnswer;
		}
		System.out.println(answer);
	}
	
	public static void solve(int depth) {
		if(depth == N + 1) {
			//initiate visit array
			for(int i=1; i<=N; i++) {
				visit[i] = 0;
			}
			//check and if false return
			for(int i=1; i<=N; i++) {
				if(selected[i] == 0) {
					dfs(i, selected[i]);
					break;
				}
			}
			for(int i=1; i<=N; i++) {
				if(selected[i] == 1) {
					dfs(i, selected[i]);
					break;
				}
			}
			boolean flag = true;
			for(int i=1; i<=N; i++) {
				if(visit[i] == 0) {
					flag = false;
					break;
				}
			}
			if(!flag) {
				return;
			}
			int sum1 = 0;
			int sum2 = 0;
			for(int i=1; i<=N; i++) {
				if(selected[i] == 0) {
					sum1 += population[i];
				} else {
					sum2 += population[i];
				}
			}
			tempAnswer = Math.min(tempAnswer, Math.abs(sum1 - sum2));
			return;
		}
		selected[depth] = 1;
		solve(depth+1);
		selected[depth] = 0;
		solve(depth+1);
	}
	
	public static void dfs(int i, int select) {
		if(visit[i] == select + 1) {
			return;
		}
		visit[i] = select + 1;
		for(int j : adjList.get(i)) {
			if(selected[j] == select) {
				dfs(j, select);
			}
		}
	}
}
