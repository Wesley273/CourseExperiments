package applications;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class GUICalendar extends JFrame {
    private JButton lastMonth = new JButton("上个月");
    private JButton nextMonth = new JButton("下个月");
    private JButton check = new JButton("查询");
    private JLabel yearLabel = new JLabel("   年");
    private JLabel monthLabel = new JLabel("   月");
    private JTextArea getYear = new JTextArea();
    private JTextArea getMonth = new JTextArea();
    private JTextArea showArea = new JTextArea(10, 50);
    private JLabel weekLabel = new JLabel("星期日       星期一       星期二       星期三        星期四       星期五       星期六  ");
    private int year = 2020;
    private int month = 1;

    public GUICalendar() {
        Container container = this.getContentPane();
        //设置窗体的标题
        setTitle("Wesley's Calendar");
        //设置窗体的显示位置及大小
        setBounds(100, 100, 1000, 250);
        //设置窗体关闭按钮的动作为退出
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //放置两个Panel，布局方式为GridLayout
        BoxLayout panelLayout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(panelLayout);
        JPanel chooseDate = new JPanel();
        JPanel showCalendar = new JPanel();
        container.add(chooseDate);
        container.add(Box.createVerticalStrut(20));
        container.add(showCalendar);
        //在chooseDate中放置控件
        //创建网格布局管理器对象
        GridLayout chooseLayout = new GridLayout(1, 6);
        chooseLayout.setHgap(20);
        //设置容器采用网格布局管理器
        chooseDate.setLayout(chooseLayout);
        lastMonth.setFont(new Font("黑体", Font.BOLD, 20));
        chooseDate.add(lastMonth);
        getYear.setFont(new Font("黑体", Font.BOLD, 32));
        chooseDate.add(getYear);
        yearLabel.setFont(new Font("黑体", Font.BOLD, 28));
        chooseDate.add(yearLabel);
        getMonth.setFont(new Font("黑体", Font.BOLD, 32));
        chooseDate.add(getMonth);
        monthLabel.setFont(new Font("黑体", Font.BOLD, 28));
        chooseDate.add(monthLabel);
        nextMonth.setFont(new Font("黑体", Font.BOLD, 20));
        chooseDate.add(nextMonth);
        //在日历显示区域showCalendar放置控件
        FlowLayout showLayout = new FlowLayout(FlowLayout.CENTER, 0, 10);
        //设置容器采用FlowLayout
        showCalendar.setLayout(showLayout);
        weekLabel.setFont(new Font("黑体", Font.BOLD, 16));
        showCalendar.add(weekLabel);
        check.setFont(new Font("黑体", Font.BOLD, 20));
        showCalendar.add(check);
        showArea.setEditable(false);
        showArea.setOpaque(false);
        showArea.setBorder(null);
        showArea.setFont(new Font("黑体", Font.BOLD, 12));
        showCalendar.add(showArea);
        //下面加入监听
        this.addListener();
    }

    private void addListener() {
        check.addActionListener(actionEvent -> {
            //若未填写直接查询，则会直接输出程序内置的月份
            year = Integer.parseInt(getYear.getText());
            month = Integer.parseInt(getMonth.getText());
            printCalendar(year, month);
        });
        nextMonth.addActionListener(actionEvent -> {
            if (month == 12) {
                year++;
                month = 1;
            } else {
                month++;
            }
            printCalendar(year, month);
            getYear.setText(String.valueOf(year));
            getMonth.setText(String.valueOf(month));
        });
        lastMonth.addActionListener(actionEvent -> {
            if (month == 1) {
                year--;
                month = 12;
            } else {
                month--;
            }
            printCalendar(year, month);
            getYear.setText(String.valueOf(year));
            getMonth.setText(String.valueOf(month));
        });
    }

    private void printCalendar(int year, int month) {
        StringBuffer stringCalendar = new StringBuffer("");
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
        //输出上个月的后几天
        for (int i = 1; i < judgeFirstDay; i++) {
            stringCalendar.append(" [" + (daysOfLastMonth - judgeFirstDay + i) + "]            ");
        }
        //本月则依次输出即可
        for (int i = 1; i <= daysOfThisMonth; i++) {
            //找到第i天
            calendar.set(year, month - 1, i);
            //判断第i天是周几
            int judgeDay = calendar.get(Calendar.DAY_OF_WEEK);
            stringCalendar.append(" " + String.format("%-4d", i) + "            ");
            //每到周六则要换行
            if (judgeDay == 7) {
                stringCalendar.append("\n");
            }
        }
        //下月的初几天也要输出
        for (int i = 1; i < 7 - judgeLastDay + 1; i++) {
            stringCalendar.append(" [" + i + "]             ");
        }
        showArea.setText(String.valueOf(stringCalendar));
    }

    public static void main(String[] args) throws Exception {
        GUICalendar frame = new GUICalendar();
        frame.setVisible(true);
        frame.setResizable(false);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
}
