package arithmetic.multiple;

public class MultipleOfNine {

    public static void main(String[] args) {
        double sum = 0;
        int count = 10;
        for (int i = 0; i < count; i++) {
            sum += judgeMultiple();
        }
        double averageRunTime = sum / count;
        System.out.println("�������� " + count + " �ε�ƽ��ʱ��Ϊ��" + averageRunTime + "ms");
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
                                                // ɸ���0��ʼ��0������Ŀ��������ͨ��switch�����Ƶ�Ѱ����һ������λ��Ϊ9�ı�������
                                                num = one + 10 * two + 100 * three + 1000 * four + 10000 * five
                                                        + 100000 * six + 1000000 * seven + 10000000 * eight
                                                        + 100000000 * nine + 1000000000 * ten;
                                                switch (one) {
                                                    // ����λ��0������һ����λ��Ϊ9�ı���������Ϊ��λ+9
                                                    // ����λ����0������һ����λ��Ϊ9�ı���������Ϊ��λ-1��ʮλ+1
                                                    case 0:
                                                        count = 9;
                                                        if (num % 9 == 0) {
                                                            // System.out.println(num+"����");
                                                            // ��two��1�����Ҳ�޷�����Ϊ�˵���break label1��two������
                                                            two--;
                                                            break label1;
                                                        } else {
                                                            System.out.println("����������");
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
                                                        // ��λ-1
                                                        count = one - 1;
                                                        if (num % 9 == 0) {
                                                            // 2147483646�Ƿ�Χ�����һ����Ҫ���������������Ӧ�˳�
                                                            if (num == 2147483646) {
                                                                break label2;
                                                            }
                                                            // System.out.println(num+"����");
                                                            break label1;
                                                        } else {
                                                            System.out.println("����������");
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
        System.out.println("�ѵ���" + num + "�������һ�ּ��飬��ʱ��" + (endTime - startTime));
        return (endTime - startTime);
    }
}