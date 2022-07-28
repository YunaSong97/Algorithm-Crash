import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P_17070_파이프_옮기기 {
    static Integer N;
    static Integer numOfPath = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());
        ArrayList<Integer[]> pipePos = new ArrayList<>();
        Short[][] map = new Short[N + 1][N + 1];

        for (int i = 1; i < N + 1; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 1; j < N + 1; j++) {
                map[i][j] = Short.parseShort(st.nextToken());
            }
        }

        //초기화
        pipePos.add(new Integer[]{1, 1});
        pipePos.add(new Integer[]{1, 2});

        searchPath(map, new Integer[]{1, 1}, new Integer[]{1, 2});
        System.out.println(numOfPath);
    }

    public static void searchPath(Short[][] map, Integer[] pipePosFront, Integer[] pipePosRear) {
        Integer r1 = pipePosFront[0];
        Integer r2 = pipePosRear[0];
        Integer c1 = pipePosFront[1];
        Integer c2 = pipePosRear[1];

        //가로, 세로, 대각중 가능한 경로 검사

        if (map[N][N] == 1)
            return;

        //우선 경계검사부터
        if (r1 > 0 && r2 > 0 && c1 > 0 && c2 > 0 && r1 < N + 1 && r2 < N + 1 && c1 < N + 1 && c2 < N + 1) {

            if ((r2 == N) && (c2 == N)) {
                numOfPath++;
                return;
            }
            //파이프 상태 가로일 경우
            if ((r1 == r2) && (c1 == (c2 - 1))) {
                //가로로 가는것이 가능한 경우
                if ((c2 + 1) < (N + 1) && (r2 < N + 1)) {
                    //System.out.println("Out of bound 가로 가로: " + r1 + "," + c1 + " " + r2 + "," + c2);
                    if (map[r2][c2 + 1] != 1) {
                        searchPath(map, new Integer[] {r1, c1 + 1}, new Integer[] {r2, c2 + 1});
                    }
                }

                if ((c2 + 1) < (N + 1) && (c1 + 1) < (N + 1) && ((r2 + 1) < (N + 1))) {
                    //System.out.println("Out of bound : 가로 대각" + r1 + "," + c1 + " " + r2 + "," + c2);
                    //대각으로 이동 가능한 경우
                    if (map[r2][c2 + 1] != 1 && map[r2 + 1][c2] != 1 && map[r2 + 1][c2 + 1] != 1) {
                        searchPath(map, new Integer[] {r1, c1 + 1}, new Integer[] {r2 + 1, c2 + 1});
                    }
                }
            }
                //파이프 상태 세로일 경우
            else if ((r1 == r2 - 1) && (c1 == c2)) {
                if (r2 + 1 < N + 1) {
                    //System.out.println("Out of bound : 세로 세로" + r1 + "," + c1 + " " + r2 + "," + c2);
                    //세로 이동 가능 경우
                    if (map[r2 + 1][c2] != 1) {
                        searchPath(map, new Integer[] {r1 + 1, c1}, new Integer[] {r2 + 1, c2});
                    }
                }
                //대각으로 이동 가능한 경우

                if ((r2 + 1) < (N + 1) && ((c2 + 1) < N + 1) && ((r1 + 1) < (N + 1))) {
                    //System.out.println("Out of bound : 세로 대각" + r1 + "," + c1 + " " + r2 + "," + c2);
                    if (map[r2][c2 + 1] != 1 && map[r2 + 1][c2] != 1 && map[r2 + 1][c2 + 1] != 1) {
                        searchPath(map, new Integer[] {r1 + 1, c1}, new Integer[] {r2 + 1, c2 + 1});
                    }
                }
            }
            //파이프 상태 대각일 경우
            else if ((r1 == r2 - 1) && (c1 == c2 - 1)) {
                if ((c2 + 1) < (N + 1) && (c1 + 1) < (N + 1) && (r1 + 1) < (N + 1)) {
                    //System.out.println("Out of bound :대각 가로 " + r1 + "," + c1 + " " + r2 + "," + c2);
                    //가로로 가는것이 가능한 경우
                    if (map[r2][c2 + 1] != 1) {
                        searchPath(map, new Integer[] {r1 + 1, c1 + 1}, new Integer[] {r2, c2 + 1});
                    }
                }

                if ((r2 + 1) < (N + 1) && (r1 + 1) < (N + 1) && (c1 + 1) < (N + 1)) {
                    //System.out.println("Out of bound 대각 세로: " + r1 + "," + c1 + " " + r2 + "," + c2);
                    //세로 이동 가능 경우
                    if (map[r2 + 1][c2] != 1) {
                        searchPath(map, new Integer[] {r1 + 1, c1 + 1}, new Integer[] {r2 + 1, c2});
                    }
                }

                if (((c2 + 1) < (N + 1)) && ((r2 + 1) < (N + 1)) && (r1 + 1) < (N + 1) && (r2 + 1) < (N + 1)) {
                    //System.out.println("Out of bound 대각 대각: " + r1 + "," + c1 + " " + r2 + "," + c2);
                    //대각으로 이동 가능한 경우
                    if (map[r2][c2 + 1] != 1 && map[r2 + 1][c2] != 1 && map[r2 + 1][c2 + 1] != 1) {
                        searchPath(map, new Integer[] {r1 + 1, c1 + 1}, new Integer[] {r2 + 1, c2 + 1});
                    }
                }
            }
        }
    }
}
