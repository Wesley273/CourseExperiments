package applications;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;

public class PerpetualCalendar extends JFrame {
    private final JTextField getMonth = new JTextField(4);
    private final JTextField getYear = new JTextField(4);
    private final JLabel yearLabel = new JLabel("年");
    private final JLabel monthLabel = new JLabel("月");
    private final JButton lastMonth = new JButton("上个月");
    private final JButton nextMonth = new JButton("下个月");
    private final JButton check = new JButton("查 询");
    private final JButton[] dayButton = new JButton[42];
    private final String[] week = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    private final JButton[] weekButton = new JButton[7];
    private int year = 2020;
    private int month = 1;

    public PerpetualCalendar() {
        // 设置标题
        setTitle("Wesley's Calendar");
        setSize(440, 260);
        setLocation(500, 300);
        init();
    }

    private void init() {
        //设置查询部分
        yearLabel.setFont(new Font("宋体", Font.BOLD, 14));
        monthLabel.setFont(new Font("宋体", Font.BOLD, 14));
        check.setFont(new Font("宋体", Font.BOLD, 14));
        getYear.setFont(new Font("Times new Roman", Font.BOLD, 16));
        getMonth.setFont(new Font("Times new Roman", Font.BOLD, 16));
        lastMonth.setFont(new Font("宋体", Font.BOLD, 14));
        nextMonth.setFont(new Font("宋体", Font.BOLD, 14));
        JPanel chooseDate = new JPanel();
        chooseDate.add(lastMonth);
        chooseDate.add(getYear);
        chooseDate.add(yearLabel);
        chooseDate.add(getMonth);
        chooseDate.add(monthLabel);
        chooseDate.add(check);
        chooseDate.add(nextMonth);
        //设置日期显示部分
        JPanel datesPanel = new JPanel();
        datesPanel.setLayout(new GridLayout(7, 7, 0, 0));
        for (int i = 0; i < 7; i++) {
            weekButton[i] = new JButton(" ");
            weekButton[i].setText(week[i]);
            datesPanel.add(weekButton[i]);
            weekButton[i].setBorder(null);
        }
        //添加日期
        for (int i = 0; i < 42; i++) {
            dayButton[i] = new JButton(" ");
            datesPanel.add(dayButton[i]);
            dayButton[i].setBackground(Color.WHITE);
        }
        //设置日期显示
        JPanel showArea = new JPanel();
        //边界布局管理器
        showArea.setLayout(new BorderLayout());
        showArea.add(datesPanel, BorderLayout.SOUTH);
        showArea.add(chooseDate, BorderLayout.NORTH);
        getContentPane().add(showArea);
        //加入监听
        lastMonth.addActionListener(e -> {
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
        nextMonth.addActionListener(e -> {
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
        check.addActionListener(e -> {
            if (e.getSource() == check) {
                // 如果点击确定按钮就调用setDay()重新方法绘制按钮
                this.year = Integer.parseInt(getYear.getText());
                this.month = Integer.parseInt(getMonth.getText());
                printCalendar(year, month);
            }
        });
        getYear.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == 10) {
                    year = Integer.parseInt(getYear.getText());
                    month = Integer.parseInt(getMonth.getText());
                    printCalendar(year, month);
                }
            }
        });
        getMonth.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == 10) {
                    year = Integer.parseInt(getYear.getText());
                    month = Integer.parseInt(getMonth.getText());
                    printCalendar(year, month);
                }
            }
        });
    }

    private void printCalendar(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        int daysOfThis = calendar.getActualMaximum(Calendar.DATE);
        int firstDayOfThis = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.set(year, month - 2, 1);
        int daysOfLast = calendar.getActualMaximum(Calendar.DATE);
        for (int i = 0; i < firstDayOfThis - 1; i++) {
            dayButton[i].setText("[" + (daysOfLast - firstDayOfThis + i + 2) + "]");
        }
        for (int i = 0; i < daysOfThis; i++) {
            dayButton[firstDayOfThis - 1 + i].setText(String.valueOf(i + 1));
        }
        for (int i = firstDayOfThis - 1 + daysOfThis; i < dayButton.length; i++) {
            dayButton[i].setText("[" + (i - firstDayOfThis - daysOfThis + 2) + "]");
        }
    }

    public static void main(String[] args) throws Exception {
        PerpetualCalendar frame = new PerpetualCalendar();
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
