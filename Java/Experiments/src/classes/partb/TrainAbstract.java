package classes.partb;

abstract class Shape {
    double area = 0;

    abstract void Area();

    abstract void printArea();
}

class Rectangle extends Shape {
    double width = 0;
    double length = 0;

    Rectangle(double width, double length) {
        this.length = length;
        this.width = width;
    }

    void Area() {
        this.area = this.length * this.width;
    }

    void printArea() {
        System.out.println("长为 " + this.length + " 宽为 " + this.width + " 的矩形的面积为： ");
        System.out.println(this.area);
    }
}

class Circle extends Shape {
    double radius = 0;

    Circle(double radius) {
        this.radius = radius;
    }

    void Area() {
        this.area = Math.PI * this.radius * this.radius;
    }

    void printArea() {
        System.out.println("半径为 " + this.radius + " 的圆的面积为： ");
        System.out.println(this.area);
    }
}

public class TrainAbstract {
    public static void main(String[] args) {
        Rectangle rect = new Rectangle(3, 4);
        Circle cir = new Circle(2);
        rect.Area();
        rect.printArea();
        cir.Area();
        cir.printArea();
    }
}
