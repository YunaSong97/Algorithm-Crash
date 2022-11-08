import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    static boolean[] visited;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        for (int i=0; i<N; i++){
        	StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N];
        ans = Integer.MAX_VALUE;
        
      // 두 팀의 인원수는 같지않아도 되지만 한 팀의 인원이 최소 한명 이상이어야 한다.
        for(int i=1; i<N; i++) {
        	comb(0, i, 0);
        }

        System.out.println(ans);
    }

    static void comb(int cnt, int end, int idx){
      // 만약 인원수만큼 다 돌았다면 능력치 최소 구하기
    	if(cnt == end) {
    		int now = getSub();
    		ans = Math.min(ans, now);
    		
    		return;
    	}
    	
      // 조합으로 팀 구성
        for(int i=idx; i<N; i++) {
        	if(!visited[i]) {
        		visited[i] = true;
        		comb(cnt+1, end, i+1);
        		visited[i] = false;
        	}
        }
    }
    
    static int getSub() {
    	int vSum = 0;
    	int nVSum = 0;
    	
    	for(int i=0; i<N-1; i++) {
    		for(int j=i+1; j<N; j++) {
    			if(visited[i] && visited[j]) { // 둘 다 방문한 위치의 배열값이라면 vSum에 더하기
    				vSum += (arr[i][j] + arr[j][i]);
    			} else if(!visited[i] && !visited[j]) { // 둘 다 방문하지 않은 위치의 배열값이라면 nVSum에 더하기
    				nVSum += (arr[i][j] + arr[j][i]);
    			}
    		}
    	}
    	int sub = Math.abs(vSum - nVSum);
    	
    	return sub;
    }
}
