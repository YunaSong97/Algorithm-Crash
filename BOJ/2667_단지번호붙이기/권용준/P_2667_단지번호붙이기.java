import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	
	static int N, areaCnt;
	static int[][] map, area;
	static int dy[] = new int[] {0, 1, 0, -1};
	static int dx[] = new int[] {1, 0, -1, 0};
	static ArrayList<Integer> ansList = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		area = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (map[y][x] == 1 && area[y][x] == 0) {
					areaCnt++;
					ansList.add(dfs(y, x) + 1);
				}
			}
		}
		
		Collections.sort(ansList);
		System.out.println(ansList.size());
		for (Integer i : ansList) {
			System.out.println(i);
		}
	}
	static int dfs(int y, int x) {
		int cnt = 0;
		if (area[y][x] != 0) return 0;
		
		area[y][x] = areaCnt;
		
		for (int i = 0; i < 4; i++) {
			int nY = y + dy[i];
			int nX = x + dx[i];
			
			if (nY < 0 || nY >= N || nX < 0 || nX >= N) continue;
			// 다음 방문 예정인 칸과 현재 칸이 다르면
			if (map[nY][nX] != map[y][x]) continue;
			if (area[nY][nX] != 0) continue;
			cnt += dfs(nY, nX);
			cnt++;
		}
		
		return cnt;
	}
}
