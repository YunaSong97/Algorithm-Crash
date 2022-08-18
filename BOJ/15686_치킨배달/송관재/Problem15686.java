import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem15686 {
	static int N;
	static int M;
	static int[][] arr;
	static int[] sel;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int count = 0;
	static int result = Integer.MAX_VALUE;
	static List<int[]> person;
	static List<int[]> chicken;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		M = s.nextInt();
		arr = new int[N][N];
		person = new ArrayList<int[]>();
		chicken = new ArrayList<int[]>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = s.nextInt();
				if(arr[i][j] == 2) {
					count++;
					chicken.add(new int[]{i, j});
				} else if (arr[i][j] == 1) {
					person.add(new int[] {i, j});
				}
			}
		}
		sel = new int[count];
		dfs(0, 0);
		System.out.println(result);//결과 출력
	}

	public static void dfs(int depth, int select) {
		if(depth == count) {
			if(select == count - M) {
				op();
				return;
			} else {
				return;
			}
		} else if (select == count - M) {
			op();
			return;
		}
		sel[depth] = 1;
		dfs(depth + 1, select + 1);
		sel[depth] = 0;
		dfs(depth + 1, select);
	}
	
	public static void op() {
		int sum = 0;
		for(int i = 0; i < person.size(); i++) {
			int t1 = Integer.MAX_VALUE;
			for(int j = 0; j < chicken.size(); j++) {
				if(sel[j] == 1) {
					continue;
				} else {
					int t2 = Math.abs(person.get(i)[0] - chicken.get(j)[0]) + Math.abs(person.get(i)[1] - chicken.get(j)[1]);
					if(t2 < t1) {
						t1 = t2;
					}
				}
			}
			sum += t1;
		}
		if(sum < result) {
			result = sum;
		}
	}
}
