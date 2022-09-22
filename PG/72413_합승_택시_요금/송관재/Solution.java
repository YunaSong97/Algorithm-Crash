import java.util.ArrayList;

class Solution {
    static ArrayList<ArrayList<int[]>> adjList;
    static long[][] dist;
    static int[] visit;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        //dijkstra로 dist배열 2차원으로 구현 후 dist[s][a] + dist[s][b] , dist[s][i] + dist[i][a] + dist[i][b] 비교
        adjList = new ArrayList<>();
        for(int i=0; i<=n; i++) {
        	adjList.add(new ArrayList<>());
        }
        for(int[] fare : fares){
            int v = fare[0];
            int u = fare[1];
            int weight = fare[2];
            int[] temp = new int[2];
            temp[1] = weight;
            temp[0] = v;
            adjList.get(u).add(temp.clone());
            temp[0] = u;
            adjList.get(v).add(temp.clone());
        }
        dist = new long[n+1][n+1];
        visit = new int[n+1];
        for(int i=1; i<=n; i++) {
        	for(int j=1; j<=n; j++) {
        		dist[i][j] = Integer.MAX_VALUE;
        	}
        	dist[i][i] = 0;
        	visit[i] = i % 2;
        	for(int[] node : adjList.get(i)) {
        		int v = node[0];
        		int weight = node[1];
        		dist[i][v] = weight;
        	}
        	int sum = 1;
        	while(sum != n) {
        		long min = Long.MAX_VALUE;
        		int u = 0;
        		for(int j=1; j<=n; j++) {
        			if(dist[i][j] < min && visit[j] != i % 2) {
        				min = dist[i][j];
        				u = j;
        			}
        		}
        		visit[u] = i % 2;
        		for(int[] node : adjList.get(u)) {
        			int v = node[0];
        			int weight = node[1];
        			dist[i][v] = Math.min(dist[i][v], dist[i][u] + weight);
        		}
        		sum++;
        	}
        }
        long answer = dist[s][a] + dist[s][b];
        for(int i=1; i<=n; i++) {
        	answer = Math.min(answer, dist[s][i] + dist[i][a] + dist[i][b]);
        }
        return (int) answer;
    }
}