package classes.partb;

import java.lang.*;

interface Common {
    double getSpeed(double A, double B, double C);
}

class Car implements Common {
    public double getSpeed(double A, double B, double C) {
        return (A * B / C);
    }
}

class Plane implements Common {
    public double getSpeed(double A, double B, double C) {
        return (A + B + C);
    }
}

public class ComputeTime {
    public static void main(String[] args) {
        double speed, time;
        //下面寻找类时需要使用到包名
        String packageName = ComputeTime.class.getPackage().getName() + ".";
        //这里是运行程序时传进来的参数
        System.out.println("交通工具类型: " + args[0]);
        System.out.println(" 参数A: " + args[1]);
        System.out.println(" 参数B: " + args[2]);
        System.out.println(" 参数C: " + args[3]);
        double A = Double.parseDouble(args[1]);
        double B = Double.parseDouble(args[2]);
        double C = Double.parseDouble(args[3]);
        try {
            //Class.forName("") 的作用是要求JVM查找并加载指定的类
            //加载类后再使用newInstance得到实例化对象
            //这里要求这些类必须属于Common这个接口
            Common newObject = (Common) Class.forName(packageName + args[0]).newInstance();
            speed = newObject.getSpeed(A, B, C);
            time = 1000 / speed;
            System.out.println("平均速度为: " + speed + " km/h");
            System.out.println("运行时间为：" + time + " 小时");
        } catch (ReflectiveOperationException e) {
            System.out.println("尚未编写" + args[0] + "类的程序！");
        }
    }
}
