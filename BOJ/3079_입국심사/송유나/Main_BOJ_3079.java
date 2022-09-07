package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		ArrayList<Integer> timeList = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			timeList.add(Integer.parseInt(br.readLine()));
		}

		Collections.sort(timeList);
		long l = timeList.get(0);
		long h = l * m;
		long ans = 0;

		while (l <= h) {
			long mid = (l + h) / 2;
			long sum = 0;

			for (int i = 0; i < n; i++) {
				sum += (mid / timeList.get(i));
				
				if (sum >= m) {
					ans = mid;
					h = mid - 1;
					break;
				}
			}
			if (sum < m) {
				l = mid + 1;
			}
		}

		System.out.println(ans);
	}
}
