package com.myfood.rest.controller.advt;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myfood.common.constant.MyFoodConstant;
import com.myfood.common.util.ControllerUtils;
import com.myfood.common.util.RequestProcessorUtil;
import com.myfood.dto.AdvertisementDTO;
import com.myfood.dto.AdvtTemplate;
import com.myfood.framework.exception.util.ErrorCodesEnum;
import com.myfood.framework.success.SuccessCodesEnum;
import com.myfood.rest.constant.MyFoodWebConstant;
import com.myfood.service.advt.exception.AdvtServiceException;
import com.myfood.service.advt.service.v1_0.AdvtService;
import com.google.gson.Gson;




@Controller
@RequestMapping("/api/advt")
public class AdvtController {
	
	private static Logger logger = LoggerFactory.getLogger(AdvtController.class);
	private AdvtService advtService ;
	
	public void setAdvtService(AdvtService advtService) {
		this.advtService = advtService;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createAdvt(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		if(logger.isDebugEnabled()){
			logger.debug("AdvtController.create"+httpServletRequest);
			Enumeration<Object> headerNames = httpServletRequest.getHeaderNames();
			Map<String,String> requestParamMap = new HashMap<String, String>();
			while (headerNames.hasMoreElements()) {
				String headerName = (String) headerNames.nextElement();
				requestParamMap.put(headerName, httpServletRequest.getHeader(headerName));
			
			}
			logger.debug("AdvtController.create"+requestParamMap);
		}
		
		HashMap<String, Object>  requestMap = ControllerUtils.getRequestMapFromMultipart(httpServletRequest);
		try {
			if(null!=requestMap && requestMap.size()>0){	
				AdvertisementDTO advertisementDTO = new AdvertisementDTO();
				advertisementDTO = RequestProcessorUtil.enrichAdvertisementDTO(requestMap,advertisementDTO);
				Long advtId = advtService.createAdvt(advertisementDTO);
				if(null!=advtId && advtId>0){
					dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
					dataMap.put(MyFoodWebConstant.ADVTCODE, advtId);
				}else{
					dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
					dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.ADVT_SERVICE_FAILED_EXCEPTION.getErrorMessage());
					dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.ADVT_SERVICE_FAILED_EXCEPTION.getErrorCode());
					
				}
			}
		} catch (AdvtServiceException e) {
			logger.error("AdvtController()"+e.getLocalizedMessage(),e);
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.ADVT_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.ADVT_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyFoodWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyFoodWebConstant.RESPONSE, jsonData);
		logger.debug("AdvtController.dataMap="+dataMap);
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/getTemplate", method = RequestMethod.GET)
	public ModelAndView getAdvtTemplate(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		if(logger.isDebugEnabled()){
			logger.debug("AdvtController.create"+httpServletRequest);
			Enumeration<Object> headerNames = httpServletRequest.getHeaderNames();
			Map<String,String> requestParamMap = new HashMap<String, String>();
			while (headerNames.hasMoreElements()) {
				String headerName = (String) headerNames.nextElement();
				requestParamMap.put(headerName, httpServletRequest.getHeader(headerName));	
			}
			logger.debug("AdvtController.create"+requestParamMap);
		}
		try {
			Byte type =(null!=httpServletRequest.getParameter(MyFoodWebConstant.TYPE))?Byte.parseByte(httpServletRequest.getParameter(MyFoodWebConstant.TYPE)):0;
				List<AdvtTemplate>  advtList = advtService.getAdvtTemplate(type);
				 if(null!=advtList && advtList.size()>0){
					dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
					dataMap.put(MyFoodWebConstant.ADVT_TEMPLATE, advtList);
				}else{
					dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
					dataMap.put(MyFoodWebConstant.MESSAGE, SuccessCodesEnum.NO_ADVT_TEMPLATE_SUCCESS.getSuccessMessage());
					dataMap.put(MyFoodWebConstant.CODE, SuccessCodesEnum.NO_ADVT_TEMPLATE_SUCCESS.getSuccessCode());	
				}
		
		} catch (AdvtServiceException e) {
			logger.error("AdvtController()"+e.getLocalizedMessage(),e);
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.ADVT_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.ADVT_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyFoodWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyFoodWebConstant.RESPONSE, jsonData);
		logger.debug("AdvtController.dataMap="+dataMap);
		return modelAndView;
	}
	
	@RequestMapping(value = "/getAdvtList", method = RequestMethod.GET)
	public ModelAndView getAdvtList(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		Long userId =0l;
		if(logger.isDebugEnabled()){
			logger.debug("AdvtController.create"+httpServletRequest);
			Enumeration<Object> headerNames = httpServletRequest.getHeaderNames();
			Map<String,String> requestParamMap = new HashMap<String, String>();
			while (headerNames.hasMoreElements()) {
				String headerName = (String) headerNames.nextElement();
				requestParamMap.put(headerName, httpServletRequest.getHeader(headerName));	
			}
			logger.debug("AdvtController.getAdvtList"+requestParamMap);
		}
		try {
				userId = ControllerUtils.extractUserIdFromHeader(httpServletRequest);
				dataMap = advtService.getAdvtList(userId, 0, MyFoodWebConstant.DEFAULT_ADVT_LIMIT, false);
				 if(null!=dataMap && dataMap.size()>0){
					dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
				}else{
					dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
					dataMap.put(MyFoodWebConstant.MESSAGE, SuccessCodesEnum.NO_ADVT_SUCCESS.getSuccessMessage());
					dataMap.put(MyFoodWebConstant.CODE, SuccessCodesEnum.NO_ADVT_SUCCESS.getSuccessCode());	
				}
		
		} catch (AdvtServiceException e) {
			logger.error("AdvtController()"+e.getLocalizedMessage(),e);
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.ADVT_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.ADVT_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyFoodWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyFoodWebConstant.RESPONSE, jsonData);
		logger.debug("AdvtController.getAdvtList.dataMap="+dataMap);
		return modelAndView;
	}
	
	@RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
	public ModelAndView changeAdvtStatus(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		Long userId= 0l,advtCode=0l;
		Byte status = 2;
		if(logger.isDebugEnabled()){
			logger.debug("AdvtController.create"+httpServletRequest);
			Enumeration<Object> headerNames = httpServletRequest.getHeaderNames();
			Map<String,String> requestParamMap = new HashMap<String, String>();
			while (headerNames.hasMoreElements()) {
				String headerName = (String) headerNames.nextElement();
				requestParamMap.put(headerName, httpServletRequest.getHeader(headerName));	
			}
			logger.debug("AdvtController.create"+requestParamMap);
		}
		HashMap<String, Object> requestMap = ControllerUtils.getRequestMapFromMultipart(httpServletRequest);
		try {
			if(null!=requestMap.get(MyFoodConstant.USERID)){
				userId = Long.parseLong(requestMap.get(MyFoodConstant.USERID).toString());
			}
			if(null!=requestMap.get(MyFoodConstant.ADVTCODE)){
				advtCode = Long.parseLong(requestMap.get(MyFoodConstant.ADVTCODE).toString());
			}
			if(null!=requestMap.get(MyFoodConstant.STATUS)){
				status = Byte.parseByte(requestMap.get(MyFoodConstant.STATUS).toString());
			}
			if(userId>0 && advtCode>0){
				 advtService.changeAdvtStatus(userId,advtCode, status);
				 dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
				 dataMap.put(MyFoodWebConstant.MESSAGE, SuccessCodesEnum.ADVT_STATUS_CHANGE_SUCCESS.getSuccessMessage());
				 dataMap.put(MyFoodWebConstant.CODE, SuccessCodesEnum.ADVT_STATUS_CHANGE_SUCCESS.getSuccessCode());
			}else{
				 dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
				 dataMap.put(MyFoodWebConstant.MESSAGE, SuccessCodesEnum.NO_ADVT_STATUS_SUCCESS.getSuccessMessage());
				 dataMap.put(MyFoodWebConstant.CODE, SuccessCodesEnum.NO_ADVT_STATUS_SUCCESS.getSuccessCode());
			}
		} catch (AdvtServiceException e) {
			logger.error("AdvtController()"+e.getLocalizedMessage(),e);
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.ADVT_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.ADVT_SERVICE_FAILED_EXCEPTION.getErrorCode());	
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyFoodWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyFoodWebConstant.RESPONSE, jsonData);
		logger.debug("AdvtController.changeAdvtStatus.dataMap="+dataMap);
		return modelAndView;
	}
	
}
