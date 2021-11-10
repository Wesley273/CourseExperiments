import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloWorld {

    public static void main(String[] args) {
        HelloWorld h = new HelloWorld();
        int s=0;
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine se = manager.getEngineByName("JavaScript");
        try {
            s= (int) se.eval("2**3");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        System.out.println(s);
    }
}