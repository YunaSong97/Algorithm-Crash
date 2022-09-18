import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        HashMap<Integer, ArrayList<Record>> in = new HashMap<>();
        HashMap<Integer, ArrayList<Record>> out = new HashMap<>();

        for (int i=0; i<records.length; i++){
            String[] s = records[i].split(" ");
            String[] time = s[0].split(":");
            int t = Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);

            int num = Integer.parseInt(s[1]);
            ArrayList<Record> al = new ArrayList<>();
            // HashMap Key=차량번호, Value=Record(시간, 차량번호, 내역) 저장
            if (s[2].equals("IN")){
                if (in.containsKey(num)){
                    al = in.get(num);
                    al.add(new Record(t, num, s[2]));

                    in.replace(num, al);
                } else{
                    al.add(new Record(t, num, s[2]));
                    in.put(num, al);
                }
            } else{
                if (out.containsKey(num)){
                    al = out.get(num);
                    al.add(new Record(t, num, s[2]));

                    out.replace(num, al);
                } else{
                    al.add(new Record(t, num, s[2]));
                    out.put(num, al);
                }
            }
        }

        // 차량 번호 순으로 오름차순 정렬
        ArrayList<Integer> ans = new ArrayList<>();
        Object[] ob = in.keySet().toArray();
        Arrays.sort(ob);

        // 요금 계산
        for (int j=0; j<ob.length; j++){
            int tSum = 0;
            int num = (int)ob[j];  // 차량번호

            if (out.get(num) != null){  // 출차 기록이 있다면
                // 출차기록이 모두 있는 경우 요금계산
                if (in.get(num).size() == out.get(num).size()){
                    for (int i=0; i<in.get(num).size(); i++){
                        tSum += out.get(num).get(i).time - in.get(num).get(i).time;
                    }
                } else{  // 출차기록이 하나 빠져있는 경우 요금계산
                    for (int i=0; i<out.get(num).size(); i++){
                        tSum += out.get(num).get(i).time - in.get(num).get(i).time;
                    }
                    tSum += (23*60 + 59) - in.get(num).get(in.get(num).size()-1).time;
                }
            } else{  // 출차 기록이 없으면 23:59분 기준으로 요금 계산
                tSum += (23*60 + 59) - in.get(num).get(in.get(num).size()-1).time;
            }
            
            int price = fees[1];
            if (tSum > fees[0]){
                price += Math.ceil((double)(tSum-fees[0])/fees[2])*fees[3];
            }

            ans.add(price);
        }

        // 결과를 answer배열에 저장
        int[] answer = new int[ans.size()];
        for (int i=0; i<ans.size(); i++){
            answer[i] = ans.get(i);
        }

        return answer;
    }
}

// 시간이 빠른 순으로 정렬
class Record implements Comparable<Record>{
    int time;
    int number;
    String io;

    Record(int time, int number, String io){
        this.time = time;
        this.number = number;
        this.io = io;
    }

    @Override
    public int compareTo(Record o) {
        if (number == o.number){
            return time-o.time;
        }
        return number-o.number;
    }
}
