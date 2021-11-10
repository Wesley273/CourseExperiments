package classes.parta;

class Test {
    public static void main(String[] args) {
        //这里源代码没有赋值，无法成功构造
        A a = new A("Test This Code");
        a.print();
    }
}

class A {
    String s;

    A(String s) {
        this.s = s;
    }

    public void print() {
        //这里的s改为this.s更好
        System.out.println(s);
    }
}