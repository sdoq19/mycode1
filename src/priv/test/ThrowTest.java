package priv.test;

import priv.utils.LoggerUtil;

import java.io.IOException;

/**
 * Created by zhuzh on 2017-3-3.
 */
public class ThrowTest {
    public static void main(String[] args) {

        new Thread(() -> {
            Hello hello = new Hello();
            try {
                LoggerUtil.error(ThrowTest.class, "error");
                hello.test();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }
}

class Hello {
    public void test() throws Exception {
        throw new IOException();
    }
}
