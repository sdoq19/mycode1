package priv.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhuzh on 2017-3-31.
 */
public class FastJsonTest {
    public static void main(String[] args) {

        TypeUtils.compatibleWithJavaBean = true;

        Map<String, Object> map = new HashMap<>();

        map.put("AS", "S");
        map.put("LL", "b");

        A a = new A();
        a.setAA("AA");
        a.setBB("BB");
        map.put("CC", a);

        Map<String, Object> map2 = new HashMap<>();

        map2.put("JJ", "JJ");
        map2.put("KK", "KK");
        map.put("DD", map2);

        System.out.println( JSONObject.toJSONString(map));
    }
}
