package classes.partb;

import java.util.Random;

interface IShapeArea {

    double getArea();

    double getPerimeter();
}

class RectangleB implements IShapeArea {
    double width = 0;
    double length = 0;
    double area = 0;
    double perimeter = 0;

    RectangleB() {
    }

    //由于同包中有Rectangle类，故命名为了RectangleB
    RectangleB(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double getArea() {
        this.area = this.width * this.length;
        return this.area;
    }

    public double getPerimeter() {
        this.perimeter = 2 * (this.width + this.length);
        return perimeter;
    }
}

class CircleB implements IShapeArea {
    double radius = 0;
    double area = 0;
    double perimeter = 0;

    CircleB() {
    }

    CircleB(double radius) {
        this.radius = radius;
    }

    //实现接口的方法必须为public
    public double getArea() {
        this.area = Math.PI * this.radius * this.radius;
        return this.area;
    }

    public double getPerimeter() {
        this.perimeter = 2 * Math.PI * radius;
        return this.perimeter;
    }
}

class Square extends RectangleB {
    double side = 0;

    Square(double side) {
        this.side = side;
    }

    public double getArea() {
        this.area = this.side * this.side;
        return this.area;
    }

    public double getPerimeter() {
        this.perimeter = 4 * this.side;
        return perimeter;
    }
}

public class TrainInterface {
    public static void main(String[] args) {
        TrainInterface train = new TrainInterface();
        double[][] sum = new double[3][2];
        RectangleB[] rect = new RectangleB[2];
        Square[] sqr = new Square[3];
        CircleB[] cir = new CircleB[4];
        //随机生成图形并求其面积、周长和
        train.getSum(rect);
        train.getSum(cir);
        train.getSum(sqr);
    }

    private void getSum(RectangleB[] rectangle) {
        System.out.println("-----------------矩形-----------------");
        Random rand = new Random();
        double[][] sum = new double[1][2];
        for (int i = 0; i < rectangle.length; i++) {
            rectangle[i] = new RectangleB(10 * rand.nextDouble(), 10 * rand.nextDouble());
            double area = rectangle[i].getArea();
            double perimeter = rectangle[i].getPerimeter();
            System.out.println("第 " + (i + 1) + " 个矩形：长为： " + String.format("%.2f", rectangle[i].length) + " 宽为： " + String.format("%.2f", rectangle[i].width));
            System.out.println("           面积为： " + String.format("%.2f", area) + " 周长为： " + String.format("%.2f", perimeter));
            sum[0][0] += area;
            sum[0][1] += perimeter;
        }
        System.out.println("这 " + rectangle.length + " 个矩形的面积和为： " + String.format("%.2f", sum[0][0]));
        System.out.println("这 " + rectangle.length + " 个矩形的周长和为： " + String.format("%.2f", sum[0][1]));
    }

    private void getSum(CircleB[] circle) {
        System.out.println("-----------------圆形-----------------");
        Random rand = new Random();
        double[][] sum = new double[1][2];
        for (int i = 0; i < circle.length; i++) {
            circle[i] = new CircleB(10 * rand.nextDouble());
            double area = circle[i].getArea();
            double perimeter = circle[i].getPerimeter();
            System.out.println("第 " + (i + 1) + " 个圆形：半径为： " + String.format("%.2f", circle[i].radius));
            System.out.println("           面积为： " + String.format("%.2f", area) + " 周长为： " + String.format("%.2f", perimeter));
            sum[0][0] += area;
            sum[0][1] += perimeter;
        }
        System.out.println("这 " + circle.length + " 个圆形的面积和为： " + String.format("%.2f", sum[0][0]));
        System.out.println("这 " + circle.length + " 个圆形的周长和为： " + String.format("%.2f", sum[0][1]));
    }

    private void getSum(Square[] square) {
        System.out.println("-----------------正方形-----------------");
        Random rand = new Random();
        double[][] sum = new double[1][2];
        for (int i = 0; i < square.length; i++) {
            square[i] = new Square(10 * rand.nextDouble());
            double area = square[i].getArea();
            double perimeter = square[i].getPerimeter();
            System.out.println("第 " + (i + 1) + " 个正方形：边长为： " + String.format("%.2f", square[i].side));
            System.out.println("             面积为： " + String.format("%.2f", area) + " 周长为： " + String.format("%.2f", perimeter));
            sum[0][0] += area;
            sum[0][1] += perimeter;
        }
        System.out.println("这 " + square.length + " 个正方形的面积和为： " + String.format("%.2f", sum[0][0]));
        System.out.println("这 " + square.length + " 个正方形的周长和为： " + String.format("%.2f", sum[0][1]));
    }
}
