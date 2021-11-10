package arrays;

import java.util.Arrays;
import java.util.Random;

public class SortArray {
    public static void main(String[] args) {
        int[] arr = new int[10];
        int sum = 0;
        Random randomNumber = new Random(1);
        for (int i = 0; i < 10; i++) {
            arr[i] = randomNumber.nextInt(100);
            sum += arr[i];
            System.out.println("第" + (i + 1) + "个数为 " + arr[i]);
        }
        System.out.println("所有数之和为：" + sum);
        getMaxAndMin(arr);
        Arrays.sort(arr);
        System.out.print("排序结果：");
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private static void getMaxAndMin(int[] array) {
        int max = 0, maxPosition = 1, min = 2147483647, minPosition = 1, count = 0;
        for (int i : array) {
            count++;
            if (i >= max) {
                maxPosition = count;
                max = i;
            }
            if (i <= min) {
                min = i;
                minPosition = count;
            }
        }
        System.out.println("最大值为：" + max + " 是第" + maxPosition + "个元素");
        System.out.println("最小值为：" + min + " 是第" + minPosition + "个元素");
    }
}
