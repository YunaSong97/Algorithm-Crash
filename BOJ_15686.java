import jdk.swing.interop.SwingInterOpUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_15686 {
    static int N;
    static int result = Integer.MAX_VALUE;
    static List<Point> home = new ArrayList<>();
    static List<Point> dak = new ArrayList<>();
    static boolean[] visited;
    static int M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                int in = Integer.parseInt(st.nextToken());
                if(in == 1){
                    home.add(new Point(i,j));
                }
                else if(in==2){
                    dak.add(new Point(i,j));
                }
            }
        }
        visited = new boolean[dak.size()];
        dfs(0,0);
        System.out.println(result);


    }
    static void dfs(int index , int depth){
        if(index==dak.size()) {
            if (depth == M) {
                int total = 0;
                for (int i = 0; i < home.size(); i++) {
                    int dis = Integer.MAX_VALUE;
                    for (int j = 0; j < dak.size(); j++) {
                        if (visited[j]) {
                            int res = Math.abs(home.get(i).x - dak.get(j).x) + Math.abs(home.get(i).y - dak.get(j).y);
                            dis = Math.min(dis, res);
                        }
                    }
                    total += dis;
                }
                result = Math.min(result, total);
            }
            return;
        }

        visited[index] = true;
        dfs(index+1,depth+1);
        visited[index] = false;
        dfs(index+1,depth);
    }
}
class Point{
    int x;
    int y;

    public Point(int x,int y){
        this.x = x;
        this.y = y;
    }
}
