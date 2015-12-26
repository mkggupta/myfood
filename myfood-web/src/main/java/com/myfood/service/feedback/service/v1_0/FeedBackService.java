package com.myfood.service.feedback.service.v1_0;

import java.util.List;

import com.myfood.dto.FeedBackInfo;
import com.myfood.dto.FeedBackReplyInfo;
import com.myfood.service.feedback.exception.FeedBackServiceException;

public interface FeedBackService {
	
	
	public List<FeedBackInfo> getFeedBackList(int pageLimit,int endLimit) throws FeedBackServiceException;
	
	public void savFeedBackInfo(FeedBackInfo feedBackInfo)throws FeedBackServiceException;
	
	public void savFeedBackReplyInfo(FeedBackReplyInfo feedBackReplyInfo)throws FeedBackServiceException;

	
}
