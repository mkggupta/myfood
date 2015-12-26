package com.myfood.rest.controller.stats;

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

import com.myfood.common.util.ControllerUtils;
import com.myfood.framework.exception.util.ErrorCodesEnum;
import com.myfood.framework.success.SuccessCodesEnum;
import com.myfood.rest.constant.MyFoodWebConstant;
import com.myfood.service.advt.exception.AdvtServiceException;
import com.myfood.service.advt.service.v1_0.AdvtService;
import com.google.gson.Gson;

@Controller
@RequestMapping("/api/stats")
public class StatsController {
	private static Logger logger = LoggerFactory.getLogger(StatsController.class);
	
	private AdvtService advtService ;
	
	public void setAdvtService(AdvtService advtService) {
		this.advtService = advtService;
	}
	
	@RequestMapping(value = "/getAdvtStats", method = RequestMethod.GET)
	public ModelAndView getAdvtStats(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		Long userId =0l;
		if(logger.isDebugEnabled()){
			logger.debug("StatsController.getAdvtStats"+httpServletRequest);
			Enumeration<Object> headerNames = httpServletRequest.getHeaderNames();
			Map<String,String> requestParamMap = new HashMap<String, String>();
			while (headerNames.hasMoreElements()) {
				String headerName = (String) headerNames.nextElement();
				requestParamMap.put(headerName, httpServletRequest.getHeader(headerName));	
			}
			logger.debug("StatsController.getAdvtStats"+requestParamMap);
		}
		try {
				userId = ControllerUtils.extractUserIdFromHeader(httpServletRequest);
				Long advtCode =(null!=httpServletRequest.getParameter(MyFoodWebConstant.ADVTCODE))?Long.parseLong(httpServletRequest.getParameter(MyFoodWebConstant.ADVTCODE)):0;
				if(userId>0){
					dataMap = advtService.getAdvtStatsList(userId,advtCode);
					 if(null!=dataMap && dataMap.size()>0){
						dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
					}else{
						dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
						dataMap.put(MyFoodWebConstant.MESSAGE, SuccessCodesEnum.NO_ADVT_SUCCESS.getSuccessMessage());
						dataMap.put(MyFoodWebConstant.CODE, SuccessCodesEnum.NO_ADVT_SUCCESS.getSuccessCode());	
					}
				}else{
					dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
					dataMap.put(MyFoodWebConstant.MESSAGE, SuccessCodesEnum.NO_ADVT_SUCCESS.getSuccessMessage());
					dataMap.put(MyFoodWebConstant.CODE, SuccessCodesEnum.NO_ADVT_SUCCESS.getSuccessCode());	
				}
		
		} catch (AdvtServiceException e) {
			logger.error("StatsController()"+e.getLocalizedMessage(),e);
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.ADVT_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.ADVT_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyFoodWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyFoodWebConstant.RESPONSE, jsonData);
		logger.debug("StatsController.getAdvtStats.dataMap="+dataMap);
		return modelAndView;
	}
	
	

}
