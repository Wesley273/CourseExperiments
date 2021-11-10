package arithmetic.indefiniteequation;

public class HundredChickens {
    public static void main(String[] args) {
        useTripleLoop();
        useDoubleLoop();
        useOneLoop();
    }

    public static void useTripleLoop() {
        System.out.println("该方法使用了三重循环");
        for (int roaster = 0; roaster <= 100; roaster++) {
            for (int hen = 0; hen <= 100; hen++) {
                for (int chick = 0; chick <= 100; chick++) {
                    if (3 * roaster + 5 * hen + chick / 3 == 100 && roaster + hen + chick == 100 && chick % 3 == 0) {
                        System.out.println("公鸡数=" + roaster + " " + " 母鸡数=" + hen + " " + " 小鸡数=" + chick);
                    }
                }
            }
        }
    }

    public static void useDoubleLoop() {
        System.out.println("该方法使用了二重循环");
        // 将该三元不定方程组中消去chick，得4*roaster+7*hen=100，则roaster必小于等于25，hen必小于等于14
        for (int roaster = 0; roaster <= 25; roaster++) {
            for (int hen = 0; hen <= 14; hen++) {
                if (4 * roaster + 7 * hen == 100) {
                    int chick = 100 - roaster - hen;
                    System.out.println("公鸡数=" + roaster + " " + " 母鸡数=" + hen + " " + " 小鸡数=" + chick);
                }
            }
        }
    }

    public static void useOneLoop() {
        System.out.println("该方法使用了一重循环");
        // 将该三元不定方程组中消去chick，得4*roaster+7*hen=100，则hen=(100-4*roaster)/7，其须为整数
        for (int roaster = 0; roaster <= 25; roaster++) {
            if ((100 - 4 * roaster) % 7 == 0) {
                int hen = (100 - 4 * roaster) / 7;
                int chick = 100 - roaster - hen;
                System.out.println("公鸡数=" + roaster + " " + " 母鸡数=" + hen + " " + " 小鸡数=" + chick);
            }
        }
    }
}
