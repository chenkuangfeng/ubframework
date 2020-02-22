package com.ubsoft.framework.core.exception;
public class ConfigException extends ComException{
	public static final int FORMMETA_ERROR = 0;
	public static final int FDM_ERROR = 1;
	
	public ConfigException(int code, String originalMessage) {
		super(MIN_ERROR_CODE_CONFIGURATION + code, originalMessage);
	}

	/**
	 * Constructor with error code and original exception
	 * @param code
	 * @param cause
	 */
	public ConfigException(int code, Throwable cause) {

		super(MIN_ERROR_CODE_CONFIGURATION, cause);
		
	}

	/**
	 * Constructor with message and original exception
	 * @param message
	 * @param cause
	 */
	public ConfigException(String message, Throwable cause) {

		super(message, cause);
	}

}
