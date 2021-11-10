package applications;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WesleyCalculator extends JFrame {
    private final Container container = this.getContentPane();
    private final JTextField textField = new JTextField();
    private final JMenu chooseMode = new JMenu("Standard");
    private final JMenuItem standard = new JMenuItem("Standard");
    private final JMenuItem alphaMode = new JMenuItem("Alpha Mode");
    private final JPanel cards = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel standardPanel = new JPanel();
    private final JPanel alphaPanel = new JPanel();
    private String strExpression = "";
    private final AlphaAPI alphaAPI = new AlphaAPI();

    private WesleyCalculator() {
        //设置窗口基本属性和文本输入框
        setBasic();
        //container.add(panel, BorderLayout.CENTER);
        cards.setLayout(cardLayout);
        container.add(cards, BorderLayout.CENTER);
        //设置StandardPanel
        setStandardPanel();
        setAlphaPanel();
        cards.add("Standard", standardPanel);
        cards.add("Alpha", alphaPanel);
        cards.updateUI();
    }

    private void setBasic() {
        //设置窗体的标题
        setTitle("Wesley's Calculator");
        //设置窗体的显示位置及大小
        setBounds(200, 100, 340, 545);
        //设置窗体关闭按钮的动作为退出
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout calculatorLayout = new BorderLayout();
        calculatorLayout.setHgap(5);
        calculatorLayout.setVgap(5);
        container.setLayout(calculatorLayout);
        //加入菜单
        JMenuBar myMenuBar = new JMenuBar();
        myMenuBar.setBackground(Color.white);
        setJMenuBar(myMenuBar);
        chooseMode.setFont(new Font("Lao UI", Font.BOLD, 14));
        myMenuBar.add(chooseMode);
        standard.setBackground(Color.white);
        standard.setFont(new Font("Lao UI", Font.BOLD, 13));
        chooseMode.add(standard);
        alphaMode.setBackground(Color.white);
        alphaMode.setFont(new Font("Lao UI", Font.BOLD, 13));
        chooseMode.add(alphaMode);
        //加入一个输入框
        textField.setHorizontalAlignment(SwingConstants.TRAILING);
        textField.setBorder(new BorderUIResource.LineBorderUIResource(Color.GRAY));
        textField.setFont(new Font("Lao UI", Font.BOLD, 28));
        textField.setPreferredSize(new Dimension(12, 115));
        container.add(textField, BorderLayout.NORTH);
        //菜单栏事件
        standard.addActionListener(actionEvent -> {
            textField.setText("");
            chooseMode.setText("Standard");
            cardLayout.show(cards, "Standard");
        });
        alphaMode.addActionListener(actionEvent -> {
            textField.setText("");
            chooseMode.setText("Alpha Mode");
            cardLayout.show(cards, "Alpha");
        });
    }

    private void setStandardPanel() {
        //标准模式下的Panel
        //按键的布局格式为网状
        GridLayout buttonLayout = new GridLayout(7, 5);
        buttonLayout.setHgap(4);
        buttonLayout.setVgap(3);
        standardPanel.setLayout(buttonLayout);
        //设置按键
        JButton[][] buttons = new JButton[7][5];
        String[][] buttonNames = {
                {"sin", "cos", "Del", "CE", "AC"},
                {"<html>&int", "mod", "|x|", "(", ")"},
                {"Dif", "<html>x<sup>y</sup>", "ln", "π", "e"},
                {"x", "1", "2", "3", "+"},
                {"y", "4", "5", "6", "-"},
                {"z", "7", "8", "9", "*"},
                {"n!", ".", "0", "/", "="}
        };
        for (int row = 0; row < buttonNames.length; row++) {
            for (int column = 0; column < buttonNames[row].length; column++) {
                buttons[row][column] = new JButton(buttonNames[row][column]);
                buttons[row][column].setFont(new Font("Lao UI", Font.BOLD, 15));
                buttons[row][column].setBackground(Color.white);
                buttons[row][column].setBorder(null);
                switch (buttonNames[row][column]) {
                    case "int":
                    case "sin":
                    case "cos":
                        convertOperator(buttons[row][column], buttonNames[row][column], "()", 4);
                        break;
                    case "|x|":
                        convertOperator(buttons[row][column], "abs", "()", 4);
                        break;
                    case "<html>x<sup>y</sup>":
                        convertOperator(buttons[row][column], "^", "", 1);
                        break;
                    case "<html>&int":
                        convertOperator(buttons[row][column], "int", "()", 4);
                        break;
                    case "Dif":
                        convertOperator(buttons[row][column], "d()/dx", "", 2);
                        break;
                    case "ln":
                        convertOperator(buttons[row][column], buttonNames[row][column], "()", 3);
                        break;
                    case "mod":
                        convertOperator(buttons[row][column], buttonNames[row][column], "", 3);
                        break;
                    case "n!":
                        convertOperator(buttons[row][column], "!", "", 1);
                        break;
                    case "Del":
                        buttons[row][column].addActionListener(actionEvent -> textField.setText(textField.getText().substring(0, textField.getText().length() - 1)));
                        break;
                    case "CE":
                        buttons[row][column].addActionListener(actionEvent -> {
                            if (textField.getText().contains("=")) {
                                textField.setText(textField.getText().substring(0, textField.getText().indexOf("=")));
                            }
                        });
                        break;
                    case "AC":
                        buttons[row][column].addActionListener(actionEvent -> textField.setText(""));
                        break;
                    case "=":
                        buttons[row][column].addActionListener(actionEvent -> getStandardResult());
                        break;
                    default:
                        convertOperator(buttons[row][column], buttonNames[row][column], "", 1);
                        break;
                }
                //将按钮添加到面板中
                standardPanel.add(buttons[row][column]);
            }
        }
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == 10 & Objects.equals(chooseMode.getText(), "Standard")) {
                    getStandardResult();
                }
            }
        });
        standardPanel.updateUI();
    }

    private void setAlphaPanel() {
        //Alpha模式下的Panel
        //布局格式为网状
        BorderLayout buttonLayout = new BorderLayout(5, 5);
        alphaPanel.setLayout(buttonLayout);
        alphaPanel.setBackground(Color.white);
        //设置提醒标签
        JLabel remind = new JLabel("        Get Whatever You Want");
        remind.setFont(new Font("Lao UI", Font.BOLD, 22));
        alphaPanel.add(remind, BorderLayout.NORTH);
        //设置结果显示区域
        JTextArea result = new JTextArea();
        //设置自动换行
        result.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(result);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        result.setBackground(Color.white);
        result.setEditable(false);
        result.setFont(new Font("Lao UI", Font.BOLD, 14));
        alphaPanel.add(scroll, BorderLayout.CENTER);
        //添加事件
        //文本框的回车监听
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == 10 & Objects.equals(chooseMode.getText(), "Alpha Mode")) {
                    result.setText(alphaAPI.fullResultQuery(textField.getText()));
                }
            }
        });
        alphaPanel.updateUI();
    }

    private void convertOperator(JButton button, String buttonName, String bracketForm, int moveCursorPosition) {
        //这里用于控制输入一个运算符或者数字后的展示形式及光标位置
        button.addActionListener(actionEvent -> {
            StringBuffer showExpression = new StringBuffer(textField.getText());
            int cursorPosition = textField.getCaretPosition();
            showExpression.insert(cursorPosition, buttonName + bracketForm);
            textField.setText(String.valueOf(showExpression));
            textField.setCaretPosition(cursorPosition + moveCursorPosition);
            textField.requestFocus();
        });
    }

    private void convertOperator() throws Exception {
        strExpression = strExpression.replace("sin", "Math.sin");
        strExpression = strExpression.replace("cos", "Math.cos");
        strExpression = strExpression.replace("tan", "Math.tan");
        strExpression = strExpression.replace("mod", "%");
        strExpression = strExpression.replace("abs", "Math.abs");
        strExpression = strExpression.replace("pow", "Math.pow");
        strExpression = strExpression.replace("ln", "Math.log");
        strExpression = strExpression.replace("e", "Math.E");
        strExpression = strExpression.replace("π", "Math.PI");
        if (strExpression.contains("^")) {
            throw new Exception("You Need Online Result");
        }
    }
    private String convertOperator(String stringToConvert) {
        while (stringToConvert.contains("^")) {
            Pattern firstParam = Pattern.compile("[a-z].*?\\(.*?\\)\\^" + "|" +
                    "[0-9]\\d*\\.?\\d*\\^" + "|" +
                    "\\(.*?\\)\\^");
            Pattern secondParam = Pattern.compile("\\^\\(.*?\\)" + "|" +
                    "\\^[a-z].*?\\(.*?\\)" + "|" +
                    "\\^[0-9]\\d*\\.?\\d*");
            Matcher matchFirst = firstParam.matcher(stringToConvert);
            Matcher matchSecond = secondParam.matcher(stringToConvert);
            ArrayList<String> firstParameter = new ArrayList<>();
            ArrayList<String> secondParameter = new ArrayList<>();
            while (matchFirst.find()) {
                firstParameter.add(matchFirst.group().substring(0, matchFirst.group().length() - 1));
            }
            while (matchSecond.find()) {
                secondParameter.add(matchSecond.group().substring(1));
            }
            for (int i = firstParameter.size() - 1; i >= 0; i--) {
                stringToConvert = stringToConvert.replace(firstParameter.get(i) + "^" + secondParameter.get(i), "pow(" + firstParameter.get(i) + "," + secondParameter.get(i) + ")");
            }
        }
        return stringToConvert;
    }
    private void getStandardResult() {
        try {
            getLocalResult();
        } catch (Exception e) {
            try {
                textField.setText(textField.getText() + "=" + alphaAPI.fastQuery(textField.getText()));
            } catch (Exception exception) {
                exception.printStackTrace();
                textField.setText("Modify the expression or try Alpha Mode");
            }
        }
    }

    private void getLocalResult() throws Exception {
        strExpression = textField.getText(0, textField.getText().length());
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine se = manager.getEngineByName("JavaScript");
        //用于将字符串转换为可计算的形式
        convertOperator();
        textField.setText(textField.getText() + "=" + se.eval(strExpression));
        textField.requestFocus();
    }

    public static void main(String[] args) throws Exception {
        WesleyCalculator frame = new WesleyCalculator();
        frame.setResizable(false);
        frame.setVisible(true);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
}

