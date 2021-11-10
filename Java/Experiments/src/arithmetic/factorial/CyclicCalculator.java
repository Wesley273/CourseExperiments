package arithmetic.factorial;

public class CyclicCalculator {
    public static void main(String[] args) {
        int max = 9;
        int totalSum = 0;
        while (max != 0) {
            int factorial = 1;
            for (int i = 1; i <= max; i++) {
                factorial *= i;
            }
            totalSum += factorial;
            max--;
        }
        System.out.println("所求和为：" + totalSum);
    }
}
