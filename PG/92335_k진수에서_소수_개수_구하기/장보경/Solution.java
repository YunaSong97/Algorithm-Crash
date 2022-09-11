class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String change = Integer.toString(n, k);

        String[] splitChange = change.split("0");
        for (int i=0; i<splitChange.length; i++){
            if (!splitChange[i].equals("") && chkPrime(splitChange[i])){
                answer++;
            }
        }

        return answer;
    }

    public boolean chkPrime(String sub){
        long subInt = Long.parseLong(sub);

        if (subInt == 1){
            return false;
        }
        for (int i=2; i<=Math.sqrt(subInt); i++){
            if (subInt%i == 0){
                return false;
            }
        }

        return true;
    }
}
