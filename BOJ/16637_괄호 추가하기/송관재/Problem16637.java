import java.util.Scanner;

public class Problem16637 {
    
    static int N;
    static String str;
    static int T = 0;
    static int MAX = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        s.nextLine();
        str = s.nextLine();
        operation(2, Integer.parseInt(str.charAt(0) + ""));
        System.out.println(MAX);
    }
    
    public static void operation(int index, int total) {
        if(index >= N) {
            MAX = (MAX > total) ? MAX : total;
            return;
        }
        operation(index + 2, value(total, Integer.parseInt(str.charAt(index) + ""), str.charAt(index - 1)));
        
        if(index + 2 < N) {
            int temp = value(Integer.parseInt(str.charAt(index) + ""), Integer.parseInt(str.charAt(index + 2) + ""), str.charAt(index + 1));
            operation(index + 4, value(total, temp, str.charAt(index - 1)));
        }
    }
    
    public static int value(int left, int right, char op) {
        switch(op) {
        case '+':
            return left + right;
        case '-':
            return left - right;
        default:
            return left * right;
        }
    }
}
