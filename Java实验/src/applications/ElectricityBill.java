package applications;

import javax.swing.*;

public class ElectricityBill {
    public static void main(String[] args) {
        String strElectricity;
        double bill = 0;
        strElectricity = JOptionPane.showInputDialog("��������ʵ�ʵ��õ���");
        double electricity = Double.parseDouble(strElectricity);
        if (electricity >= 0 && electricity <= 240) {
            // ������ʹ��float���ͣ���Ҫǿ������ת��
            bill = electricity * 0.55;
        } else if (electricity > 240 && electricity <= 540) {
            bill = 240 * 0.55 + (electricity - 240) * 0.7;
        } else if (electricity > 540) {
            bill = 240 * 0.55 + (540 - 240) * 0.7 + (electricity - 540) * 0.9;
        }
        JOptionPane.showMessageDialog(null, "����Ҫ���ɵ��Ϊ�� " + String.format("%.2f", bill) + "Ԫ", "���",
                JOptionPane.PLAIN_MESSAGE);
    }
}