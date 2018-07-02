package priv.test.test;

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
                System.out.println("1");
                LoggerUtil.error(ThrowTest.class, "error");
                System.out.println("2");
                hello.test();
                System.out.println("3");
            } catch (Exception e) {
                System.out.println("4");
                e.printStackTrace();
                try {
                    System.out.println("5");
                    throw new IOException();
                } catch (IOException e1) {
                    System.out.println("6");
                    e1.printStackTrace();
                }
            } finally {
                System.out.println("finally print");
            }
        }).start();

    }
}

class Hello {
    public void test() throws Exception {
        try {
            throw new IOException();
        }catch(Exception e) {
            System.out.println(e.toString());
            throw new IOException();
        } finally {
            System.out.println("aaa");
        }
    }
}
