package arithmetic.multiple;

public class MultipleOfNine {

    public static void main(String[] args) {
        double sum = 0;
        int count = 10;
        for (int i = 0; i < count; i++) {
            sum += judgeMultiple();
        }
        double averageRunTime = sum / count;
        System.out.println("程序运行 " + count + " 次的平均时间为：" + averageRunTime + "ms");
    }

    public static double judgeMultiple() {
        int one, two, three, four, five, six, seven, eight, nine, ten, num = 0, count = 0;
        double startTime = System.currentTimeMillis();
        label2:
        for (ten = 0; ten <= 2; ten++) {
            for (nine = 0; nine <= 9; nine++) {
                for (eight = 0; eight <= 9; eight++) {
                    for (seven = 0; seven <= 9; seven++) {
                        for (six = 0; six <= 9; six++) {
                            for (five = 0; five <= 9; five++) {
                                for (four = 0; four <= 9; four++) {
                                    for (three = 0; three <= 9; three++) {
                                        for (two = 0; two <= 9; two++) {
                                            label1:
                                            for (one = count; one <= 9; one++) {
                                                // 筛查从0开始，0满足题目条件，则通过switch语句递推地寻找下一个各数位和为9的倍数的数
                                                num = one + 10 * two + 100 * three + 1000 * four + 10000 * five
                                                        + 100000 * six + 1000000 * seven + 10000000 * eight
                                                        + 100000000 * nine + 1000000000 * ten;
                                                switch (one) {
                                                    // 若个位是0，则下一个数位和为9的倍数的数必为个位+9
                                                    // 若个位不是0，则下一个数位和为9的倍数的数必为个位-1，十位+1
                                                    case 0:
                                                        count = 9;
                                                        if (num % 9 == 0) {
                                                            // System.out.println(num+"满足");
                                                            // 将two减1（溢出也无妨）是为了抵消break label1后two的自增
                                                            two--;
                                                            break label1;
                                                        } else {
                                                            System.out.println("有数不满足");
                                                            break label2;
                                                        }
                                                    case 1:
                                                    case 2:
                                                    case 3:
                                                    case 4:
                                                    case 5:
                                                    case 6:
                                                    case 7:
                                                    case 8:
                                                    case 9:
                                                        // 个位-1
                                                        count = one - 1;
                                                        if (num % 9 == 0) {
                                                            // 2147483646是范围内最后一个需要检验的数，到此则应退出
                                                            if (num == 2147483646) {
                                                                break label2;
                                                            }
                                                            // System.out.println(num+"满足");
                                                            break label1;
                                                        } else {
                                                            System.out.println("有数不满足");
                                                            break label2;
                                                        }
                                                    default:
                                                        break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        double endTime = System.currentTimeMillis();
        System.out.println("已到达" + num + "，完成了一轮检验，用时：" + (endTime - startTime));
        return (endTime - startTime);
    }
}