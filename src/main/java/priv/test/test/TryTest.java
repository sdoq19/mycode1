package priv.test.test;

/**
 * Created by zhuzh on 2018-5-29.
 */
public class TryTest {

    public static void main(String[] args) {

        try {
            System.out.println("->" + 1 / 0);
        } catch(Exception e ) {

        }

        int a = 0;
        System.out.println("a=" +a);
        try {
            a = 1;
            System.out.println("a=" + a);
            try {
                a = 2;
                System.out.println("a=" + a);
                int b = a/0;
            } catch (Exception e1) {
                try {
                    a = 3;
                    System.out.println("a=" + a);
                    int b = a/0;
                } catch (Exception e2) {
                    a = 4;
                    System.out.println("a=" + a);
                    return;
                }
                a = 5;
                System.out.println("a=" + a);
            }
        } catch (Exception e) {
            a = 6;
            System.out.println("a=" + a);
        } finally {
            a = 7;
            System.out.println("a=" + a);
        }
    }
}
