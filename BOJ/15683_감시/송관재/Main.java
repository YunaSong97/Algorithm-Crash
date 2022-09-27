import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static int answer = Integer.MAX_VALUE;
	static ArrayList<int[]> cctv;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cctv = new ArrayList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] >= 1 && map[i][j] <= 5) {
					int[] node = new int[2];
					node[0] = i;
					node[1] = j;
					cctv.add(node);
				}
			}
		}
		solve(0);
		System.out.println(answer);
	}

	public static void solve(int depth) {
		if(depth == cctv.size()) {
			//calculation
			int sum = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == 0) {
						sum++;
					}
				}
			}
			answer = Math.min(answer, sum);
			return;
		}
		int i = cctv.get(depth)[0];
		int j = cctv.get(depth)[1];
		if(map[i][j] == 1) {
			run(i, j, 1, 7 + depth);//up run
			solve(depth+1);
			rollback(i, j, 1, 7 + depth);//up rollback
            run(i, j, 2, 7 + depth);//left run
            solve(depth+1);
            rollback(i, j, 2, 7 + depth);//left rollback
			run(i, j, 3, 7 + depth);//down run
			solve(depth+1);
            rollback(i, j, 3, 7 + depth);//down rollback
			run(i, j, 4, 7 + depth);//right run
			solve(depth+1);
			rollback(i, j, 4, 7 + depth);//right rollback
		} else if(map[i][j] == 2) {
            run(i, j, 1, 7 + depth);//up run
            run(i, j, 3, 7 + depth);//down run
			solve(depth+1);
            rollback(i, j, 1, 7 + depth);//up rollback
            rollback(i, j, 3, 7 + depth);//down rollback
            run(i, j, 2, 7 + depth);//left run
            run(i, j, 4, 7 + depth);//right run
			solve(depth+1);
            rollback(i, j, 2, 7 + depth);//left rollback
            rollback(i, j, 4, 7 + depth);//right rollback
		} else if(map[i][j] == 3) {
            run(i, j, 1, 7 + depth);//up run
            run(i, j, 2, 7 + depth);//left run
			solve(depth+1);
            rollback(i, j, 1, 7 + depth);//up rollback
            rollback(i, j, 2, 7 + depth);//left rollback
            run(i, j, 1, 7 + depth);//up run
            run(i, j, 4, 7 + depth);//right run
			solve(depth+1);
            rollback(i, j, 1, 7 + depth);//up rollback
            rollback(i, j, 4, 7 + depth);//right rollback
            run(i, j, 3, 7 + depth);//down run
            run(i, j, 2, 7 + depth);//left run
			solve(depth+1);
            rollback(i, j, 3, 7 + depth);//down rollback
            rollback(i, j, 2, 7 + depth);//left rollback
            run(i, j, 3, 7 + depth);//down run
            run(i, j, 4, 7 + depth);//right run
			solve(depth+1);
            rollback(i, j, 3, 7 + depth);//down rollback
            rollback(i, j, 4, 7 + depth);//right rollback
		} else if(map[i][j] == 4) {
            run(i, j, 2, 7 + depth);//left run
            run(i, j, 3, 7 + depth);//down run
            run(i, j, 4, 7 + depth);//right run
			solve(depth+1);
            rollback(i, j, 2, 7 + depth);//left rollback
            rollback(i, j, 3, 7 + depth);//down rollback
            rollback(i, j, 4, 7 + depth);//right rollback
            run(i, j, 1, 7 + depth);//up run
            run(i, j, 3, 7 + depth);//down run
            run(i, j, 4, 7 + depth);//right run
			solve(depth+1);
            rollback(i, j, 1, 7 + depth);//up rollback
            rollback(i, j, 3, 7 + depth);//down rollback
            rollback(i, j, 4, 7 + depth);//right rollback
            run(i, j, 1, 7 + depth);//up run
            run(i, j, 2, 7 + depth);//left run
            run(i, j, 4, 7 + depth);//right run
			solve(depth+1);
            rollback(i, j, 1, 7 + depth);//up rollback
            rollback(i, j, 2, 7 + depth);//left rollback
            rollback(i, j, 4, 7 + depth);//right rollback
            run(i, j, 1, 7 + depth);//up run
            run(i, j, 2, 7 + depth);//left run
            run(i, j, 3, 7 + depth);//down run
			solve(depth+1);
            rollback(i, j, 1, 7 + depth);//up rollback
            rollback(i, j, 2, 7 + depth);//left rollback
            rollback(i, j, 3, 7 + depth);//down rollback
		} else if(map[i][j] == 5) {
            run(i, j, 1, 7 + depth);//up run
            run(i, j, 2, 7 + depth);//left run
            run(i, j, 3, 7 + depth);//down run
            run(i, j, 4, 7 + depth);//right run
			solve(depth+1);
            rollback(i, j, 1, 7 + depth);//up rollback
            rollback(i, j, 2, 7 + depth);//left rollback
            rollback(i, j, 3, 7 + depth);//down rollback
            rollback(i, j, 4, 7 + depth);//right rollback
		}
	}
	
	public static void run(int i, int j, int direction, int value) {
	    if(direction == 1) {//up
            for(int k = i-1; k >= 0; k--) {
                if(map[k][j] == 6) {
                    break;
                }
                if(map[k][j] == 0) {
                    map[k][j] = value;
                }
            }
	    } else if(direction == 2) {//left
            for(int k = j-1; k >= 0; k--) {
                if(map[i][k] == 6) {
                    break;
                }
                if(map[i][k] == 0) {
                    map[i][k] = value;
                }
            }
	    } else if(direction == 3) {//down
            for(int k = i+1; k < N; k++) {
                if(map[k][j] == 6) {
                    break;
                }
                if(map[k][j] == 0) {
                    map[k][j] = value;
                }
            }
        } else if(direction == 4) {//right
            for(int k = j+1; k < M; k++) {
                if(map[i][k] == 6) {
                    break;
                }
                if(map[i][k] == 0) {
                    map[i][k] = value;
                }
            }
        }
	}
	public static void rollback(int i, int j, int direction, int value) {
        if(direction == 1) {//up
            for(int k = i-1; k >= 0; k--) {
                if(map[k][j] == 6) {
                    break;
                }
                if(map[k][j] == value) {
                    map[k][j] = 0;
                }
            }
        } else if(direction == 2) {//left
            for(int k = j-1; k >= 0; k--) {
                if(map[i][k] == 6) {
                    break;
                }
                if(map[i][k] == value) {
                    map[i][k] = 0;
                }
            }
        } else if(direction == 3) {//down
            for(int k = i+1; k < N; k++) {
                if(map[k][j] == 6) {
                    break;
                }
                if(map[k][j] == value) {
                    map[k][j] = 0;
                }
            }
        } else if(direction == 4) {//right
            for(int k = j+1; k < M; k++) {
                if(map[i][k] == 6) {
                    break;
                }
                if(map[i][k] == value) {
                    map[i][k] = 0;
                }
            }
        }
	}
}
