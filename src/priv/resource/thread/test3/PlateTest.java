package priv.resource.thread.test3;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产者消费者，A生产，B消费，若盘子中无产品，A进入就绪，B进入等待；反之。。。
 */
public class PlateTest {
    List<Object> eggList = new ArrayList<Object>();

    // 取鸡蛋
    public synchronized Object getEgg() {
        while(eggList.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Object egg = eggList.get(0);
        eggList.clear();
        notifyAll(); // 用notify()可能会造成死锁
        System.out.println("拿到鸡蛋-->");
        return egg;
    }

    // 放鸡蛋
    public synchronized void putEgg(Object egg) {
        while(eggList.size()>0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        eggList.add(egg);
        notifyAll();
        System.out.println("拿走鸡蛋");
    }

    static class AddThread implements Runnable {
        private PlateTest plateTest;
        private Object egg = new Object();
        public AddThread(PlateTest plate) {
            this.plateTest = plate;
        }
        @Override
        public void run() {
            plateTest.putEgg(egg);
        }
    }
    static class GetThread implements Runnable {
        private PlateTest plateTest;
        public GetThread(PlateTest plate) {
            this.plateTest = plate;
        }
        @Override
        public void run() {
            plateTest.getEgg();
        }
    }

    public static void main(String[] args) {
        PlateTest plate = new PlateTest();

        for(int i=0;i<100;i++) {
            new Thread(new AddThread(plate)).start();
            new Thread(new GetThread(plate)).start();
        }
    }
}





