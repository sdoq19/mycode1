package priv.test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhuzh on 2017-9-1.
 */
public class test1 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("bbb", "bbb");
        new test1().add(map);
        System.out.println(map);
    }

    public void add(Map<String, String> map) {
        map.put("aaa", "aaa");
    }
}
