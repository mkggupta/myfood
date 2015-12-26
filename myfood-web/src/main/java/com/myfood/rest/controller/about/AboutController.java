package com.myfood.rest.controller.about;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.myfood.framework.exception.util.ErrorCodesEnum;
import com.myfood.framework.success.SuccessCodesEnum;
import com.myfood.rest.constant.MyFoodWebConstant;
import com.myfood.service.about.exception.AboutServiceException;
import com.myfood.service.about.service.v1_0.AboutService;


@Controller
@RequestMapping("/api/about")
public class AboutController {
	private static Logger logger = LoggerFactory.getLogger(AboutController.class);
	
	private AboutService aboutService ;

	public void setAboutService(AboutService aboutService) {
		this.aboutService = aboutService;
	}

	@RequestMapping(value = "/pub/getAbt", method = RequestMethod.GET)
	public ModelAndView getAbout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
	
		try {
					String aboutUs = aboutService.getAboutUs();
					 if(StringUtils.isNotBlank(aboutUs)){
						dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
						dataMap.put(MyFoodWebConstant.ABOUT_US,aboutUs);
					}else{
						dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
						dataMap.put(MyFoodWebConstant.MESSAGE, SuccessCodesEnum.NO_ABOUT_SUCCESS.getSuccessMessage());
						dataMap.put(MyFoodWebConstant.CODE, SuccessCodesEnum.NO_ABOUT_SUCCESS.getSuccessCode());	
					}
				
		
		} catch (AboutServiceException e) {
			logger.error("AboutController()"+e.getLocalizedMessage(),e);
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.ABOUT_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.ABOUT_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyFoodWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyFoodWebConstant.RESPONSE, jsonData);
		logger.debug("AboutController.dataMap="+dataMap);
		return modelAndView;
	}
	

}
