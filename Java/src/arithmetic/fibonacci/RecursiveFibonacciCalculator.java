package arithmetic.fibonacci;

public class RecursiveFibonacciCalculator {
    public static void main(String[] args) {
        int month = 24;
        System.out.println("������ӳ������" + month + "����ʱ��������Ϊ��" + getFibonacci(month) + "��");
    }

    public static int getFibonacci(int month) {
        // ���������ʼֵ,�·��Ǵ�3��ʼ�ģ�����Ϊ�·�Ϊ2ʱ������Ϊ1
        if (month <= 2) {
            return (1);
        } else {
            // F(N)=F(N-1)+F(N-2)
            return (getFibonacci(month - 1) + getFibonacci(month - 2));
        }
    }
}
