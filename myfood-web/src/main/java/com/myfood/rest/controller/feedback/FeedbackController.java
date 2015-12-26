package com.myfood.rest.controller.feedback;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.myfood.common.business.util.BusinessControllerUtils;
import com.myfood.common.util.RequestProcessorUtil;
import com.myfood.dto.FeedBackInfo;
import com.myfood.dto.FeedBackReplyInfo;
import com.myfood.framework.exception.util.ErrorCodesEnum;
import com.myfood.framework.success.SuccessCodesEnum;
import com.myfood.rest.constant.MyFoodWebConstant;
import com.myfood.service.feedback.exception.FeedBackServiceException;
import com.myfood.service.feedback.service.v1_0.FeedBackService;

@Controller
@RequestMapping("/api/feedback")
public class FeedbackController {
	private static Logger logger = LoggerFactory.getLogger(FeedbackController.class);
	
	private FeedBackService feedBackService ;
	
	public void setFeedBackService(FeedBackService feedBackService) {
		this.feedBackService = feedBackService;
	}

	@RequestMapping(value = "/pub/getfbacks", method = RequestMethod.GET)
	public ModelAndView getFeedBacks(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		
	
		try {
			int startLimit = 0;
			int pageLimit = 10;
				
			 		List<FeedBackInfo> feedBackList = feedBackService.getFeedBackList(startLimit,pageLimit);
					 if(null!=feedBackList && feedBackList.size()>0){
						dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
						dataMap.put(MyFoodWebConstant.FEEDBACK,feedBackList);
					}else{
						dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
						dataMap.put(MyFoodWebConstant.MESSAGE, SuccessCodesEnum.NO_FEEDBACK_SUCCESS.getSuccessMessage());
						dataMap.put(MyFoodWebConstant.CODE, SuccessCodesEnum.NO_FEEDBACK_SUCCESS.getSuccessCode());	
					}
		
		} catch (FeedBackServiceException e) {
			logger.error("FeedbackController()"+e.getLocalizedMessage(),e);
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyFoodWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyFoodWebConstant.RESPONSE, jsonData);
		logger.debug("FeedbackController.getCategories.dataMap="+dataMap);
		return modelAndView;
	}
	
	@RequestMapping(value = "/pvt/savefbacks", method = RequestMethod.POST)
	public ModelAndView saveFeedBack(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		HashMap<String, Object>  requestMap = BusinessControllerUtils.getRequestMapFromMultipart(httpServletRequest);
		try {
			 if(null!=requestMap && requestMap.size()>0){	
					FeedBackInfo feedBackInfo = new FeedBackInfo();
					feedBackInfo = RequestProcessorUtil.enrichFeedBackInfoInfo(requestMap, feedBackInfo);
			 	    feedBackService.savFeedBackInfo(feedBackInfo);
					dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);	
			 }
		} catch (FeedBackServiceException e) {
			logger.error("FeedbackController().saveFeedBack"+e.getLocalizedMessage(),e);
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyFoodWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyFoodWebConstant.RESPONSE, jsonData);
		logger.debug("FeedbackController.saveFeedBack.dataMap="+dataMap);
		return modelAndView;
	}
	
	@RequestMapping(value = "/pvt/replyfbacks", method = RequestMethod.POST)
	public ModelAndView replyFeedBack(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		HashMap<String, Object>  requestMap = BusinessControllerUtils.getRequestMapFromMultipart(httpServletRequest);
		try {
			 if(null!=requestMap && requestMap.size()>0){	
				    FeedBackReplyInfo feedBackReplyInfo = new FeedBackReplyInfo();
				    feedBackReplyInfo = RequestProcessorUtil.enrichFeedBackReplyInfo(requestMap, feedBackReplyInfo);
			 	    feedBackService.savFeedBackReplyInfo(feedBackReplyInfo);
					dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);	
			 }
		} catch (FeedBackServiceException e) {
			logger.error("FeedbackController().replyFeedBack"+e.getLocalizedMessage(),e);
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.FEEDBACK_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyFoodWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyFoodWebConstant.RESPONSE, jsonData);
		logger.debug("FeedbackController.replyFeedBack.dataMap="+dataMap);
		return modelAndView;
	}
	

}
