package priv.test.submitOrder;


@SuppressWarnings("serial")
public class HZException extends Exception {

	public HZException(String errorMsg) {
		super(errorMsg);
	}
	
	public HZException(String message, Throwable cause) {
        super(message, cause);
    }
	public HZException(Throwable cause) {
		super(cause);
    }
}
