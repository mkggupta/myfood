/**
 * 
 */
package com.myfood.framework.exception;

import com.myfood.framework.exception.util.ErrorCodesEnum;



/**
 * @author Admin
 * 
 */
public class CDBusinessException extends CDException {

	/**
	 * @param errorCode
	 * @param e
	 */
	public CDBusinessException(String errorCode, Exception e) {
		super(errorCode, e);
		super.setExceptionType(BUSINESS_EXCEPTION_TYPE);
	}

	/**
	 * @param errorCode
	 */
	public CDBusinessException(String errorCode) {
		super(errorCode);
		super.setExceptionType(BUSINESS_EXCEPTION_TYPE);
	}

	/**
	 * @param errorCodeEnum
	 * @param e
	 */
	public CDBusinessException(ErrorCodesEnum errorCodeEnum, Exception e) {
		super(errorCodeEnum, e);
		super.setExceptionType(BUSINESS_EXCEPTION_TYPE);
	}

	/**
	 * @param errorCodeEnum
	 */
	public CDBusinessException(ErrorCodesEnum errorCodeEnum) {
		super(errorCodeEnum);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1146757635940220423L;

}
