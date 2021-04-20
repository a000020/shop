package cn.tedu.shop.service.ex;

public class EmailNotMatchException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailNotMatchException() {
		super();
	}

	public EmailNotMatchException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmailNotMatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmailNotMatchException(String message) {
		super(message);
	}

	public EmailNotMatchException(Throwable cause) {
		super(cause);
	}
	
}
