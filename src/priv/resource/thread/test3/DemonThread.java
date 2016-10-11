package priv.resource.thread.test3;

/**
 * 守护线程(用户线程执行完后，守护线程自动退出)
 */
public class DemonThread {

    public static void main(String[] args) {
        Thread t1 = new Thread(()-> {
                int i = 10;
                while(i>0) {
                    i--;
                    System.out.println("common:" + i);
                }
        });

        Thread t2 = new Thread(() -> {
            int i = 1000;
            while(i>0) {
                i--;
                System.out.println("demon:" + i);
            }
        });
        t2.setDaemon(true);

        t1.start();
        t2.start();


    }

}
