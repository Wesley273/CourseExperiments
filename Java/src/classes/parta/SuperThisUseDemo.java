package classes.parta;

class Person {
    int weight;

    Person() {
        weight = 50;
    }

    void printPerson() {
        System.out.println("Person.printPerson()");
    }
}

class ZhangSan extends Person {
    int weight;

    ZhangSan() {
        //这里其实不写也会自动调用父类的构造方法
        super();
        weight = 500;
    }

    void printPerson() {
        System.out.println("ZhangSan.printPerson()");
    }

    void superThisUseDemo() {
        int weight;
        weight = 5000;
        super.printPerson();
        printPerson();
        //这里的weight是Person类中的成员
        System.out.println("super.weight=" + super.weight);
        //这里的weight是ZhangSan类中的成员
        System.out.println("this.weight=" + this.weight);
        //这个weight是函数体内的局部变量
        System.out.println("weight=" + weight);
    }
}

public class SuperThisUseDemo {
    public static void main(String[] args) {
        ZhangSan zhangsan = new ZhangSan();
        zhangsan.superThisUseDemo();
    }
}
