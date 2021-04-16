package cn.tedu.shop.service.ex;

public class PasswordNotMatchException extends ServiceException {

	/**
	 * 用戶密碼不匹配異常
	 */
	private static final long serialVersionUID = 1L;

	public PasswordNotMatchException() {
		super();
		
	}

	public PasswordNotMatchException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public PasswordNotMatchException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public PasswordNotMatchException(String message) {
		super(message);
		
	}

	public PasswordNotMatchException(Throwable cause) {
		super(cause);
		
	}
	
}
