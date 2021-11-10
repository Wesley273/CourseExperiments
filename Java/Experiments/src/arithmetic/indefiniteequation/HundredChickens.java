package arithmetic.indefiniteequation;

public class HundredChickens {
    public static void main(String[] args) {
        useTripleLoop();
        useDoubleLoop();
        useOneLoop();
    }

    public static void useTripleLoop() {
        System.out.println("�÷���ʹ��������ѭ��");
        for (int roaster = 0; roaster <= 100; roaster++) {
            for (int hen = 0; hen <= 100; hen++) {
                for (int chick = 0; chick <= 100; chick++) {
                    if (3 * roaster + 5 * hen + chick / 3 == 100 && roaster + hen + chick == 100 && chick % 3 == 0) {
                        System.out.println("������=" + roaster + " " + " ĸ����=" + hen + " " + " С����=" + chick);
                    }
                }
            }
        }
    }

    public static void useDoubleLoop() {
        System.out.println("�÷���ʹ���˶���ѭ��");
        // ������Ԫ��������������ȥchick����4*roaster+7*hen=100����roaster��С�ڵ���25��hen��С�ڵ���14
        for (int roaster = 0; roaster <= 25; roaster++) {
            for (int hen = 0; hen <= 14; hen++) {
                if (4 * roaster + 7 * hen == 100) {
                    int chick = 100 - roaster - hen;
                    System.out.println("������=" + roaster + " " + " ĸ����=" + hen + " " + " С����=" + chick);
                }
            }
        }
    }

    public static void useOneLoop() {
        System.out.println("�÷���ʹ����һ��ѭ��");
        // ������Ԫ��������������ȥchick����4*roaster+7*hen=100����hen=(100-4*roaster)/7������Ϊ����
        for (int roaster = 0; roaster <= 25; roaster++) {
            if ((100 - 4 * roaster) % 7 == 0) {
                int hen = (100 - 4 * roaster) / 7;
                int chick = 100 - roaster - hen;
                System.out.println("������=" + roaster + " " + " ĸ����=" + hen + " " + " С����=" + chick);
            }
        }
    }
}
