package cn.tedu.shop.service.ex;

public class EmptyException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyException() {
		super();
	}

	public EmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyException(String message) {
		super(message);
	}

	public EmptyException(Throwable cause) {
		super(cause);
	}
	
}
