import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Record implements Comparable<Record> {
	String num;
	String in;
	String out;
	int fee;
	int minute;
	boolean check;

	public Record(String num) {
		this.num = num;
	}

	public void calTime() {
		String[] token1 = this.in.split(":");
		int inMin = Integer.parseInt(token1[0]) * 60 + Integer.parseInt(token1[1]);
		int outMin;

		String[] token2 = this.out.split(":");
		outMin = Integer.parseInt(token2[0]) * 60 + Integer.parseInt(token2[1]);

		this.minute += outMin - inMin;
	}

	public void calFee(int[] fees) {
		if (this.minute >= fees[0]) {
			this.fee = (int) (fees[1] + Math.ceil((this.minute - fees[0]) / (double) fees[2]) * fees[3]);
			return;
		} else {
			this.fee = fees[1];
		}
	}

	@Override
	public int compareTo(Record o) {
		return Integer.parseInt(this.num) - Integer.parseInt(o.num);
	}
}

class PG_92341 {
	public int[] solution(int[] fees, String[] records) {
		int[] answer = {};

		HashMap<String, Record> hashMap = new HashMap<>();

		for (String s : records) {
			String[] token = s.split(" ");

			if (token[2].equals("IN")) {
				if (!hashMap.containsKey(token[1])) {
					Record rec = new Record(token[1]);
					rec.in = token[0];
					hashMap.put(rec.num, rec);
					rec.check = false;
				} else {
					Record rec = hashMap.get(token[1]);
					rec.in = token[0];
					rec.check = false;
				}

			} else {
				Record rec = hashMap.get(token[1]);
				rec.out = token[0];

				rec.calTime();
				rec.check = true;
			}
		}

		ArrayList<Record> recList = new ArrayList<>();
		for (String key : hashMap.keySet()) {
			Record rec = hashMap.get(key);

			if (!rec.check) {
				rec.out = "23:59";
				rec.calTime();
			}

			rec.calFee(fees);
			recList.add(rec);
		}

		Collections.sort(recList);

		answer = new int[recList.size()];
		for (int i = 0; i < recList.size(); i++) {
			answer[i] = recList.get(i).fee;
		}

		return answer;
	}
}
