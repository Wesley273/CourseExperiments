package arithmetic.multiple;

public class MultipleOfThree {
    public static void main(String[] args) {
        int i = 0, sum = 0;
        while (i <= 200) {
            sum += i;
            i += 3;
        }
        System.out.println("200����3�ı���֮��Ϊ��" + sum);
    }
}
