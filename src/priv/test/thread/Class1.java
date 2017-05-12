package priv.test.thread;

/**
 * Created by zhuzh on 2017-5-10.
 */
public class Class1 {
    private static Class1 class1 = null;

    private String name1;

    private Class1(String name1) {
        this.name1 = name1;
    }

    public static synchronized Class1 getInstance() {
        if(class1 == null) {
            class1 = new Class1("0");
        }
        return class1;
    }

    public String method(String threadName) {
        Class2 c2 = Class2.getInstance();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String c2Name  = c2.method(threadName);
        if(!threadName.equals(c2Name)) {
            System.out.println("------------!!!!!!!!!!!!!!!!!!!!!!!!!!!-----------------------");
        }
        return threadName + "," +  c2Name;
    }
}
