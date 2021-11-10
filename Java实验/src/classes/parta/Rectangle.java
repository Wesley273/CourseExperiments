package classes.parta;

public class Rectangle {
    private double width = 0;
    private double height = 0;
    double area = 0;
    double perimeter = 0;

    private Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    private double getArea() {
        this.area = this.height * this.width;
        return this.area;
    }

    private double getPerimeter() {
        this.perimeter = (this.height + this.width) * 2;
        return this.perimeter;
    }

    public static void main(String[] args) {
        Rectangle rect = new Rectangle(3, 4);
        System.out.println("矩形面积为：" + rect.getArea());
        System.out.println("矩形周长为：" + rect.getPerimeter());
    }
}
