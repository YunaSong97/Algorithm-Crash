import java.util.Scanner;

public class Main {

	static int N, ans;
	static int[] info;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		info = new int[N + 1];
		dfs(1);
		System.out.println(ans);
	}

	static void dfs(int depth) {
		if (depth == N + 1) {
			ans++;
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			// 조건문으로 퀸을 놓지 못하는 경우를 걸러낸 후 dfs
			// 이전에 놓은 queen의 row에는 퀸을 놓지못한다.
			// => 이전에 놓은 queen의 row정보는 info[]안에 존재.
			if(checkRow(depth, i)) continue;
			if (checkCross(depth, i)) continue;
			// 이전에 놓은 queen의 대각선 영역에는 퀸을 놓지 못한다.
			info[depth] = i;
			dfs(depth + 1);
		}
	}
	static boolean checkRow(int depth, int row) {
		for (int i = 1; i < depth; i++) {
			if (info[i] == row) return true;
		}
		
		return false;
	}
	
	static boolean checkCross(int depth, int row) {
		for (int col = 1; col < depth; col++) {
			int qRow = info[col];
			
			// 이전 퀸들의 현재 위치정보
			int curY = qRow;
			int curX = col;
			while(true) {
				int nY = curY + 1;
				int nX = curX + 1;
				
				if (nY <= 0 || nY > N || nX <= 0 || nX > N) break;
				if (depth == nX && row == nY) return true;
				
				curY++;
				curX++;
			}
			
			// 이전 퀸들의 현재 위치정보
			curY = qRow;
			curX = col;
			while(true) {
				int nY = curY - 1;
				int nX = curX + 1;
				
				if (nY <= 0 || nY > N || nX <= 0 || nX > N) break;
				
				if (depth == nX && row == nY) return true;
				
				curY--;
				curX++;
			}
		}
		
		return false;
	}
}
