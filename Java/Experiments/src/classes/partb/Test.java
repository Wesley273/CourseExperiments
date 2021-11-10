package classes.partb;

class A {
    int sum, num1, num2;

    public A() {
        num1 = 10;
        num2 = 20;
        sum = 0;
    }

    void sum1() {
        sum = num1 + num2;
        System.out.println("sum=" + num1 + "+" + num2 + "=" + sum);
    }

    void sum2(int n) {
        num1 = n;
        sum = num1 + num2;
        System.out.println("sum=" + num1 + "+" + num2 + "=" + sum);
    }
}

class B extends A {
    int num2;

    public B() {
        num2 = 200;
    }

    void sum2() {
        sum = num1 + num2;
        System.out.println("sum=" + num1 + "+" + num2 + "=" + sum);
    }

    void sum2(int n) {
        num1 = n;
        sum = num1 + num2;
        System.out.println("sum=" + num1 + "+" + num2 + "=" + sum);
    }

    void sum3(int n) {
        super.sum2(n);
        System.out.println("sum=" + num1 + "+" + num2 + "=" + sum);
    }
}

public class Test {
    public static void main(String[] args) {
        B m = new B();
        //这里的sum1()是从父类继承而来的,未进行重写和重载，调用了父类中的属性和方法
        m.sum1();
        //这里对sum2()进行了重载
        m.sum2();
        //此方法是对父类里方法的重写
        m.sum2(50);
        //这是一个新方法
        m.sum3(50);
    }
}
