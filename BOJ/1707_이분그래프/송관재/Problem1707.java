import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem1707 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int K = s.nextInt();
		List<List<Integer>> list = new ArrayList<>();
		for(int j=0; j<=20000; j++) {
			list.add(new ArrayList<>());
		}
		for(int i=0; i<K; i++) {
			int V = s.nextInt();
			int E = s.nextInt();
			for(int j=0; j<E; j++) {
				int u = s.nextInt();
				int v = s.nextInt();
				list.get(u).add(v);
				list.get(v).add(u);
			}
			boolean flag = true;
			Queue<Integer> q = new LinkedList<>();
			int[] visit = new int[V+1];
			for(int j = 1; j <= V; j++) {
				if(visit[j] == 0) {
					q.add(j);
					while(!q.isEmpty()) {
						int v = q.poll();
						List<Integer> adjList = list.get(v);
						int child = 0;
						for(int u : adjList) {
							if(visit[u] != 0) {
								child = visit[u];
							}
						}
						if(visit[v] == 0) {
							if(child == 1) {
								visit[v] = 2;
							} else if (child == 2) {
								visit[v] = 1;
							} else {
								visit[v] = 1;
							}
						}
						int n = 1;
						if(visit[v] == 1) {
							n = 2;
						}
						for(int u : adjList) {
							if(visit[u] == visit[v]) {
								flag = false;
								break;
							} else {
								if(visit[u] == 0) {
									visit[u] = n;
									q.add(u);
								}
							}
						}
						if(!flag) {
							break;
						}
						if(visit[v] != 0) {
							continue;
						}
					}
				}
			}
			if(flag) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
			for(int j = 1; j <= V; j++) {
				list.get(j).clear();
			}
		}
	}
}
