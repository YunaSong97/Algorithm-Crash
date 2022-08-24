import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int arr[][] = new int[N*N][7];
        for(int i=0; i<N*N; i++) {
            for(int j=0; j<5; j++) {
                arr[i][j] = s.nextInt();
            }
        }
        int map[][] = new int[N][N];
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        for(int k=0; k<N*N; k++) {
            int like = -1;
            int empty = -1;
            int y = N;
            int x = N;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(map[i][j] == 0) {
                        int tl = 0;
                        int te = 0;
                        for(int t=0; t<4; t++) {
                            if(i + dy[t] < 0 || i + dy[t] >= N || j + dx[t] < 0 || j + dx[t] >= N) {
                                continue;
                            } else {
                                if(map[i + dy[t]][j + dx[t]] == 0) {
                                    te++;
                                } else {
                                    for(int t2=1; t2<5; t2++) {
                                        if(map[i + dy[t]][j + dx[t]] == arr[k][t2]) {
                                            tl++;
                                        }
                                    }
                                }
                            }
                        }
                        if(tl > like) {
                            like = tl;
                            empty = te;
                            y = i;
                            x = j;
                        } else if(tl == like) {
                            if(te > empty) {
                                empty = te;
                                y = i;
                                x = j;
                            } else if(te == empty) {
                                if(y > i) {
                                    y = i;
                                    x = j;
                                } else if(y == i) {
                                    if(x > j) {
                                        x = j;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            map[y][x] = arr[k][0];
            arr[k][5] = y * N + x;
        }
        int answer = 0;
        for(int k=0; k<N*N; k++) {
            int y = arr[k][5] / N;
            int x = arr[k][5] % N;
            int like = 0;
            for(int t=0; t<4; t++) {
                if(y + dy[t] < 0 || y + dy[t] >= N || x + dx[t] < 0 || x + dx[t] >= N) {
                    continue;
                }
                for(int t2=1; t2<5; t2++) {
                    if(map[y + dy[t]][x + dx[t]] == arr[k][t2]) {
                        like++;
                    }
                }
            }
            if(like == 1) {
                answer += 1;
            } else if(like == 2) {
                answer += 10;
            } else if(like == 3) {
                answer += 100;
            } else if(like == 4) {
                answer += 1000;
            }
        }
        System.out.println(answer);
    }

}
