package cn.tedu.shop.controller.ex;
	/**
	 * 上傳文件大小異常
	 * @author User
	 *
	 */
public class FileSizeException extends FileUploadException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public FileSizeException() {
			super();
			
		}

		public FileSizeException(String message, Throwable cause, boolean enableSuppression,
				boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
			
		}

		public FileSizeException(String message, Throwable cause) {
			super(message, cause);
		}

		public FileSizeException(String message) {
			super(message);
		}

		public FileSizeException(Throwable cause) {
			super(cause);
		}
	
}
