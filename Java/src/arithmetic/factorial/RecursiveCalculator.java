package arithmetic.factorial;

public class RecursiveCalculator {
    public static void main(String[] args) {
        int totalSum = 0;
        for (int i = 1; i <= 9; i++)
            totalSum += getFactorial(i);
        System.out.println(totalSum);
    }

    public static int getFactorial(int factorial) {
        // 这里给出起始值
        if (factorial <= 1) {
            return (1);
        } else {
            // 实现F(N)*F(N-1)*...*F(1)*F(0)
            return (getFactorial(factorial - 1) * factorial);
        }
    }
}
