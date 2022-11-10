import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static boolean[] visited;
    static ArrayList<int[]> al;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
        	Arrays.fill(arr[i], 999999);  //Integer.MAX_VALUE로 하면 floyed()수행했을 때 값이 넘침
        	arr[i][i] = 0;
        }
        
        for (int i=1; i<=M; i++){
        	st = new StringTokenizer(br.readLine());
        	
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	
        	arr[x][y] = 1;
        	arr[y][x] = 1;
        }

        al = new ArrayList<>();
        visited = new boolean[N+1];
        
        floyed();

        comb(0, 0, new int[2], 1);

        al.sort(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0]){
					if (o1[1] == o1[2]){
						return o1[2]-o2[2];  // 큰 번호가 더 작은 것을 리턴
					}
					return o1[1]-o2[1];  // 작은 번호가 더 작은 것 리턴
				}
				return o1[0]-o2[0];  // 최단시간 답 리턴
			}
		});

        System.out.println(al.get(0)[1] + " " + al.get(0)[2] + " " + al.get(0)[0]);
    }

    static void comb(int cnt, int idx, int[] chicken, int start){
    	if(cnt == 2) {
    		int sum = getSum(chicken);
    		
    		// ArrayList에 {최단시간 총 합, 치킨집1, 치킨집2}배열을 넣어줌
			int[] tmpArr = {sum*2, Math.min(chicken[0], chicken[1]), Math.max(chicken[0], chicken[1])};
			al.add(tmpArr);
    		
    		return;
    	}
    	
    	for(int i=start; i<=N; i++) {
    		if(!visited[i]) {
    			visited[i] = true;
    			chicken[idx] = i;
    			comb(cnt+1, idx+1, chicken, i+1);
    			visited[i] = false;
    		}
    	}
    }

    // 모든 건물에서 치킨 집까지 왕복하는 시간의 합
    static int getSum(int[] chicken){
    	int c1 = chicken[0];
    	int c2 = chicken[1];
    	
    	int sum = 0;
    	for(int i=1; i<=N; i++) {
    		sum += Math.min(arr[i][c1], arr[i][c2]);
    	}
    	
    	return sum;
	}
    
    // 모든 건물에 대한 최소거리 값
    static void floyed() {
    	for(int k=1; k<=N; k++) {
    		for(int i=1; i<=N; i++) {
    			for(int j=1; j<=N; j++) {
    				if(arr[i][j] > arr[i][k]+arr[k][j]) {
    					arr[i][j] = arr[i][k]+arr[k][j];
    				}
    			}
    		}
    	}
    }
}
