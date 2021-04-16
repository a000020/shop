package cn.tedu.shop.controller.ex;

/**
 * 上傳文件類型異常
 * @author User
 *
 */
public class FileTypeException extends FileUploadException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileTypeException() {
		super();
		
	}

	public FileTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public FileTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileTypeException(String message) {
		super(message);
	}

	public FileTypeException(Throwable cause) {
		super(cause);
	}
	
}
