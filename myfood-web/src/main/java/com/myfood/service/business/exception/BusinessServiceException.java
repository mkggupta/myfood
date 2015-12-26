package com.myfood.service.business.exception;

import com.myfood.framework.exception.CDBusinessException;
import com.myfood.framework.exception.util.ErrorCodesEnum;

public class BusinessServiceException extends CDBusinessException{
	public BusinessServiceException(ErrorCodesEnum errorCodeEnum) {
		super(errorCodeEnum);
		// TODO Auto-generated constructor stub
	}

	
	public BusinessServiceException(ErrorCodesEnum errorCodeEnum, Exception e) {
		super(errorCodeEnum, e);
		// TODO Auto-generated constructor stub
	}

	public BusinessServiceException(String errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	public BusinessServiceException(String errorCode, Exception e) {
		super(errorCode, e);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
