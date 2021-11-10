package arrays;

import java.util.Arrays;
import java.util.Random;

public class CopyArray {
    public static void main(String[] args) {
        //由于调用了该类中的非静态方法，需要先实例化
        CopyArray copied = new CopyArray();
        int[] sourceArray = new int[10];
        int[] targetArray = new int[10];
        Random randomNumber = new Random(1);
        for (int i = 0; i < 10; i++) {
            sourceArray[i] = randomNumber.nextInt(100);
        }
        //两个方法互为重载
        System.out.println("使用第一种方法");
        copied.copyArray(sourceArray, targetArray);
        System.out.println("\n使用第二种方法");
        copied.copyArray(sourceArray);
    }

    private void copyArray(int[] source, int[] target) {
        System.arraycopy(source, 0, target, 0, 10);
        System.out.println("源数组");
        for (int i : source) {
            System.out.print(i + " ");
        }
        System.out.println("\n目标数组");
        for (int i : target) {
            System.out.print(i + " ");
        }
    }

    public void copyArray(int[] source) {
        int[] target = Arrays.copyOf(source, 10);
        System.out.println("源数组");
        for (int i : source) {
            System.out.print(i + " ");
        }
        System.out.println("\n目标数组");
        for (int i : target) {
            System.out.print(i + " ");
        }
    }
}


