import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static ArrayList<ArrayList<Integer>> adjList;
    static int[] combVisit;
    static int[] dist;
    static int[] chicken;
    static int[] combList;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        chicken = new int[2];
        combList = new int[2];
        combVisit = new int[N+1];
        adjList = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            adjList.add(new ArrayList<>());
        }
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            adjList.get(v).add(e);
            adjList.get(e).add(v);
        }
        comb(0, 1);
        System.out.println(chicken[0] + " " + chicken[1] + " " + answer);
    }
    
    public static void comb(int depth, int start) {
        if(depth == 2) {
            bfs();
            //calc
            int sum = 0;
            for(int i=1; i<=N; i++) {
                sum += dist[i];
            }
            sum *= 2;
            if(answer > sum) {
                chicken = combList.clone();
                answer = sum;
            }
            return;
        }
        for(int i=start; i<=N; i++) {
            if(combVisit[i] == 0) {
                combList[depth] = i;
                combVisit[i] = 1;
                comb(depth+1, i);
                combVisit[i] = 0;
            }
        }
    }

    public static void bfs() {
        dist = new int[N+1];
        for(int i=1; i<=N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[combList[0]] = 0;
        dist[combList[1]] = 0;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[] {combList[0], 0});
        q.add(new int[] {combList[1], 0});
        while(!q.isEmpty()) {
            int[] node = q.poll();
            for(int v : adjList.get(node[0])) {
                if(dist[v] > node[1] + 1) {
                    dist[v] = node[1] + 1;
                    int[] newNode = new int[2];
                    newNode[0] = v;
                    newNode[1] = node[1] + 1;
                    q.add(newNode);
                }
            }
        }
    }
}
