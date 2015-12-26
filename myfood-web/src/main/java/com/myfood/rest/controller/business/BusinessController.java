package com.myfood.rest.controller.business;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.myfood.common.constant.MyFoodBusinessConstant;
import com.myfood.common.constant.MyFoodConstant;
import com.myfood.common.util.ControllerUtils;
import com.myfood.common.util.RequestProcessorUtil;
import com.myfood.dto.BusinessInfo;
import com.myfood.framework.exception.util.ErrorCodesEnum;
import com.myfood.framework.success.SuccessCodesEnum;
import com.myfood.rest.constant.MyFoodWebConstant;
import com.myfood.service.business.exception.BusinessServiceException;
import com.myfood.service.business.service.v1_0.BusinessService;


@Controller
@RequestMapping("/api/business")
public class BusinessController {
	
	private static Logger logger = LoggerFactory.getLogger(BusinessController.class);
	private BusinessService businessService ;
	
	
	
	public void setBusinessService(BusinessService businessService) {
		this.businessService = businessService;
	}


	@RequestMapping(value = "/pvt/create", method = RequestMethod.POST)
	public ModelAndView createBusiness(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		if(logger.isDebugEnabled()){
			logger.debug("BusinessController.create"+httpServletRequest);
			Enumeration<Object> headerNames = httpServletRequest.getHeaderNames();
			Map<String,String> requestParamMap = new HashMap<String, String>();
			while (headerNames.hasMoreElements()) {
				String headerName = (String) headerNames.nextElement();
				requestParamMap.put(headerName, httpServletRequest.getHeader(headerName));
			
			}
			logger.debug("BusinessController.create"+requestParamMap);
		}
		
		HashMap<String, Object>  requestMap = BusinessControllerUtils.getRequestMapFromMultipart(httpServletRequest);
		try {
			if(null!=requestMap && requestMap.size()>0){	
				BusinessInfo businessInfo = new BusinessInfo();
				businessInfo = RequestProcessorUtil.enrichBusinessInfo(requestMap,businessInfo);
				Long businessId = businessService.createBusiness(businessInfo);
				if(null!=businessId && businessId>0){
					dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
					dataMap.put(MyFoodWebConstant.BUSINESS_ID, businessId);
				}else{
					dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
					dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorMessage());
					dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorCode());
					
				}
			}
		} catch (BusinessServiceException e) {
			logger.error("BusinessController()"+e.getLocalizedMessage(),e);
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyFoodWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyFoodWebConstant.RESPONSE, jsonData);
		logger.debug("BusinessController.dataMap="+dataMap);
		return modelAndView;
	}
	
	@RequestMapping(value = "/pvt/my/{userId}", method = RequestMethod.GET)
	public ModelAndView getMyBusinessList(@PathVariable String userId,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		 String jsonData = null;
		 Gson gson = new Gson();
		try {
			if(StringUtils.isNotBlank(userId)){	
				
				List<BusinessInfo> businessList = businessService.getMyBusinessList(Long.parseLong(userId));
				 if(null!= businessList && businessList.size()>0){
					 dataMap.put(MyFoodWebConstant.BUSINESS_LIST, businessList);
				 }else{
					 dataMap.put(MyFoodWebConstant.MESSAGE, SuccessCodesEnum.NO_BUSINESS_SUCCESS.getSuccessMessage());
				 }
			}
		} catch (BusinessServiceException e) {
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		} catch (Exception e) {
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorCode());
		}
		if(null!= userId){
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
		    jsonData = gson.toJson(dataMap);
		}else{
		if (dataMap.size() == 0) {
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}	
		jsonData = gson.toJson(dataMap);
		}
		modelAndView.setViewName(MyFoodWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyFoodWebConstant.RESPONSE, jsonData);
		logger.debug("getMyBusinessList.dataMap="+dataMap);
		return modelAndView;

	}
	
	@RequestMapping(value = "/pvt/my/{userId}/{bussId}", method = RequestMethod.GET)
	public ModelAndView getMyBusinessDetail(@PathVariable String userId,@PathVariable String bussId,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		 String jsonData = null;
		 Gson gson = new Gson();
		try {
			if(StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(bussId)  ){	
				
				BusinessInfo businessInfo = businessService.getMyBusinessDetail(Long.parseLong(userId),Long.parseLong(bussId));
				 if(null!= businessInfo ){
					 dataMap.put(MyFoodWebConstant.BUSINESS_DETAIL, businessInfo);
				 }else{
					 dataMap.put(MyFoodWebConstant.MESSAGE, SuccessCodesEnum.NO_BUSINESS_SUCCESS.getSuccessMessage());
				 }
			}
		} catch (BusinessServiceException e) {
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		} catch (Exception e) {
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorCode());
		}
		if(null!= userId){
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
		    jsonData = gson.toJson(dataMap);
		}else{
		if (dataMap.size() == 0) {
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}	
		jsonData = gson.toJson(dataMap);
		}
		modelAndView.setViewName(MyFoodWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyFoodWebConstant.RESPONSE, jsonData);
		logger.debug("getMyBusinessDetail.dataMap="+dataMap);
		return modelAndView;

	}
	
	@RequestMapping(value = "/pub/buss/{bussId}", method = RequestMethod.GET)
	public ModelAndView getBusinessDetail(@PathVariable String bussId,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		 String jsonData = null;
		 Gson gson = new Gson();
		try {
			if( StringUtils.isNotBlank(bussId)  ){	
				
				BusinessInfo businessInfo = businessService.getBusinessDetail(Long.parseLong(bussId));
				 if(null!= businessInfo ){
					 dataMap.put(MyFoodWebConstant.BUSINESS_DETAIL, businessInfo);
				 }else{
					 dataMap.put(MyFoodWebConstant.MESSAGE, SuccessCodesEnum.NO_BUSINESS_SUCCESS.getSuccessMessage());
				 }
			}
		} catch (BusinessServiceException e) {
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		} catch (Exception e) {
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorCode());
		}
		if(null!= bussId){
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
		    jsonData = gson.toJson(dataMap);
		}else{
		if (dataMap.size() == 0) {
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}	
		jsonData = gson.toJson(dataMap);
		}
		modelAndView.setViewName(MyFoodWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyFoodWebConstant.RESPONSE, jsonData);
		logger.debug("getBusinessDetail.dataMap="+dataMap);
		return modelAndView;

	}
	
	@RequestMapping(value = "/pvt/changestatus", method = RequestMethod.POST)
	public ModelAndView changeBusinessStatus(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		Long userId= 0l,bussId=0l;
		Byte status = 2;
		
		HashMap<String, Object> requestMap = ControllerUtils.getRequestMapFromMultipart(httpServletRequest);
		try {
			if(null!=requestMap.get(MyFoodBusinessConstant.USERID)){
				userId = Long.parseLong(requestMap.get(MyFoodBusinessConstant.USERID).toString());
			}
			if(null!=requestMap.get(MyFoodBusinessConstant.BID)){
				bussId = Long.parseLong(requestMap.get(MyFoodBusinessConstant.BID).toString());
			}
			if(null!=requestMap.get(MyFoodConstant.STATUS)){
				status = Byte.parseByte(requestMap.get(MyFoodConstant.STATUS).toString());
			}
			if(userId>0 && bussId>0){
				  businessService.changeBussStatus(userId,bussId, status);
				 dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
				 dataMap.put(MyFoodWebConstant.MESSAGE, SuccessCodesEnum.BUSS_STATUS_CHANGE_SUCCESS.getSuccessMessage());
				 dataMap.put(MyFoodWebConstant.CODE, SuccessCodesEnum.BUSS_STATUS_CHANGE_SUCCESS.getSuccessCode());
			}else{
				 dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
				 dataMap.put(MyFoodWebConstant.MESSAGE, SuccessCodesEnum.NO_BUSS_STATUS_SUCCESS.getSuccessMessage());
				 dataMap.put(MyFoodWebConstant.CODE, SuccessCodesEnum.NO_BUSS_STATUS_SUCCESS.getSuccessCode());
			}
		}catch (BusinessServiceException e) {
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyFoodWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyFoodWebConstant.RESPONSE, jsonData);
		logger.debug("BussController.changeBusinessStatus.dataMap="+dataMap);
		return modelAndView;
	}
	
	@RequestMapping(value = "/pvt/updatebuss", method = RequestMethod.POST)
	public ModelAndView updateBusinessInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		HashMap<String, Object>  requestMap = BusinessControllerUtils.getRequestMapFromMultipart(httpServletRequest);
		try {
			if(null!=requestMap && requestMap.size()>0){	
				BusinessInfo businessInfo = new BusinessInfo();
				RequestProcessorUtil.enrichBusinessInfo(requestMap, businessInfo);
				if(businessInfo.getBussId()>0){
					businessInfo = businessService.updateBusinessInfo(businessInfo);
					 if(null!= businessInfo){
						 dataMap.put(MyFoodWebConstant.BUSINESS_DETAIL, businessInfo);
					 }else{
						 logger.debug("updateBusinessInfo() ");
						   dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
							dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.BUSINESS_NOT_FOUND_EXCEPTION.getErrorMessage());
							dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.BUSINESS_NOT_FOUND_EXCEPTION.getErrorCode());
					}
				}
			}
			
		} catch (BusinessServiceException e) {
			logger.error("updateBusinessInfo()"+e.getLocalizedMessage(),e);
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.BUSINESS_NOT_FOUND_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.BUSINESS_NOT_FOUND_EXCEPTION.getErrorCode());
			
		}catch (Exception e) {
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION.getErrorCode());
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyFoodWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyFoodWebConstant.RESPONSE, jsonData);
		logger.debug("updateBusinessInfo.dataMap="+dataMap);
		return modelAndView;
	}
	
	
}
