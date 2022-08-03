import java.util.Scanner;

public class Problem14888 {
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static int N;
    static int[] values;
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        values = new int[N];
        for (int i = 0; i < N; i++) {
            values[i] = s.nextInt();
        }
        int plus = s.nextInt();
        int minus = s.nextInt();
        int mul = s.nextInt();
        int div = s.nextInt();
        operation(values[0], 1, plus, minus, mul, div);
        System.out.println(max);
        System.out.println(min);
    }
    
    public static void operation(int value, int index, int plus, int minus, int mul, int div) {
        if(index == N) {
            if(max < value) {
                max = value;
            }
            if(min > value) {
                min = value;
            }
            return;
        }
        if(plus != 0) {
            operation(value + values[index], index + 1, plus - 1, minus, mul, div);
        }
        if(minus != 0) {
            operation(value - values[index], index + 1, plus, minus - 1, mul, div);
        }
        if(mul != 0) {
            operation(value * values[index], index + 1, plus, minus, mul - 1, div);
        }
        if(div != 0) {
            operation(value / values[index], index + 1, plus, minus, mul, div - 1);
        }
    }
}
