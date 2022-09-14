import java.util.ArrayList;

class Solution {
    static int answer = 0;
    static int[][][] visit;
    static int[] tree;
    static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
    public int solution(int[] info, int[][] edges) {
    	tree = info.clone();
        for(int i=0; i<info.length; i++) {
        	adjList.add(new ArrayList<>());
        }
        for(int[] edge : edges) {
        	adjList.get(edge[0]).add(edge[1]);
        	adjList.get(edge[1]).add(edge[0]);
        }
        visit = new int[info.length][info.length + 1][info.length + 1];
        dfs(0, 0, 0);
        return answer;
    }
    
    public static void dfs(int node, int w, int s){
        if(tree[node] == 0){
            s++;
        } else if(tree[node] == 1) {
        	w++;
        }
        
        if(w >= s){
            return;
        }
        
        if(s > answer) {
        	answer = s;
        }
        
        for(int n : adjList.get(node)) {
        	if(visit[n][w][s] == 0) {
        		int temp = tree[node];
        		tree[node] = -1;
        		visit[node][w][s] = 1;
        		dfs(n, w, s);
        		visit[node][w][s] = 0;
        		tree[node] = temp;
        	}
        }
    }
}