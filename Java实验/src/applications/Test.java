package applications;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ConvertOperator {
    String expressionToConvert;

    ConvertOperator(String expressionToConvert) {
        this.expressionToConvert = expressionToConvert;
        convertBracket();
    }

    private String convertBracket() {
        Pattern firstBracket = Pattern.compile("\\(.*\\)\\^");
        Pattern secondBracket = Pattern.compile("\\^\\(.*\\)");
        Matcher matchFirstBracket = firstBracket.matcher(expressionToConvert);
        Matcher matchSecondBracket = secondBracket.matcher(expressionToConvert);
        while (matchFirstBracket.find()) {
            String replaceBracket = matchFirstBracket.group().substring(0, matchFirstBracket.group().length() - 1);
            if (replaceBracket.charAt(0) == '(') {
                replaceBracket = replaceBracket.substring(1);
            }
            expressionToConvert = expressionToConvert.replace(replaceBracket, this.convertOperator(replaceBracket));
            matchFirstBracket = firstBracket.matcher(expressionToConvert);
        }
        while (matchSecondBracket.find()) {
            String replaceBracket = matchSecondBracket.group().substring(1);
            expressionToConvert = expressionToConvert.replace(replaceBracket, this.convertOperator(replaceBracket));
            matchSecondBracket = firstBracket.matcher(expressionToConvert);
        }
        System.out.println(convertOperator(expressionToConvert));
        return convertOperator(expressionToConvert);
    }

    private String convertOperator(String expressionToConvert) {
        while (expressionToConvert.contains("^")) {
            Pattern firstParam = Pattern.compile("[a-z]*?\\(.*?\\)\\^" + "|" +
                    "\\d\\d*\\.?\\d*\\^");
            Pattern secondParam = Pattern.compile("\\^.*[a-z]*\\(.*?\\)" + "|" +
                    "\\^.*\\d\\d*\\.?\\d*");
            Matcher matchFirst = firstParam.matcher(expressionToConvert);
            Matcher matchSecond = secondParam.matcher(expressionToConvert);
            while (matchFirst.find()) {
                while (matchSecond.find()) {
                    //System.out.println(i + "------------------");
                    String replaceFirst = matchFirst.group().substring(0, matchFirst.group().length() - 1);
                    //System.out.println(replaceFirst);
                    String replaceSecond = matchSecond.group().substring(1);
                    //System.out.println(replaceSecond);
                    expressionToConvert = expressionToConvert.replace(replaceFirst + "^" + replaceSecond, "pow(" + replaceFirst + "," + replaceSecond + ")");
                    //System.out.println(expressionToConvert);
                }
            }
        }
        expressionToConvert = expressionToConvert.replace("sin", "Math.sin");
        expressionToConvert = expressionToConvert.replace("cos", "Math.cos");
        expressionToConvert = expressionToConvert.replace("tan", "Math.tan");
        expressionToConvert = expressionToConvert.replace("mod", "%");
        expressionToConvert = expressionToConvert.replace("abs", "Math.abs");
        expressionToConvert = expressionToConvert.replace("pow", "Math.pow");
        expressionToConvert = expressionToConvert.replace("ln", "Math.log");
        expressionToConvert = expressionToConvert.replace("e", "Math.E");
        expressionToConvert = expressionToConvert.replace("π", "Math.PI");
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine se = manager.getEngineByName("JavaScript");
        try {
            return String.valueOf(se.eval(expressionToConvert));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return null;
    }
}

public class Test {
    public static void main(String[] args) {
        ConvertOperator convert = new ConvertOperator("2^(abs(3)+2^abs(1)+2^2)");
        System.out.println(convert.expressionToConvert);
    }
}
