package applications;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Calendar;

public class WesleyCalendar {
    private void printCalendar(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        //参数需要月份从0开始记，故-1
        calendar.set(year, month - 1, 1);
        //下面要寻找该月第一天是周几
        //1：周日 2：周一 3：周二 4：周三 5：周四 6：周五 7：周六
        int judgeFirstDay = calendar.get(Calendar.DAY_OF_WEEK);
        //获取该月天数
        int daysOfThisMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        //获取该月最后一天是周几
        calendar.set(year, month - 1, daysOfThisMonth);
        int judgeLastDay = calendar.get(Calendar.DAY_OF_WEEK);
        //获取上月天数
        calendar.set(year, month - 2, 1);
        int daysOfLastMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        //下面作输出操作
        //输出表头
        System.out.println("-------------------  " + year + " 年 " + month + " 月 " + " -------------------");
        System.out.println("星期日\t星期一\t星期二\t星期三\t星期四\t星期五\t星期六");
        //输出上个月的后几天
        for (int i = 1; i < judgeFirstDay; i++) {
            System.out.print("[" + (daysOfLastMonth - judgeFirstDay + i) + "]    ");
        }
        //本月则依次输出即可
        for (int i = 1; i <= daysOfThisMonth; i++) {
            //找到第i天
            calendar.set(year, month - 1, i);
            //判断第i天是周几
            int judgeDay = calendar.get(Calendar.DAY_OF_WEEK);
            System.out.print(String.format("%-2d", i) + "      ");
            //每到周六则要换行
            if (judgeDay == 7) {
                System.out.println();
            }
        }
        //下月的初几天也要输出
        for (int i = 1; i < 7 - judgeLastDay + 1; i++) {
            System.out.print("[" + i + "]     ");
        }
        //输出结尾
        System.out.println("\n----------------------------------------------------");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        WesleyCalendar calendar = new WesleyCalendar();
        System.out.println("----------------------My Calender---------------------");
        System.out.println("请输入年份：");
        int year = Integer.parseInt(reader.readLine());
        System.out.println("请输入月份：");
        int month = Integer.parseInt(reader.readLine());
        calendar.printCalendar(year, month);
    }
}
