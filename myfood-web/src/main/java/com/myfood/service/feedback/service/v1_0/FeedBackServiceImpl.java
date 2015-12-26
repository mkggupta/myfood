package com.myfood.service.feedback.service.v1_0;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myfood.dao.exception.DataAccessFailedException;
import com.myfood.dao.exception.DataUpdateFailedException;
import com.myfood.dao.modules.feedback.FeedbackDAO;
import com.myfood.dto.FeedBackInfo;
import com.myfood.dto.FeedBackReplyInfo;
import com.myfood.framework.exception.util.ErrorCodesEnum;
import com.myfood.service.feedback.exception.FeedBackServiceException;

public class FeedBackServiceImpl implements FeedBackService {
	private static Logger logger = LoggerFactory.getLogger(FeedBackServiceImpl.class);
	private FeedbackDAO feedbackDAO ;

	

	public void setFeedbackDAO(FeedbackDAO feedbackDAO) {
		this.feedbackDAO = feedbackDAO;
	}


	public List<FeedBackInfo> getFeedBackList(int startLimit,int endLimit) throws FeedBackServiceException{
		
		try {
			return feedbackDAO.getFeedbackInfoList(startLimit, endLimit);
		} catch (DataAccessFailedException e) {
			throw new FeedBackServiceException(ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION);
		}
		
		
	}
	
	public void savFeedBackInfo(FeedBackInfo feedBackInfo)throws FeedBackServiceException{

		try {
			feedbackDAO.saveFeedbackInfo(feedBackInfo);
		} catch (DataUpdateFailedException e) {
			throw new FeedBackServiceException(ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION);
		}
	}
	
	public void savFeedBackReplyInfo(FeedBackReplyInfo feedBackReplyInfo)throws FeedBackServiceException{
		

		try {
			feedbackDAO.saveFeedbackReplyInfo(feedBackReplyInfo);
		} catch (DataUpdateFailedException e) {
			throw new FeedBackServiceException(ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION);
		}
	}

	

	
}
