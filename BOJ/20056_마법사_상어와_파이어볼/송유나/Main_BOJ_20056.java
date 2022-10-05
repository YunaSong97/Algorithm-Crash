import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Fire {
	int r, c;
	int mass, dir, speed;

	public Fire(int r, int c, int mass, int speed, int dir) {
		this.r = r;
		this.c = c;
		this.mass = mass;
		this.dir = dir;
		this.speed = speed;
	}
}

public class Main_BOJ_20056 {
	static int n, m, k;
	static List<Fire>[] fires;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		fires = new ArrayList[n * n];

		for (int i = 0; i < n * n; i++) {
			fires[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			Fire f = new Fire(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
            
			fires[(f.r - 1) * n + f.c - 1].add(f);
		}

        //k번 만큼 움직이고 합치기
		for (int i = 0; i < k; i++) {
			moveFire();
			mergeFire();
		}

		int answer = 0;
		for (int i = 0; i < n * n; i++) {
			for (Fire f : fires[i]) {
				answer += f.mass;
			}
		}

		System.out.println(answer);
	}

    //1. 1초동안 움직이기
	static void moveFire() {
		int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
		int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

		List<Fire> temp = new ArrayList<>();
		for (int i = 0; i < n * n; i++) {
			for (Fire f : fires[i]) {
				int nr = f.r + dr[f.dir] * f.speed;
				int nc = f.c + dc[f.dir] * f.speed;

				f.r = isOut(nr);
				f.c = isOut(nc);

                //옮겨진 파이어볼을 temp에 저장
				temp.add(f);
			}

			fires[i].clear();
		}

        //fires에 옮겨진 파이어볼들 저장
		for (Fire f : temp) {
			fires[n * (f.r - 1) + f.c - 1].add(f);
		}
	}

    //2. 2개 이상 있으면
	static void mergeFire() {
		for (int i = 0; i < n * n; i++) {
			if (fires[i].size() >= 2) {
				int mSum = 0;
				int sSum = 0;

				for (Fire f : fires[i]) {
					mSum += f.mass;
					sSum += f.speed;
				}

				int nMass = mSum / 5;
				if (nMass == 0) { //질량이 0
					fires[i].clear();
					continue;
				}

				int nSpeed = sSum / fires[i].size();
                
                //모든 원소가 짝수/홀수 인지 확인
				if (fires[i].stream().allMatch(f -> f.dir % 2 == 0) || fires[i].stream().allMatch(f -> f.dir % 2 == 1)) {
					fires[i].clear();

					for (int j = 0; j < 4; j++) {
						fires[i].add(new Fire(i / n + 1, i % n + 1, nMass, nSpeed, j * 2));
					}
				} else {
					fires[i].clear();
					for (int j = 0; j < 4; j++) {
						fires[i].add(new Fire(i / n + 1, i % n + 1, nMass, nSpeed, j * 2 + 1));
					}
				}
			}
		}
	}

    //범위를 벗어나면 n씩 더해주거나 빼준다.
	static int isOut(int x) {
		while (x <= 0) {
			x += n;
		}
		while (x >= n + 1) {
			x -= n;
		}

		return x;
	}
}
