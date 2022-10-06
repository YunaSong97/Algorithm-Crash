import java.util.*;
import java.io.*;

public class Main {
    static int N, M, K;
    static ArrayList<Fireball>[][] matrix;
    static Queue<Fireball> fQ;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        fQ = new ArrayDeque<>();
        matrix = new ArrayList[N][N];
        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                matrix[i][j] = new ArrayList<>();
            }
        }

        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            fQ.add(new Fireball(r, c, m, s, d));
            matrix[r][c].add(new Fireball(r, c, m, s, d));
        }

        ans = 0;
        for (int i=0; i<K; i++){
            move();
            cal();
        }

        // 남아있는 파이어볼 질량의 합 구하기
        while (!fQ.isEmpty()){
            ans += fQ.poll().m;
        }

        System.out.println(ans);
    }

    static int[] X = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] Y = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void move(){
        while (!fQ.isEmpty()){
            Fireball f = fQ.poll();

            int r = f.r;
            int c = f.c;
            int m = f.m;
            int s = f.s;
            int d = f.d;

            int nr = (r + N + ((s % N) * X[d]))%N;
            int nc = (c + N + ((s % N) * Y[d]))%N;

            matrix[nr][nc].add(new Fireball(nr, nc, m, s, d));  // 새로운 위치에 Fireball 추가
            matrix[r][c].remove(0);  // 기존 위치에 저장된 Fireball 지우기
        }
    }

    public static void cal(){
        for (int n=0; n<N; n++){
            for (int m=0; m<N; m++){
                if (matrix[n][m].size() > 1){  // 두 개 이상의 파이어볼이 있는 칸이라면
                    int mSum = 0;
                    int sSum = 0;

                    boolean odd = false;
                    boolean even = false;
                    boolean checkDirection = false;
                    for (Fireball msd: matrix[n][m]){ // matrix[n][m]에 있는 리스트 만큼 반복
                        // m의 합과 s의 합 구하기
                        mSum += msd.m;
                        sSum += msd.s;

                        // 만약 짝수 홀수 섞여있다면 굳이 밑에 코드들 실행할 필요 없으므로 continue
                        if (checkDirection){
                            continue;
                        }

                        if (msd.d%2 == 0){
                            even = true;
                        } else{
                            odd = true;
                        }

                        if (even == odd){  //even == odd라면 짝수와 홀수가 섞여있는 것
                            checkDirection = true;
                        }
                    }

                    int calM = mSum/5;
                    int calS = sSum/matrix[n][m].size();

                    matrix[n][m].clear(); // matrix[n][m]에 있는 값 다 합쳐줬으니까 clear

                    // 질량 총 합 나눈 값이 0이라면 소멸되기때문에 밑에 코드 실행할 필요 없음
                    if (calM == 0){
                        continue;
                    }

                    if (checkDirection){  // 짝수 또는 홀수만 있을 때
                        for (int i=1; i<8; i+=2){
                            matrix[n][m].add(new Fireball(n, m, calM, calS, i)); // matrix[n][m]에 변경된 값 넣기
                            fQ.add(new Fireball(n, m, calM, calS, i));  // 다음 계산을 위해 fQ에도 추가
                        }
                    } else{  // 짝수와 홀수가 섞여있을 때
                        for (int i=0; i<7; i+=2){
                            matrix[n][m].add(new Fireball(n, m, calM, calS, i));
                            fQ.add(new Fireball(n, m, calM, calS, i));
                        }
                    }
                } else if(matrix[n][m].size() == 1){ // 파이어볼이 한개만 있는 칸이라면 기존 내용 유지한 채 fQ에 추가
                    fQ.add(new Fireball(n, m, matrix[n][m].get(0).m, matrix[n][m].get(0).s, matrix[n][m].get(0).d));
                }
            }
        }
    }
}

class Fireball{
    int r, c, m, s, d;

    Fireball(int r, int c, int m, int s, int d){
        this.r = r;
        this.c = c;
        this.m = m;
        this.s = s;
        this.d = d;
    }
}
