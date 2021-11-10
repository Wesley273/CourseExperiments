package arrays;

public class OperateString {
    public static void main(String[] args) {
        String name = "My name is NetworkCrazy";
        System.out.println("字符串的长度为： " + name.length());
        System.out.println("第一个字符为：'" + name.charAt(0) + "'");
        System.out.println("最后一个字符为：'" + name.charAt(name.length() - 1) + "'");
        //substring()索引时包括起始索引，不包括结尾索引
        System.out.println("截取字符串：\"" + name.substring(11, 23) + "\"");
        System.out.println("寻找'e'最后出现的位置： " + name.lastIndexOf("e"));
    }
}
