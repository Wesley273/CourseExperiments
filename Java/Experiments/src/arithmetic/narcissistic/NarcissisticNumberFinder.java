package arithmetic.narcissistic;

public class NarcissisticNumberFinder {
    public static void main(String[] args) {
        int units = 0, tens = 0, hundreds = 0;
        for (int i = 100; i <= 999; i++) {
            // 下面三行求解个位、十位、百位
            units = i % 10;
            tens = (i / 10) % 10;
            hundreds = i / 100;
            // Math.pow是计算乘方的方法
            if ((Math.pow(units, 3) + Math.pow(tens, 3) + Math.pow(hundreds, 3)) == i) {
                System.out.println(i + "是水仙花数");
            }
        }
    }
}
