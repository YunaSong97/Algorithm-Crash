import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        HashMap<String, String> in = new HashMap<>();
        HashMap<String, Integer> time = new HashMap<>();
        
        for(String str : records) {
            String[] temp = str.split(" ");
            String hm = temp[0];
            String number = temp[1];
            String record = temp[2];
            if(record.equals("IN")) {
                in.put(number, hm);
            } else if (record.equals("OUT")) {
                temp = hm.split(":");
                int outHour = Integer.parseInt(temp[0]);
                int outMinute = Integer.parseInt(temp[1]);
                temp = in.get(number).split(":");
                int inHour = Integer.parseInt(temp[0]);
                int inMinute = Integer.parseInt(temp[1]);
                outHour -= inHour;
                if(outHour > 0) {
                    outMinute += outHour * 60;
                }
                outMinute -= inMinute;
                if(time.containsKey(number)) {
                    int minute = time.get(number);
                    minute += outMinute;
                    time.put(number, minute);
                } else {
                    time.put(number, outMinute);
                }
                in.remove(number);
            }
        }
        //no out process
        for(String number : in.keySet()) {
            int outHour = 23;
            int outMinute = 59;
            String[] temp = in.get(number).split(":");
            int inHour = Integer.parseInt(temp[0]);
            int inMinute = Integer.parseInt(temp[1]);
            outHour -= inHour;
            if(outHour > 0) {
                outMinute += outHour * 60;
            }
            outMinute -= inMinute;
            if(time.containsKey(number)) {
                int minute = time.get(number);
                minute += outMinute;
                time.put(number, minute);
            } else {
                time.put(number, outMinute);
            }
        }
        
        int[] answer = new int[time.keySet().size()];
        int idx = 0;
        
        ArrayList<String> list = new ArrayList<>(time.keySet());
        Collections.sort(list);
        for(String number : list) {
            if(time.get(number) <= fees[0]) {
                answer[idx] = fees[1];
            } else {
                answer[idx] += fees[1];
                answer[idx] += fees[3] * ((time.get(number) - fees[0]) / fees[2]);
                if((time.get(number) - fees[0]) % fees[2] != 0) {
                    answer[idx] += fees[3];
                }
            }
            idx++;
        }
        return answer;
    }
}