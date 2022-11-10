import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] abilityTable;
    static int ans = Integer.MAX_VALUE;
    static ArrayList<Integer> combination = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        abilityTable = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                abilityTable[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int r = 1; r <= N / 2; r++) {
            combination(0, r, 0);
        }

        System.out.println(ans);
    }

    static void combination(int cnt, int r, int lastNum) {
        if (cnt == r) {
            int sSum = 0;
            int lSum = 0;
            boolean[] selected = new boolean[N + 1];
            // combination에 뽑힌 조합으로 스타트팀 팀 능력치 계산
            int combSize = combination.size();
            for (int i = 0; i < combSize; i++) {
                int pi = combination.get(i);
                if (!selected[pi]) selected[pi] = true;
                for (int j = i + 1; j < combSize; j++) {
                    int pj = combination.get(j);
                    sSum += abilityTable[pi][pj];
                    sSum += abilityTable[pj][pi];
                }
            }
            ArrayList<Integer> linkTeam = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                if (!selected[i]) linkTeam.add(i);
            }

            int linkTeamSize = linkTeam.size();
            for (int i = 0; i < linkTeamSize; i++) {
                int pi = linkTeam.get(i);
                if (!selected[pi]) selected[pi] = true;
                for (int j = i + 1; j < linkTeamSize; j++) {
                    int pj = linkTeam.get(j);
                    lSum += abilityTable[pi][pj];
                    lSum += abilityTable[pj][pi];
                }
            }

            ans = Math.min(ans, Math.abs(sSum - lSum));
        }

        for (int i = lastNum + 1; i <= N; i++) {
            combination.add(i);
            combination(cnt + 1, r, i);
            combination.remove(combination.size() - 1);
        }
    }

}

