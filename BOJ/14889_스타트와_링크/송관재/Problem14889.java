import java.util.Scanner;

public class Problem14889 {
	static int[] sel;
	static int[][] arr;
	static int N;
	static int diff = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		arr = new int[N][N];
		sel = new int[N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				arr[i][j] = s.nextInt();
			}
		}
		dfs(0, 0);
		System.out.println(diff);
	}

	public static void dfs(int depth, int select) {
		if(depth == N - 1) {
			if(select == N/2) {
				op();
				return;
			} else {
				return;
			}
		} else if (select == N/2) {
			op();
			return;
		}
		sel[depth] = 1;
		dfs(depth + 1, select + 1);
		sel[depth] = 0;
		dfs(depth + 1, select);
	}
	
	public static void op() {
		int start = 0;
		int link = 0;
		//계산
		for(int i=0; i<N; i++) {
			int team = sel[i];
			for(int j=i+1; j<N; j++) {
				if(team != sel[j]) {
					continue;
				}
				if(team == 1) {
					start += arr[i][j] + arr[j][i];
				} else {
					link += arr[i][j] + arr[j][i];
				}
			}
		}
		int temp;
		if(start > link) {
			temp = start - link;
		} else {
			temp = link - start;
		}
		if(diff > temp) {
			diff = temp;
		}
		
	}
}
