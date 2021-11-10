package applet;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppletTemperature extends Applet implements ActionListener {
    // 这一序列是IDE随机生成的
    private static final long serialVersionUID = 1830598611881336880L;
    TextField temperature1, temperature2;
    Label lab1, lab2;
    JButton start = new JButton("转换");

    public void init() {
        lab1 = new Label("摄氏温度：");
        lab2 = new Label("华氏温度：");
        temperature1 = new TextField(10);
        temperature2 = new TextField(10);
        add(lab1);
        add(temperature1);
        add(lab2);
        add(temperature2);
        temperature1.addActionListener(this);
        temperature2.addActionListener(this);
        // 给按钮加入监听后，点击按钮即会调用actionPerformed方法
        start.addActionListener(this);
        add(start);
    }

    public void actionPerformed(ActionEvent evt) {
        // 这一方法时对ActionListener接口中同名方法的实现
        if (temperature1.getText().equals("")) {
            String fah = temperature2.getText();
            double fah1 = Double.parseDouble(fah);
            double cel2 = (fah1 - 32) / 1.8;
            float cel3 = (float) (Math.round(cel2 * 100)) / 100;
            temperature1.setText(cel3 + "°C");
        } else {
            String cel = temperature1.getText();
            double cel1 = Double.parseDouble(cel);
            double fah2 = 1.8 * cel1 + 32;
            float fah3 = (float) (Math.round(fah2 * 100)) / 100;
            temperature2.setText(fah3 + "F");
        }
    }
}