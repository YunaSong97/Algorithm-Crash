import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
        int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        ArrayList<FireBall> list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new FireBall(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }
        for (int i = 0; i < K; i++) {
            for (int t = 0; t < list.size(); t++) {
                FireBall f = list.get(t);
                f.r += (f.s * dr[f.d]) % N;
                f.c += (f.s * dc[f.d]) % N;
                while (f.r < 1) {
                    f.r += N;
                }
                while (f.r > N) {
                    f.r -= N;
                }
                while (f.c < 1) {
                    f.c += N;
                }
                while (f.c > N) {
                    f.c -= N;
                }
                list.set(t, f);
            }
            int[][] visit = new int[N + 1][N + 1];
            for (FireBall f : list) {
                visit[f.r][f.c]++;
            }
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    if (visit[r][c] > 1) {
                        int sumM = 0;
                        int sumS = 0;
                        int dCount1 = 0;
                        int dCount2 = 0;
                        ArrayList<FireBall> tempList = new ArrayList<>();
                        for (FireBall f : list) {
                            if (f.r == r && f.c == c) {
                                sumM += f.m;
                                sumS += f.s;
                                if (f.d % 2 == 0) {
                                    dCount2++;
                                } else {
                                    dCount1++;
                                }
                                tempList.add(f);
                            }
                        }
                        sumM /= 5;
                        sumS /= visit[r][c];
                        if (sumM > 0) {
                            if (dCount1 == 0 || dCount2 == 0) {
                                list.add(new FireBall(r, c, sumM, sumS, 0));
                                list.add(new FireBall(r, c, sumM, sumS, 2));
                                list.add(new FireBall(r, c, sumM, sumS, 4));
                                list.add(new FireBall(r, c, sumM, sumS, 6));
                            } else {
                                list.add(new FireBall(r, c, sumM, sumS, 1));
                                list.add(new FireBall(r, c, sumM, sumS, 3));
                                list.add(new FireBall(r, c, sumM, sumS, 5));
                                list.add(new FireBall(r, c, sumM, sumS, 7));
                            }
                        }
                        for(FireBall f: tempList) {
                            list.remove(f);
                        }
                    }
                }
            }
        }
        int answer = 0;
        for (FireBall f : list) {
            answer += f.m;
        }
        System.out.println(answer);
    }
}

class FireBall {
    int r;
    int c;
    int m;
    int s;
    int d;

    public FireBall(int r, int c, int m, int s, int d) {
        super();
        this.r = r;
        this.c = c;
        this.m = m;
        this.s = s;
        this.d = d;
    }
}