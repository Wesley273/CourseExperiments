package applications;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DaysJudger {
    // 加throws Exception，意味着方法可能出现异常，但不必捕获它
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入一个年份");
        int days;
        String strYear = reader.readLine();
        System.out.println("请输入一个月份");
        String strMonth = reader.readLine();
        // parseInt方法是为了得到字符串代表的值
        int year = Integer.parseInt(strYear);
        int month = Integer.parseInt(strMonth);
        boolean flag = judgeLeap(year);
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
            case 2:
                if (flag) {
                    days = 29;
                    break;
                } else {
                    days = 28;
                    break;
                }
            default:
                days = 0;
                System.out.println("Error!");
                break;
        }
        if (days != 0) {
            System.out.println(year + "年" + month + "月份" + "的天数为" + days + "天");
        }
    }

    public static boolean judgeLeap(int year) {
        if (year % 400 == 0 || year % 4 == 0 && year % 100 != 0) {
            return (true);
        } else {
            return (false);
        }
    }
}