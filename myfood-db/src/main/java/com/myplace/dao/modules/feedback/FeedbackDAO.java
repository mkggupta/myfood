package com.myfood.dao.modules.feedback;

import java.util.List;

import com.myfood.dao.exception.DataAccessFailedException;
import com.myfood.dao.exception.DataUpdateFailedException;
import com.myfood.dto.FeedBackInfo;
import com.myfood.dto.FeedBackReplyInfo;

public interface FeedbackDAO {
	public void saveFeedbackInfo(FeedBackInfo feedBackInfo) throws DataUpdateFailedException;
	public void saveFeedbackReplyInfo(FeedBackReplyInfo feedBackReplyInfo) throws DataUpdateFailedException;
	public List<FeedBackInfo> getFeedbackInfoList (int startLimit, int endLimit) throws DataAccessFailedException;
}
