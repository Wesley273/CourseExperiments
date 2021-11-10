package classes.parta;

class Circle {
    private double radius = 0;
    double area = 0;
    double perimeter = 0;

    Circle(double radius) {
        this.radius = radius;
    }

    double getArea() {
        this.area = Math.PI * radius * radius;
        return this.area;
    }

    double getPerimeter() {
        this.perimeter = 2 * Math.PI * radius;
        return this.perimeter;
    }
}

class Lader {
    private double up = 0;
    private double bottom = 0;
    private double height = 0;
    double area = 0;

    Lader(double up, double bottom, double height) {
        this.up = up;
        this.bottom = bottom;
        this.height = height;
    }

    double getArea() {
        this.area = (up + bottom) * height / 2;
        return this.area;
    }
}

class Triangle {
    boolean judge;
    private double a = 0;
    private double b = 0;
    private double c = 0;
    double area = 0;
    double perimeter = 0;

    Triangle(double a, double b, double c) {
        judgeTriangle(a, b, c);
        if (this.judge) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    private void judgeTriangle(double a, double b, double c) {
        if (a + b < c || a + c < b || b + c < a) {
            System.out.println(a + " " + b + " " + c + " " + "不能构成三角形！");
            this.judge = false;
        } else {
            this.judge = true;
        }
    }

    double getArea() {
        double p = getPerimeter() / 2;
        if (this.judge) {
            this.area = Math.sqrt(p * (p - this.a) * (p - this.b) * (p - this.c));
            return this.area;
        } else {
            return 0;
        }
    }

    double getPerimeter() {
        if (this.judge) {
            perimeter = this.a + this.b + this.c;
            return this.perimeter;
        } else {
            return 0;
        }
    }

    void changeSides(double a, double b, double c) {
        judgeTriangle(a, b, c);
        if (this.judge) {
            this.a = a;
            this.b = b;
            this.c = c;
        } else {
            this.a = this.b = this.c = 0;
        }
    }
}

public class TestShapes {
    public static void main(String[] args) {
        Triangle tri = new Triangle(3, 4.2, 5);
        Lader lad = new Lader(3, 2, 5);
        Circle cir = new Circle(3.2);
        System.out.println("三角形周长为：" + tri.getPerimeter());
        System.out.println("三角形面积为：" + tri.getArea());
        System.out.println("将三角形三边长修改为3，1，1：");
        tri.changeSides(3, 1, 1);
        System.out.println("将三角形三边长修改为4，3.3，5.2，重新计算面积：");
        tri.changeSides(4, 3.3, 5.2);
        System.out.println("三角形周长为：" + tri.getPerimeter());
        System.out.println("三角形面积为：" + tri.getArea());
        System.out.println("梯形面积为：" + lad.getArea());
        System.out.println("圆形周长为：" + cir.getPerimeter());
        System.out.println("圆形面积为：" + cir.getArea());
    }
}
