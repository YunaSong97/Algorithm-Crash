import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N, areaCnt;
	static int[] dy = new int[] {0, 1, 0, -1};
	static int[] dx = new int[] {1, 0, -1, 0};
	static char[][] map;
	static int[][] area;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		area = new int[N][N];
		
		for (int y = 0; y < N; y++) {
			String line = br.readLine();
			for (int x = 0; x < N; x++) {
				map[y][x] = line.charAt(x);
			}
		}
		
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				// 아직 방문되지 않은 영역일 경우 영역 구분
				if (area[y][x] == 0) {
					areaCnt++;
					RGB(y, x);
				}
			}
		}
		
		System.out.print(areaCnt + " ");
		areaCnt = 0;
		area = new int[N][N];
		
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				// 아직 방문되지 않은 영역일 경우 영역 구분
				if (area[y][x] == 0) {
					areaCnt++;
					RRB(y, x);
				}
			}
		}
		
		System.out.println(areaCnt);
		
	}
	
	static void RGB(int y, int x) {
		if (area[y][x] != 0) {
			return;
		}
		
		area[y][x] = areaCnt;
		
		for (int i = 0; i < 4; i++) {
			int nY = y + dy[i];
			int nX = x + dx[i];
			
			if (nY < 0 || nY >= N || nX < 0 || nX >= N) continue;
			// 다음 방문 예정인 칸과 현재 칸의 색이 다르면
			if (map[nY][nX] != map[y][x]) continue;
			RGB(nY, nX);
		}
	}
	
	static void RRB(int y, int x) {
		if (area[y][x] != 0) {
			return;
		}
		
		area[y][x] = areaCnt;
		
		for (int i = 0; i < 4; i++) {
			int nY = y + dy[i];
			int nX = x + dx[i];
			
			if (nY < 0 || nY >= N || nX < 0 || nX >= N) continue;
			// R <--> G 같은색
			if ((map[nY][nX] == 'R' && map[y][x] == 'R') ||
				(map[nY][nX] == 'G' && map[y][x] == 'R'))
				RRB(nY, nX);
			if ((map[nY][nX] == 'R' && map[y][x] == 'G') ||
				(map[nY][nX] == 'G' && map[y][x] == 'G'))
				RRB(nY, nX);
			if (map[nY][nX] == 'B' && map[y][x] == 'B')
				RRB(nY, nX);
		}
	}
}
