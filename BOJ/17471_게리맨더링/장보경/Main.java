import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static boolean[][] arr;
    static int[] pNum;
    static ArrayList<Integer> area1, area2;
    static boolean[] cVisited, visited;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        pNum = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++){
            pNum[i] = Integer.parseInt(st.nextToken());
        }

        arr = new boolean[N][N];
        area1 = new ArrayList<>();
        area2 = new ArrayList<>();
        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            int tmp = Integer.parseInt(st.nextToken());
            for (int j=0; j<tmp; j++){
                int idx = Integer.parseInt(st.nextToken())-1;
                arr[i][idx] = true;  // 그래프가 연결되어있다면 true
            }
        }

        ans = Integer.MAX_VALUE;
        cVisited = new boolean[N];
        comb(0);

        // 두 선거구로 나눌 수 없는 경우
        if (ans == Integer.MAX_VALUE){
            ans = -1;
        }

        System.out.println(ans);
    }

    static boolean b1, b2;
    // 두 개의 선거구로 나누기 위한 모든 경우의 수를 탐색 (조합)
    public static void comb(int start){
        if (start == N-1){
            return;
        }

        for (int i=start; i<N; i++){
            if (!cVisited[i]){
                cVisited[i] = true;
                makeList();

                visited = new boolean[N];
                b1 = false; b2 = false;
                dfs(area1.get(0), area1, new ArrayList<>(), 1);
                dfs(area2.get(0), area2, new ArrayList<>(), 2);

                // 1번 구역과 2번 구역이 다 연결되어 있다면 인구차이의 최솟값 구함
                if (b1 && b2){
                    int diff = Math.abs(getPDiff(area1) - getPDiff(area2));
                    ans = Math.min(ans, diff);
                }

                comb(i+1);

                cVisited[i] = false;
            }
        }
    }

    // 방문 배열에 따라 두 개의 선거구로 나누기 위한 메소드
    public static void makeList(){
        area1 = new ArrayList<>();
        area2 = new ArrayList<>();

        for (int i=0; i<N; i++){
            if (!cVisited[i]){
                area2.add(i);
            } else{
                area1.add(i);
            }
        }
    }

    // 해당 선거구 내의 구역들이 연결되어 있는지 확인 하기 위한 dfs메소드
    public static void dfs(int nx, ArrayList<Integer> al, ArrayList<Integer> node, int area){
        // 해당 선거구에 없는 구역일 경우 굳이 더 탐색하지 않아도 되기 때문에 return
        if (!al.contains(nx)){
            return;
        }

        visited[nx] = true;
        node.add(nx);

        // node.size() == al.size()라면 해당 선거구의 모든 구역들이 연결되었다는 것을 의미
        if (node.size() == al.size()){
            // 1구역이 true인지 2구역이 true인지 체크
            if (area == 1){
                b1 = true;
            } else{
                b2 = true;
            }
        }

        for (int j=0; j<N; j++){
            // 새로 탐색하는 구역이 방문되지 않았고 현재 구역과 연결되어 있다면 dfs
            if (!visited[j] && arr[nx][j]){
                dfs(j, al, node, area);
            }
        }
    }

    // 구역의 인구 합 구하는 메소드
    public static int getPDiff(ArrayList<Integer> al){
        int sum = 0;

        for (int n: al){
            sum += pNum[n];
        }

        return sum;
    }
}
