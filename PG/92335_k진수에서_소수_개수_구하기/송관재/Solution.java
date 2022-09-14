class Solution {
    public static int solution(int n, int k) {
        int temp = n;
        long digit = 1;
        long number = 0;
        while(temp != 0){
            int t = temp % k;
            temp /= k;
            number += digit * (long)t;
            digit *= 10;
        }
        int answer = 0;
        String str = Long.toString(number);
        for(String s : str.split("0")) {
        	if(s.length() == 0) {
        		continue;
        	}
        	long t = Long.parseLong(s);
        	boolean isPrime = true;
        	//prime number check
        	if(t > 2 && t % 2 == 0) {
        		continue;
        	}
        	if(t == 1) {
        		continue;
        	}
        	for(int i=3; i<Math.sqrt(t) + 1; i++) {
        		if(t % i == 0) {
        			isPrime = false;
        			break;
        		}
        	}
        	if(isPrime) {
        		answer++;
        	}
        }
        return answer;
    }
}