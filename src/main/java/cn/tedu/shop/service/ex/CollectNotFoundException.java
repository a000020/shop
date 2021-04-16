package cn.tedu.shop.service.ex;

public class CollectNotFoundException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CollectNotFoundException() {
		super();
	}

	public CollectNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CollectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CollectNotFoundException(String message) {
		super(message);
	}

	public CollectNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
