package arithmetic.narcissistic;

public class NarcissisticNumberFinder {
    public static void main(String[] args) {
        int units = 0, tens = 0, hundreds = 0;
        for (int i = 100; i <= 999; i++) {
            // ������������λ��ʮλ����λ
            units = i % 10;
            tens = (i / 10) % 10;
            hundreds = i / 100;
            // Math.pow�Ǽ���˷��ķ���
            if ((Math.pow(units, 3) + Math.pow(tens, 3) + Math.pow(hundreds, 3)) == i) {
                System.out.println(i + "��ˮ�ɻ���");
            }
        }
    }
}
