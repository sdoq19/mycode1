package priv.test.test;

import org.apache.commons.beanutils.BeanUtils;
import priv.utils.JsonUtil;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by zhuzh on 2017-5-4.
 */
public class BeanUtilTest {

    public static void main(String[] args) {
        A a = new A();
        a.setAA("1a");
        a.setBB("2a");

        B b = new B();
        try {
            BeanUtils.copyProperties(b, a);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(JsonUtil.objToJson(b));
    }

}
