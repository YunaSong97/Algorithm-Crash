

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1707 {
    static int V,E;
    static List<List<Integer>> graph;
    static int[] colors;
    static boolean result;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        int K  = Integer.parseInt(br.readLine());
        int idx=0;

        while(idx<K){
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            colors = new int[V+1];
            result = true;
            for(int i=0;i<V+1;i++) {
                graph.add(new ArrayList<Integer>());
                colors[i] = 0;
            }

            for(int i=0;i<E;i++){
                st = new StringTokenizer(br.readLine());
                int num1 = Integer.parseInt(st.nextToken());
                int num2 = Integer.parseInt(st.nextToken());
                graph.get(num1).add(num2);
                graph.get(num2).add(num1);
            }

            for(int i=1;i<V+1;i++){
                if(colors[i]==0) {
                    dfs(i, 1);
                }
            }

            System.out.println(result?"YES":"NO");


            idx++;
        }


    }
    static void dfs(int v,int color){
        colors[v] = color;

        for(int i=0;i<graph.get(v).size();i++){
            if(colors[graph.get(v).get(i)]==color){
                result = false;
                return ;
            }
            if(colors[graph.get(v).get(i)]==0){
                dfs(graph.get(v).get(i),-color);
            }
        }

    }
}
