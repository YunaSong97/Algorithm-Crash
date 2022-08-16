import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class BOJ_2252{
    public static void main(String[] args) throws IOException {
        Queue<Integer> q = new LinkedList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        List<List<Integer>> list = new ArrayList<>();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] inDegree = new int[N+1];

        for(int i=0;i<N+1;i++){
            list.add(new ArrayList<Integer>());
        }
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            list.get(num1).add(num2);
            inDegree[num2]++;
        }

        for(int i=1;i<inDegree.length;i++){
            if(inDegree[i]==0)
                q.add(i);
        }
        while(!q.isEmpty()){

            int now = q.poll();
            sb.append(now+" ");
            for(int i=0;i<list.get(now).size();i++){
                inDegree[list.get(now).get(i)]--;
                if(inDegree[list.get(now).get(i)]==0)
                    q.add(list.get(now).get(i));
            }
        }

        System.out.println(sb);

    }


}