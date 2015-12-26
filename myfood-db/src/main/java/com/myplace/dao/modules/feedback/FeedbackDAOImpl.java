package com.myfood.dao.modules.feedback;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myfood.dao.constants.FeedbackConstant;
import com.myfood.dao.exception.DataAccessFailedException;
import com.myfood.dao.exception.DataUpdateFailedException;
import com.myfood.dao.modules.base.AbstractDBManager;
import com.myfood.dto.FeedBackInfo;
import com.myfood.dto.FeedBackReplyInfo;
import com.myfood.framework.exception.util.ErrorCodesEnum;

public class FeedbackDAOImpl extends AbstractDBManager implements FeedbackDAO {
	
	private static Logger logger = LoggerFactory.getLogger(FeedbackDAOImpl.class);
	
	public void saveFeedbackInfo(FeedBackInfo feedBackInfo) throws DataUpdateFailedException{
		
		try {
			logger.debug("feedBackInfo=="+feedBackInfo);
			 sqlMapClient_.insert(FeedbackConstant.INSERT_FEEDBACK_INFO,feedBackInfo);
		
			}catch(SQLException e){
				logger.error("Exception in saveFeedbackInfo : " + e.getMessage());
				throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}
			
	}
	public void saveFeedbackReplyInfo(FeedBackReplyInfo feedBackReplyInfo) throws DataUpdateFailedException{

		try {
			logger.debug("feedBackReplyInfo=="+feedBackReplyInfo);
			 sqlMapClient_.insert(FeedbackConstant.INSERT_FEEDBACK_REPLY_INFO,feedBackReplyInfo);
		
			}catch(SQLException e){
				logger.error("Exception in saveFeedbackReplyInfo : " + e.getMessage());
				throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}
	}
	
	@SuppressWarnings("unchecked")
	public List<FeedBackInfo> getFeedbackInfoList (int startLimit, int endLimit) throws DataAccessFailedException{
		try {
			logger.debug("getFeedbackInfoList=="+startLimit+"endLimit="+endLimit);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("startLimit", startLimit);
			params.put("endLimit", endLimit);
			return (List<FeedBackInfo>) sqlMapClient_.queryForList(FeedbackConstant.GET_FEEDBACK_INFO,params);
		
			}catch(SQLException e){
				logger.error("Exception in getFeedbackInfoList : " + e.getMessage());
				throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}
	}

}
