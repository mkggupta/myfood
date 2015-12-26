/**
 * 
 */
package com.myfood.framework.exception;

import com.myfood.framework.exception.util.ErrorCodesEnum;




/**
 * @author Admin
 * 
 */
public class CDSystemException extends CDException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4197422745681241940L;

	/**
	 * @param errorCode
	 * @param e
	 */
	public CDSystemException(String errorCode, Exception e) {
		super(errorCode, e);
		super.setExceptionType(SYSTEM_EXCEPTION_TYPE);
	}

	/**
	 * @param errorCode
	 */
	public CDSystemException(String errorCode) {
		super(errorCode);
		super.setExceptionType(SYSTEM_EXCEPTION_TYPE);
	}

	/**
	 * @param errorCodeEnum
	 * @param e
	 */
	public CDSystemException(ErrorCodesEnum errorCodeEnum, Exception e) {
		super(errorCodeEnum, e);
		super.setExceptionType(SYSTEM_EXCEPTION_TYPE);
	}

	/**
	 * @param errorCodeEnum
	 */
	public CDSystemException(ErrorCodesEnum errorCodeEnum) {
		super(errorCodeEnum);
	}
}
