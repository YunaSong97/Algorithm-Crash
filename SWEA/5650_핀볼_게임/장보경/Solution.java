import java.util.*;
import java.io.*;

public class Solution {
    static int N;
    static int[][] arr;
    static HashMap<Integer, ArrayList<Position>> hPos;
    // 1~5번 블록 직각으로 진행방향 꺾이는지 여부(상:0, 좌:1, 하:2, 우:3)
    static boolean[][] block = {
            {true, true, true, true}
            , {false, true, true, false}
            , {true, true, false, false}
            , {true, false, false, true}
            , {false, false, true, true}
            , {false, false, false, false}
    };
    static int score;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int tNum = 1;

        while(T >= tNum) {
            N = Integer.parseInt(br.readLine());

            arr = new int[N][N];
            hPos = new HashMap<>();
            for (int i=0; i<N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j=0; j<N; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());

                    if (arr[i][j] > 5){
                        ArrayList<Position> al = hPos.get(arr[i][j]);
                        if(al == null){
                            al = new ArrayList<>();
                        }
                        al.add(new Position(i, j, 0));
                        hPos.put(arr[i][j], al);
                    }
                }
            }

            score = 0;
            for (int i=0; i<N; i++){
                for (int j=0; j<N; j++){
                    // 빈 공간일 때 상좌하우 탐색
                    if (arr[i][j] == 0){
                        for (int k=0; k<4; k++){
                            play(new Position(i, j, k));
                        }
                    }
                }
            }

            sb.append("#").append(tNum).append(" ").append(score).append("\n");
            tNum++;
        }
        System.out.println(sb.toString());
    }

    static int[] X = {-1, 0, 1, 0};
    static int[] Y = {0, -1, 0, 1};

    static void play(Position p){
        Queue<Position> q = new ArrayDeque<>();
        q.add(p);

        int startX = p.x;
        int startY = p.y;
        int cnt = 0;
        while (!q.isEmpty()){
            Position n = q.poll();

            int nd = n.d;
            int nx = n.x+X[nd];
            int ny = n.y+Y[nd];

            // 시작 위치로 돌아오면 return
            if (nx==startX && ny==startY){
                score = Math.max(score, cnt);
                return;
            }

            if (nx>-1 && ny>-1 && nx<N && ny<N){
                int pos = arr[nx][ny];
                if (pos == -1){  // 블랙홀이면 return
                    score = Math.max(score, cnt);
                    return;
                }

                if (pos == 0){  // 빈 공간일 때
                    q.add(new Position(nx, ny, nd));
                } else if (pos < 6){  // 1~5블록 만나는 경우
                    if (!block[pos][nd]){
                        // 직각 회전 안해도 되는 경우에는 왔던 길 되돌아감
                        q.add(new Position(nx, ny, (nd+2)%4));
                    } else{
                        // 직각 회전 해야하는 경우
                        if ((pos+nd)%2 == 0){
                            nd--;
                        }else{
                            nd++;
                        }
                        q.add(new Position(nx, ny, (nd+4)%4));
                    }
                    cnt++;
                } else{  // 6~10 웜홀 만나는 경우
                    ArrayList<Position> al = hPos.get(pos);
                    int x1=al.get(0).x, y1=al.get(0).y;
                    int x2=al.get(1).x, y2=al.get(1).y;

                    if (x1 == nx && y1 == ny){
                        q.add(new Position(x2, y2, nd));
                    } else{
                        q.add(new Position(x1, y1, nd));
                    }
                }
            } else{  // 벽 이라면
                q.add(new Position(nx, ny, (nd+2)%4));
                cnt++;
            }
        }
    }
}

class Position{
    int x;
    int y;
    int d;

    Position(int x, int y, int d){
        this.x = x;
        this.y = y;
        this.d = d;
    }
}
