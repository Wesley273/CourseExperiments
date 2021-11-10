package applet;

import java.applet.*;
import java.awt.*;

public class MyFirstApplet extends Applet {
    public void paint(Graphics g) {
        String s1, s2;
        g.setColor(Color.blue);
        s1 = "这是一个Java Applet程序";
        s2 = "我改变了字体";
        g.drawString(s1, 20, 20);
        g.setColor(Color.red);
        g.setFont(new Font("宋体", Font.BOLD, 36));
        g.drawString(s2, 20, 100);
    }
}
