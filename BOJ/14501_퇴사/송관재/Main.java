import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int day = Integer.parseInt(st.nextToken());
		int[] T = new int[day];
		int[] P = new int[day];
		int[] R = new int[day+1];
		for(int i=0; i<day; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		for(int j=day-1; j>=0; j--) {
			if(j + T[j] <= day) {
				if(R[j + T[j]] + P[j] > R[j+1]) {
					R[j] = P[j] + R[j + T[j]];
				} else {
					R[j] = R[j+1];
				}
			} else {
				R[j] = R[j+1];
			}
		}
		System.out.println(R[0]);
	}
}
