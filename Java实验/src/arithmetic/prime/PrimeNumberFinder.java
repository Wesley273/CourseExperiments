package arithmetic.prime;

public class PrimeNumberFinder {
    public static void main(String[] args) {
        int count = 1;
        System.out.println(2 + " 是素数");
        // 单独对2打印后，只需筛查剩下的奇数
        for (int i = 3; i <= 200; i += 2) {
            boolean flag = true;
            // 根据整数的质因数分解理论，其实只需筛查3~根号n之间的质数是否为其因子，下面的筛选范围广一些，为范围内的奇数
            for (int j = 3; j <= java.lang.Math.sqrt(i); j += 2) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                count++;
                System.out.println(i + " 是素数");
            }
        }
        System.out.println("素数个数为：" + count);
    }
}
