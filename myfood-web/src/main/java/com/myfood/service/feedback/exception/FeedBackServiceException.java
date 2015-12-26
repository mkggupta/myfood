package com.myfood.service.feedback.exception;

import com.myfood.framework.exception.CDBusinessException;
import com.myfood.framework.exception.util.ErrorCodesEnum;

public class FeedBackServiceException extends CDBusinessException{
	public FeedBackServiceException(ErrorCodesEnum errorCodeEnum) {
		super(errorCodeEnum);
		// TODO Auto-generated constructor stub
	}

	
	public FeedBackServiceException(ErrorCodesEnum errorCodeEnum, Exception e) {
		super(errorCodeEnum, e);
		// TODO Auto-generated constructor stub
	}

	public FeedBackServiceException(String errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	public FeedBackServiceException(String errorCode, Exception e) {
		super(errorCode, e);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
