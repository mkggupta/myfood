package com.myfood.rest.controller.user;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.myfood.common.util.ClientHeaderUtil;
import com.myfood.common.util.ControllerUtils;
import com.myfood.common.util.RequestProcessorUtil;
import com.myfood.dto.RegistrationInfo;
import com.myfood.framework.exception.util.ErrorCodesEnum;
import com.myfood.framework.success.SuccessCodesEnum;
import com.myfood.rest.constant.MyFoodWebConstant;
import com.myfood.service.user.exception.UserServiceFailedException;
import com.myfood.service.user.exception.UserServiceValidationFailedException;
import com.myfood.service.user.service.v1_0.UserService;






@Controller
@RequestMapping("/api/user")
public class RegLogController {
	
	private static Logger logger = LoggerFactory.getLogger(RegLogController.class);
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/pub/register", method = RequestMethod.POST)
	public ModelAndView registerLogin(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		 Long userId = null;
		 String jsonData = null;
		 Gson gson = new Gson();
		if(logger.isDebugEnabled()){
			logger.debug("RegLogController.registerLogin"+httpServletRequest);
			Enumeration<Object> headerNames = httpServletRequest.getHeaderNames();
			Map<String,String> requestParamMap = new HashMap<String, String>();
			while (headerNames.hasMoreElements()) {
				String headerName = (String) headerNames.nextElement();
				requestParamMap.put(headerName, httpServletRequest.getHeader(headerName));
			
			}
			logger.debug("RegLogController.create"+requestParamMap);
		}
		
		HashMap<String, Object>  requestMap = ControllerUtils.getRequestMapFromMultipart(httpServletRequest);
		try {
			if(null!=requestMap && requestMap.size()>0){	
				RegistrationInfo registrationInfo = new RegistrationInfo();
				Map<String, Object> clientParamMap = ClientHeaderUtil.extractClientParam(httpServletRequest);
				 RequestProcessorUtil.enrichRegistrationInfo(requestMap,registrationInfo,clientParamMap);
				 userId = userService.regLoginUser(registrationInfo,clientParamMap);
				 if(null!= userId && userId>0){
					 dataMap.put(MyFoodWebConstant.USERID, userId);
					 dataMap.put(MyFoodWebConstant.MESSAGE, SuccessCodesEnum.LOG_REG_SUCCESS.getSuccessMessage());
					 dataMap.put(MyFoodWebConstant.CODE, SuccessCodesEnum.LOG_REG_SUCCESS.getSuccessCode());	
				 }else if(null!= userId && userId==-1){
					 dataMap.put(MyFoodWebConstant.MESSAGE, SuccessCodesEnum.LOG_REG_ALREADY_EXIST.getSuccessMessage());
					 dataMap.put(MyFoodWebConstant.CODE, SuccessCodesEnum.LOG_REG_ALREADY_EXIST.getSuccessCode());	
				 }
			}
		} catch (UserServiceFailedException e) {
			logger.error("RegLogController()"+e.getLocalizedMessage(),e);
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		} catch (UserServiceValidationFailedException e) {
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, e.getErrorCode());
			dataMap.put(MyFoodWebConstant.CODE,ErrorCodesEnum.getErrorCodesEnum(e.getErrorCode()).getErrorMessage());
		}catch (Exception e) {
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorCode());
		}
		
		
		if(null!= userId){
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
		    jsonData = gson.toJson(dataMap);
		}else{
		if (dataMap.size() == 0) {
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		
		jsonData = gson.toJson(dataMap);
		}
		modelAndView.setViewName(MyFoodWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyFoodWebConstant.RESPONSE, jsonData);
		logger.debug("RegLogController.dataMap="+dataMap);
		return modelAndView;
	
	}

}
