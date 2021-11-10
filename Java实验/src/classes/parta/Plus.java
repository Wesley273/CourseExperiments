package classes.parta;

public class Plus {
    public static void main(String[] args) {
        Plus add = new Plus();
        add.plus(5, 7);
        add.plus(2, 3, 22);
        add.plus(3, 4, 7, 9.1);
    }

    private void plus(double a, double b) {
        System.out.println(a + "+" + b + "+" + "=" + (a + b));
    }

    private void plus(double a, double b, double c) {
        System.out.println(a + "+" + b + "+" + c + "=" + (a + b + c));
    }

    private void plus(double a, double b, double c, double d) {
        System.out.println(a + "+" + b + "+" + c + "+" + d + "=" + (a + b + c + d));
    }
}
