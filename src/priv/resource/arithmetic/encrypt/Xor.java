package priv.resource.arithmetic.encrypt;

/**
 * Created by zhuzh on 2017-6-12.
 */
public class Xor {
    public static void main(String[] args) {
        String password = "中秋快乐。。。";//要加密或者解密的字符串
        char[] array    = password.toCharArray();//获取字符数组
        for (int i = 0; i < array.length; i++)//遍历字符数组
        {
            array[i] = (char) (array[i] ^ 20140908);//对每个数组元素进行异或运算，异或的值可以自己选择
        }
        System.out.println("加密或者解密结果如下：");
        System.out.println(new String(array));//输出加密或者解密结果


        long a = 3100001234567890l;
        long b = 20170614171810l;
        System.out.println(a^b^b);
    }
}
