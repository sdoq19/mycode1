package priv.test.exception;

/**
 * Created by zhuzh on 2017-5-9.
 */
public class Level3 {
    public void gogo() throws ApiException {
        throw new ApiException(1,"2");
    }
}
