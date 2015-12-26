package com.myfood.rest.controller.user;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.myfood.common.business.util.BusinessControllerUtils;
import com.myfood.common.util.RequestProcessorUtil;
import com.myfood.dto.UserInfo;
import com.myfood.framework.exception.util.ErrorCodesEnum;
import com.myfood.rest.constant.MyFoodWebConstant;
import com.myfood.service.user.exception.UserServiceFailedException;
import com.myfood.service.user.exception.UserServiceValidationFailedException;
import com.myfood.service.user.service.v1_0.UserService;







@Controller
@RequestMapping("/api/usr")
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/pvt/profile/{userId}", method = RequestMethod.GET)
	public ModelAndView getProfile(@PathVariable String userId,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		
		 String jsonData = null;
		 Gson gson = new Gson();
		try {
			if(StringUtils.isNotBlank(userId)){
				
				UserInfo userInfo = userService.getUserProfile(Long.parseLong(userId));
				 if(null!= userInfo){
					 dataMap.put(MyFoodWebConstant.USER_DETAIL, userInfo);
					
				 }else{
					 logger.debug("getProfile() nothing");
					   dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
						dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorMessage());
						dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorCode());
				 }
			}
		} catch (UserServiceFailedException e) {
			logger.error("getProfile()"+e.getLocalizedMessage(),e);
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorCode());
			
		} catch (UserServiceValidationFailedException e) {
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE,ErrorCodesEnum.getErrorCodesEnum(e.getErrorCode()).getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, e.getErrorCode());
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
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorCode());
			
		}
		
		jsonData = gson.toJson(dataMap);
		}
		modelAndView.setViewName(MyFoodWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyFoodWebConstant.RESPONSE, jsonData);
		logger.debug("getProfile.dataMap="+dataMap);
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/pvt/updateprofile", method = RequestMethod.POST)
	public ModelAndView updateProfile(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		HashMap<String, Object>  requestMap = BusinessControllerUtils.getRequestMapFromMultipart(httpServletRequest);
		try {
			if(null!=requestMap && requestMap.size()>0){	
				UserInfo userInfo = new UserInfo();
				RequestProcessorUtil.enrichUserVO(requestMap, userInfo, null);
				if(userInfo.getId()>0){
					userInfo = userService.updateUser(userInfo);
					 if(null!= userInfo){
						 dataMap.put(MyFoodWebConstant.USER_DETAIL, userInfo);
						
					 }else{
						 logger.debug("updateProfile() ");
						   dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
							dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorMessage());
							dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorCode());
					}
				}
			}
			
		} catch (UserServiceFailedException e) {
			logger.error("updateProfile()"+e.getLocalizedMessage(),e);
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION.getErrorCode());
			
		}catch (Exception e) {
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION.getErrorCode());
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyFoodWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyFoodWebConstant.RESPONSE, jsonData);
		logger.debug("updateProfile.dataMap="+dataMap);
		return modelAndView;
	}
	

}
