import java.util.Scanner;

public class Main {
    static int N;
    static int[][] map;
    static int[][] arr;
    static String[] dir = new String[5];
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        map = new int[N][N];
        arr = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                map[i][j] = s.nextInt();
            }
        }
        dfs(0);
        System.out.println(max);
    }

    public static void copyMap() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                arr[i][j] = map[i][j];
            }
        }
    }
    
    public static void dfs(int depth) {
        if(depth == 5) {
            copyMap();
            for(int i=0; i<5; i++) {
                move(dir[i]);
            }
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(max < arr[i][j]) {
                        max = arr[i][j];
                    }
                }
            }
            return;
        }
        dir[depth] = "left";
        dfs(depth + 1);
        dir[depth] = "right";
        dfs(depth + 1);
        dir[depth] = "up";
        dfs(depth + 1);
        dir[depth] = "down";
        dfs(depth + 1);
    }
    
    public static void move(String direction) {
        if(direction.equals("left")){
            for(int i = 0; i < N; i++){
                int left = 0;
                while(left < N - 1){
                    int right = 0;
                    for(int j = left + 1; j < N; j++){
                        if(arr[i][left] == 0 && arr[i][j] != 0){
                            arr[i][left] = arr[i][j];
                            arr[i][j] = 0;
                        } else if(arr[i][left] != 0){
                            if(arr[i][left] == arr[i][j]){
                                arr[i][left] *= 2;
                                arr[i][j] = 0;
                                left++;
                            } else {
                                if(arr[i][j] != 0){
                                    right = arr[i][j];
                                    left++;
                                    if(arr[i][left] == 0){
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if(right == 0){
                        left++;
                    }
                }
            }
        } else if(direction.equals("right")){
            for(int i = 0; i < N; i++){
                int right = N - 1;
                while(right > 0){
                    int left = 0;
                    for(int j = right - 1; j >= 0; j--){
                        if(arr[i][right] == 0 && arr[i][j] != 0){
                            arr[i][right] = arr[i][j];
                            arr[i][j] = 0;
                        } else if(arr[i][right] != 0){
                            if(arr[i][right] == arr[i][j]){
                                arr[i][right] *= 2;
                                arr[i][j] = 0;
                                right--;
                            } else {
                                if(arr[i][j] != 0){
                                    left = arr[i][j];
                                    right--;
                                    if(arr[i][right] == 0){
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if(left == 0){
                        right--;
                    }
                }
            }
        } else if(direction.equals("up")){
            for(int i = 0; i < N; i++){
                int up = 0;
                while(up < N - 1){
                    int down = 0;
                    for(int j = up + 1; j < N; j++){
                        if(arr[up][i] == 0 && arr[j][i] != 0){
                            arr[up][i] = arr[j][i];
                            arr[j][i] = 0;
                        } else if(arr[up][i] != 0){
                            if(arr[up][i] == arr[j][i]){
                                arr[up][i] *= 2;
                                arr[j][i] = 0;
                                up++;
                            } else {
                                if(arr[j][i] != 0){
                                    down = arr[j][i];
                                    up++;
                                    if(arr[up][i] == 0){
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if(down == 0){
                        up++;
                    }
                }
            }
        } else {
            for(int i = 0; i < N; i++){
                int down = N - 1;
                while(down > 0){
                    int up = 0;
                    for(int j = down - 1; j >= 0; j--){
                        if(arr[down][i] == 0 && arr[j][i] != 0){
                            arr[down][i] = arr[j][i];
                            arr[j][i] = 0;
                        } else if(arr[down][i] != 0){
                            if(arr[down][i] == arr[j][i]){
                                arr[down][i] *= 2;
                                arr[j][i] = 0;
                                down--;
                            } else {
                                if(arr[j][i] != 0){
                                    up = arr[j][i];
                                    down--;
                                    if(arr[down][i] == 0){
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if(up == 0){
                        down--;
                    }
                }
            }
        }
    }
}
