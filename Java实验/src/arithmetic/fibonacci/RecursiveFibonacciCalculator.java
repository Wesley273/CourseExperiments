package arithmetic.fibonacci;

public class RecursiveFibonacciCalculator {
    public static void main(String[] args) {
        int month = 24;
        System.out.println("这对兔子出生后第" + month + "个月时兔子总数为：" + getFibonacci(month) + "对");
    }

    public static int getFibonacci(int month) {
        // 这里给出起始值,月份是从3开始的，且认为月份为2时兔子数为1
        if (month <= 2) {
            return (1);
        } else {
            // F(N)=F(N-1)+F(N-2)
            return (getFibonacci(month - 1) + getFibonacci(month - 2));
        }
    }
}
