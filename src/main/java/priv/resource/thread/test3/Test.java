package priv.resource.thread.test3;

/**
 * 单线程多线程效率测试
 * Created by zhuzh on 2016-6-24.
 */
public class Test {

    public static long count = 10000000000L;

    public static void main(String[] args) {
        concurrency();
        serial();
    }

    // concurrency
    private static void concurrency() {

        long begin = System.currentTimeMillis();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a+=5;
                }
            }
        });
        int b = 0;
        for (long i = 0; i < count; i++) {
            b+=5;
        }
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        long end = System.currentTimeMillis();
        System.out.println("多线程操作：耗时：" + (end - begin) + ",b=" + b);
    }

    // serial
    private static void serial() {
        long begin = System.currentTimeMillis();

        int a = 0;
        for (long i = 0; i < count; i++) {
            a+=5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b+=5;
        }
        long end = System.currentTimeMillis();
        System.out.println("单线程操作：耗时：" + (end - begin) + ",a=" + a + ",b=" + b);

    }

}


//多线程操作：耗时：4993,b=-1539607552
//单线程操作：耗时：9746,a=-1539607552,b=-1539607552

