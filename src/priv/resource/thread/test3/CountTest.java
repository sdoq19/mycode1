package priv.resource.thread.test3;

/**
 * runnable在Thread外创建，只有一个runnable对象
 */
public class CountTest {

    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            Count count = new Count();

            @Override
            public void run() {
                count.count();
            }
        };
        int times = 10;
        while (times -- >= 0) {
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }

}

class Count {
    private int num;

    public void count() {
        //int num = 0;
        for(int i=1;i<=1000;i++) {
            num += i;
        }
        System.out.println(Thread.currentThread().getName()+":"+num);
    }
}
