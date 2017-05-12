package priv.test.thread;

/**
 * Created by zhuzh on 2017-5-11.
 */
public class MainThread {
    public static void main(String[] args) {
        Class1 c1 = Class1.getInstance();
        int i=0;
        while(i<10000) {
            i++;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    c1.method(Thread.currentThread().getName());
                }
            }).start();
        }
    }
}
