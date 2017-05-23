package priv.resource.io.file;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 从字符串中截取出电话号码
 *
 * @author zcr
 */
public class CheckIfIsPhoneNumber {
    private static int a = 0;

    public static String isPhoneRegexp() {
        return ".*串号调试,detail partner.*";
    }


    /**
     * 从dataStr中获取出所有的电话号码（固话和移动电话），将其放入Set
     *
     * @param dataStr  待查找的字符串
     * @param phoneSet dataStr中的电话号码
     */
    public static void getPhoneNumFromStrIntoSet(String dataStr, Set<String> phoneSet, StringBuffer content) {
        //获得固定电话和移动电话的正则表达式
        String regexp = isPhoneRegexp();

        // System.out.println("Regexp = " + regexp);

        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(dataStr);

        //找与该模式匹配的输入序列的下一个子序列
        while (matcher.find()) {
            //获取到之前查找到的字符串，并将其添加入set中
            // phoneSet.add(a++ + ":" + matcher.group());
            content.append(matcher.group() + "\n");
        }
        //System.out.println(phoneSet);
    }
}