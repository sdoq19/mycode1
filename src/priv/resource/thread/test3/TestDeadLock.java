package priv.resource.thread.test3;

/**
 * 死锁测试
 * Created by zhuzh on 2016-6-24.
 */
public class TestDeadLock {

    public static Object a = "";
    public static Object b = "";

    public static void main(String[] args) {
        deadLock();
    }

    private static void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (a) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (b) {
                        System.out.println("1");
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (b) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (a) {
                        System.out.println("2");
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }


}



