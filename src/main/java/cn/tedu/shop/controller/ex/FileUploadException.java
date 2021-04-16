package cn.tedu.shop.controller.ex;

public class FileUploadException extends RuntimeException {

	/**
	 *  上傳文件異常的基類
	 */
	private static final long serialVersionUID = 1L;

	public FileUploadException() {
		super();
		
	}

	public FileUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public FileUploadException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public FileUploadException(String message) {
		super(message);
		
	}

	public FileUploadException(Throwable cause) {
		super(cause);
		
	}
	
}
