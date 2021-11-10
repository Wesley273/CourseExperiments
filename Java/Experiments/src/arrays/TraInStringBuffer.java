package arrays;

public class TraInStringBuffer {
    public static void main(String[] args) {
        String name = "My name is NetworkCrazy";
        StringBuffer stringInBuffer = new StringBuffer("Happy new year!");
        System.out.println("存放在缓存中的字符串：\"" + stringInBuffer + "\"");
        System.out.println("将索引为3处的字符'" + stringInBuffer.charAt(3) + "'替换为's'");
        stringInBuffer.setCharAt(3, 's');
        System.out.println("替换后的字符串：\"" + stringInBuffer + "\"");
        System.out.println("在索引为3处的字符'" + stringInBuffer.charAt(3) + "'前插入\"ch\"");
        stringInBuffer.insert(3, "ch");
        System.out.println("插入后的字符串：\"" + stringInBuffer + "\"");
        stringInBuffer.append(" ").append(name);
        System.out.println("对字符串进行追加：\"" + stringInBuffer + "\"");
    }
}
