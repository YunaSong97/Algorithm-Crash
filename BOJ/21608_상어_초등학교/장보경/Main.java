import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] arr;
    static int[][] seat;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N*N+1][4];
        seat = new int[N+1][N+1];
        for (int i=1; i<=N*N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int student = Integer.parseInt(st.nextToken());
            for (int j=0; j<4; j++){
                arr[student][j] = Integer.parseInt(st.nextToken());
            }
            set(student);
        }

        ans = 0;
        satisfaction();
        System.out.println(ans);
    }

    static int[] X = {-1, 1, 0, 0};
    static int[] Y = {0, 0, -1, 1};

    public static void set(int student){
        int[][] emptyArr = new int[N+1][N+1];
        int[][] likeArr = new int[N+1][N+1];

        for (int i=1; i<=N; i++){
            for (int j=1; j<=N; j++){
                int emptyCnt = 0;
                int likeCnt = 0;

                for (int k=0; k<4; k++){
                    int nx = i+X[k];
                    int ny = j+Y[k];

                    if (nx>0 && nx<=N && ny>0 && ny<=N){
                        if (seat[nx][ny]==0){
                            emptyCnt++;
                        } else{
                            for (int l=0; l<4; l++){
                                // student가 좋아하는 학생이 seat에 있다면,,?
                                if (seat[nx][ny] == arr[student][l]){
                                    likeCnt++;
                                }
                            }
                        }
                    }
                }
                if (seat[i][j] == 0){
                    emptyArr[i][j] = emptyCnt;
                    likeArr[i][j] = likeCnt;
                }
            }
        }

        ArrayList<int[]> likeXY = new ArrayList<>();
        int lMax = -1;
        for (int i=1; i<=N; i++) {
            for (int j = 1; j <= N; j++) {
                if (seat[i][j] == 0){
                    if (likeArr[i][j] > lMax){
                        lMax = likeArr[i][j];
                        likeXY.clear();
                        likeXY.add(new int[]{i, j});
                    } else if (likeArr[i][j] == lMax){
                        likeXY.add(new int[]{i, j});
                    }
                }
            }
        }

        if (likeXY.size() > 1){
            ArrayList<int[]> al = new ArrayList<>();
            int max = -1;
            for (int i=0; i<likeXY.size(); i++){
                int[] xy = likeXY.get(i);
                // likeXY의 x,y와 같은 emptyArr를 찾아서 그 최대값이 있는 x,y를 구함
                if (seat[xy[0]][xy[1]] == 0){
                    if (max < emptyArr[xy[0]][xy[1]]){
                        max = emptyArr[xy[0]][xy[1]];
                        al.clear();
                        al.add(new int[]{xy[0], xy[1]});
                    } else if(max == emptyArr[xy[0]][xy[1]]){
                        al.add(new int[]{xy[0], xy[1]});
                    }
                }
            }

            if (al.size() > 1){
                int[][] emptySort = new int[al.size()][2];
                int j = 0;
                for (int[] eArr: al){
                    emptySort[j][0] = eArr[0];
                    emptySort[j][1] = eArr[1];
                    j++;
                }

                Arrays.sort(emptySort, new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        if (o1[0] == o2[0]){
                            return o1[1]-o2[1];
                        } else{
                            return o1[0]-o2[0];
                        }
                    }
                });
                seat[emptySort[0][0]][emptySort[0][1]] = student;
            } else{
                int[] empty = al.get(0);
                seat[empty[0]][empty[1]] = student;
            }
        } else{
            int[] xy = likeXY.get(0);
            seat[xy[0]][xy[1]] = student;
        }
    }
    
    static void satisfaction(){
        for (int i=1; i<=N; i++){
            for (int j=1; j<=N; j++){
                int student = seat[i][j];
                int cnt = 0;

                for (int k=0; k<4; k++){
                    int nx = i+X[k];
                    int ny = j+Y[k];

                    if (nx>0 && nx<=N && ny>0 && ny<=N){
                        for (int l=0; l<4; l++){
                            if (seat[nx][ny]==arr[student][l]){
                                cnt++;
                            }
                        }
                    }
                }

                switch (cnt){
                    case 0:
                        break;
                    case 1:
                        ans += 1;
                        break;
                    case 2:
                        ans += 10;
                        break;
                    case 3:
                        ans += 100;
                        break;
                    case 4:
                        ans += 1000;
                        break;
                }
            }
        }
    }
}
