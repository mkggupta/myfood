package com.myfood.service.cat.exception;

import com.myfood.framework.exception.CDBusinessException;
import com.myfood.framework.exception.util.ErrorCodesEnum;

public class CatServiceException extends CDBusinessException{
	public CatServiceException(ErrorCodesEnum errorCodeEnum) {
		super(errorCodeEnum);
		// TODO Auto-generated constructor stub
	}

	
	public CatServiceException(ErrorCodesEnum errorCodeEnum, Exception e) {
		super(errorCodeEnum, e);
		// TODO Auto-generated constructor stub
	}

	public CatServiceException(String errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	public CatServiceException(String errorCode, Exception e) {
		super(errorCode, e);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
