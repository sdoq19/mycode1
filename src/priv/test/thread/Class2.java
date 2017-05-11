package priv.test.thread;

/**
 * Created by zhuzh on 2017-5-10.
 */
public class Class2 {
    private static Class2 class2 = null;

    private String name;

    private Class2(String name) {
        this.name = name;
    }

    public static synchronized Class2 getInstance() {
        if(class2 == null) {
            class2 = new Class2("0");
        }
        return class2;
    }

    public String method(String threadName) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return threadName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
