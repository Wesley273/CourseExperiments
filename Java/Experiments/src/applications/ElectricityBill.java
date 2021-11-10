package applications;

import javax.swing.*;

public class ElectricityBill {
    public static void main(String[] args) {
        String strElectricity;
        double bill = 0;
        strElectricity = JOptionPane.showInputDialog("请输入您实际的用电量");
        double electricity = Double.parseDouble(strElectricity);
        if (electricity >= 0 && electricity <= 240) {
            // 这里若使用float类型，需要强制类型转换
            bill = electricity * 0.55;
        } else if (electricity > 240 && electricity <= 540) {
            bill = 240 * 0.55 + (electricity - 240) * 0.7;
        } else if (electricity > 540) {
            bill = 240 * 0.55 + (540 - 240) * 0.7 + (electricity - 540) * 0.9;
        }
        JOptionPane.showMessageDialog(null, "您需要缴纳电费为： " + String.format("%.2f", bill) + "元", "电费",
                JOptionPane.PLAIN_MESSAGE);
    }
}