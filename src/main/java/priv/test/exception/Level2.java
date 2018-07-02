package priv.test.exception;

/**
 * Created by zhuzh on 2017-5-9.
 */
public class Level2 {
    public void gogo() {
        Level3 l3 = new Level3();
        // try {
            l3.gogo();
        // } catch (ApiException e) {
        //     // System.out.println("l2:exception:" + e);
        //     e.printStackTrace();
        // }
    }
}
