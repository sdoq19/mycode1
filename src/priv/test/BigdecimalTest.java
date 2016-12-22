package priv.test;

/**
 * Created by admin on 2016/11/22.
 */
public class BigdecimalTest {
    public static void main(String[] args) {
        Float f =  Float.valueOf("9.86");
        f =f*100;
        System.out.println(f);
        Long result = f.longValue();
        System.out.println(result);
    }
}
