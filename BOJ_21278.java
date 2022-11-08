import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_21278 {
    static int N, M;
    static boolean[][] v;
    static boolean[] vn;
    static boolean[] chicken;
    static int[] chiNum;
    static ArrayList<ArrayList<Integer>> cities;
    static int res;
    static int min, max;
    static int c = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        res = Integer.MAX_VALUE;
        min = Integer.MAX_VALUE;
        max = Integer.MAX_VALUE;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cities = new ArrayList<>();
        chicken = new boolean[2];
        chiNum = new int[2];

        for (int i = 0; i <= N; i++) {
            cities.add(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            cities.get(x).add(y);
            cities.get(y).add(x);

        }
        combi(0, 1);
        System.out.println(min + " " + max + " " + res);
    }

    static void combi(int depth, int start) {
        if (depth == 2) {
            bfs();
            return;
        }
        for (int i = start; i <= N; i++) {
            chiNum[depth] = i;
            combi(depth + 1, i + 1);
        }

    }

    static void bfs() {
        int total = 0;
        int count=0;
        Queue<Node> q = new ArrayDeque<>();
        v = new boolean[N+1][N+1];
        vn = new boolean[N+1];
        q.add(new Node(chiNum[0],0));
        q.add(new Node(chiNum[1],0));
        vn[chiNum[0]] = true;
        vn[chiNum[1]] = true;
        while(!q.isEmpty()){
            Node node = q.poll();
            int now = node.num;
            count+=node.cnt;
            for(int i=0;i<cities.get(now).size();i++){
                if(!vn[cities.get(now).get(i)]){
                    q.add(new Node(cities.get(now).get(i),node.cnt+1));
                    vn[cities.get(now).get(i)] = true;
                }
                if(count*2>res){
                    return ;
                }
            }

        }
        total = count*2;
        if(total==res){
            if(chiNum[0]<min){ //새로 들어온 값이 더 작음
                min = chiNum[0];
                max = chiNum[1];
            }else if(chiNum[0]==min){ // 둘이 같은 경우 max 값 비교
                if(chiNum[1]<max){
                    min = chiNum[0];
                    max = chiNum[1];
                }
            }
        }else{
            min = chiNum[0];
            max=  chiNum[1];
            res = total;
        }
    }

}
class Node{
    int num;
    int cnt;
    public Node(int num,int cnt){
        this.num = num;
        this.cnt = cnt;
    }
}
