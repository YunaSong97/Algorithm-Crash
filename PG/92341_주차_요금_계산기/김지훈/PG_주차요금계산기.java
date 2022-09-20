import java.io.*;
import java.util.*;

class PG_주차요금계산기 {

    static LinkedHashMap<String,String> charges;
    static int idx=0;
    public static int[] solution(int[] fees, String[] records) {
        String[] an={};
        charges = new LinkedHashMap<>();
        getInfo(records);
        int[] answer = getCharges(fees);

        return answer;
    }
    public static int[] getCharges(int[] fees){
        int[] ans = new int[charges.size()];
        Object[] mapkey = charges.keySet().toArray();
        Arrays.sort(mapkey);
        for(int k=0;k< mapkey.length;k++) {
            String v = charges.get(mapkey[k]);
            StringTokenizer st = new StringTokenizer(v, ",");
            int counts = st.countTokens(); //개수가 짝수면 출차 된것, 홀수면 출차안됨
            int[] getValue = new int[counts];
            int index = 0;
            while (st.hasMoreTokens()) {
                getValue[index] = Integer.parseInt(st.nextToken());
                index++;
            }
            if (counts == 1) {
                int max = (23 * 60) + 59;
                int sum = 0;
                if (max - getValue[0] <= fees[0]) { //11시 59분에 출차했는데 기본 시간보다 작거나 같은 경우는 기본요금
                    sum = fees[1];
                } else { //11시 59분에 출차했는데 기본 시간보다 초과한 경우
                    sum = fees[1] + (int) Math.ceil((max - getValue[0] - fees[0]) / (double)fees[2]) * fees[3];
                }
                ans[idx] = sum;
                idx++;

            } else {
                int sum = 0;
                int charge = 0;
                int c = 0;
                if (counts % 2 == 0) { //출차한 경우
                    for (int i = 0; i < counts - 1; i = i + 2) { //누적시간
                        sum += getValue[i + 1] - getValue[i];
                        c++;
                    }
                    if (sum <= fees[0]) { //기본요금인 경우
                        charge += fees[1];
                    } else { //기본요금 초과
                        charge = fees[1] + (int) Math.ceil((sum - fees[0]) /(double)fees[2]) * fees[3];
                    }
                } else {// 출차 안한경우
                    for (int i = 0; i < counts - 2; i = i + 2) {
                        sum += getValue[i + 1] - getValue[i];
                    }
                    int max = (23 * 60) + 59;
                    sum += max - getValue[counts - 1];
                    if (sum <= fees[0]) { //기본요금인 경우
                        charge += fees[1];
                    } else {
                        charge = fees[1] + (int) (Math.ceil((sum - fees[0]) / (double) fees[2])) * fees[3];
                    }
                    //System.out.println(sum);
                }
                ans[idx] = charge;
                idx++;
            }


        }

        return ans;
    }


    public static void getInfo(String[] records){ //입출차 정보 등록
        for(int i=0;i<records.length;i++){
            StringTokenizer st = new StringTokenizer(records[i]);
            String time = st.nextToken();
            String number = st.nextToken();
            String check = st.nextToken();

            int t=0;
            if(charges.containsKey(number)) { //키를 가지고 있으면 원래키에 뒤에 붙임
                if (time.charAt(0) == '0')
                    t = Integer.parseInt(time.substring(1, 2)) * 60 + Integer.parseInt(time.substring(3, 5)); //분으로 변환
                else
                    t = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));

                charges.put(number,charges.get(number)+t+ ",");
            }
            else{ //키를 가지고 있지 않다면 처음으로 생성
                if (time.charAt(0) == '0')
                    t = (Integer.parseInt(time.substring(1, 2)) * 60) + Integer.parseInt(time.substring(3, 5)); //분으로 변환
                else
                    t = (Integer.parseInt(time.substring(0, 2)) * 60) + Integer.parseInt(time.substring(3, 5));
                charges.put(number,t+ ",");
            }
        }

    }



}
