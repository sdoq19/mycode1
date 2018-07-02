/**
 *
 */
package priv.test.exception;

/**
 * @author Administrator
 */
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 568276109798489436L;

    private Integer code;

    public ApiException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }

}
