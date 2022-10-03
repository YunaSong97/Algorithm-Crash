package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_17779 {
    static int n;
    static int[][] map;
    static int[][] filter;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        map = new int[n + 1][n + 1];
        filter = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {
                for (int d1 = 1; d1 < n; d1++) {
                    for (int d2 = 1; d2 < n; d2++) {
                        if (x + d1 + d2 <= n && 1 <= y - d1 && y + d2 <= n) {
                            filter = new int[n + 1][n + 1];

                            for (int i = 0; i <= d1; i++) {
                                filter[x + i][y - i] = 5;
                                filter[x + d2 + i][y + d2 - i] = 5;
                            }

                            for (int i = 0; i <= d2; i++) {
                                filter[x + i][y + i] = 5;
                                filter[x + d1 + i][y - d1 + i] = 5;
                            }

                            int k = 0;
                            for (int i = y - d1 + 1; i < y + d2; i++) {
                                while (filter[k][i] != 5) {
                                    k++;
                                }
                                k++;
                                while (filter[k][i] != 5) {
                                    filter[k++][i] = 5;
                                }
                                k = 0;
                            }

                            for (int i = 1; i <= n; i++) {
                                for (int j = 1; j <= n; j++) {
                                    if (filter[i][j] != 5) {
                                        if (i < x + d1 && j <= y) {
                                            filter[i][j] = 1;
                                        }
                                        if (i <= x + d2 && y < j) {
                                            filter[i][j] = 2;
                                        }
                                        if (x + d1 <= i && j < y - d1 + d2) {
                                            filter[i][j] = 3;
                                        }
                                        if (x + d2 < i && y - d1 + d2 <= j) {
                                            filter[i][j] = 4;
                                        }
                                    }
                                }
                            }

                            int[] sumAry = new int[5];
                            for (int i = 1; i <= n; i++) {
                                for (int j = 1; j <= n; j++) {
                                    if (filter[i][j] == 0) {
                                        sumAry[4] += map[i][j];
                                    } else {
                                        sumAry[filter[i][j] - 1] += map[i][j];
                                    }
                                }
                            }

                            List<Integer> sumList = new ArrayList<>();
                            for (int i = 0; i < 5; i++) {
                                sumList.add(sumAry[i]);
                            }

                            Collections.sort(sumList);
                            answer = Math.min(answer, sumList.get(4) - sumList.get(0));
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
