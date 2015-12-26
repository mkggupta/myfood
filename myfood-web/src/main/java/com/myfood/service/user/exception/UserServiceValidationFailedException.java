/**
 * 
 */
package com.myfood.service.user.exception;

import com.myfood.framework.exception.CDBusinessException;
import com.myfood.framework.exception.util.ErrorCodesEnum;



/**
 * @author Manish
 * 
 */
public class UserServiceValidationFailedException extends CDBusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7459681051856273489L;

	/**
	 * @param errorCode
	 * @param e
	 */
	public UserServiceValidationFailedException(String errorCode, Exception e) {
		super(errorCode, e);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCode
	 */
	public UserServiceValidationFailedException(String errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCodeEnum
	 * @param e
	 */
	public UserServiceValidationFailedException(ErrorCodesEnum errorCodeEnum, Exception e) {
		super(errorCodeEnum, e);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errorCodeEnum
	 */
	public UserServiceValidationFailedException(ErrorCodesEnum errorCodeEnum) {
		super(errorCodeEnum);
		// TODO Auto-generated constructor stub
	}

}
