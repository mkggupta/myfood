package com.myfood.rest.controller.cat;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.myfood.dto.CategoryDTO;
import com.myfood.dto.SubCategoryDTO;
import com.myfood.framework.exception.util.ErrorCodesEnum;
import com.myfood.framework.success.SuccessCodesEnum;
import com.myfood.rest.constant.MyFoodWebConstant;
import com.myfood.service.cat.exception.CatServiceException;
import com.myfood.service.cat.service.v1_0.CatService;

@Controller
@RequestMapping("/api/category")
public class CategoryController {
	private static Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	private CatService catService ;
	
	
	
	public void setCatService(CatService catService) {
		this.catService = catService;
	}



	@RequestMapping(value = "/pub/getCats", method = RequestMethod.GET)
	public ModelAndView getCategories(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		
		if(logger.isDebugEnabled()){
			logger.debug("CategoryController.getCategories"+httpServletRequest);
			Enumeration<Object> headerNames = httpServletRequest.getHeaderNames();
			Map<String,String> requestParamMap = new HashMap<String, String>();
			while (headerNames.hasMoreElements()) {
				String headerName = (String) headerNames.nextElement();
				requestParamMap.put(headerName, httpServletRequest.getHeader(headerName));	
			}
			logger.debug("CategoryController.getCategories"+requestParamMap);
		}
		try {
				
				String countryCode =(null!=httpServletRequest.getParameter(MyFoodWebConstant.COUNTRY_CODE))?httpServletRequest.getParameter(MyFoodWebConstant.COUNTRY_CODE):MyFoodWebConstant.DEFAULT_COUNTRY_CODE;
				if(StringUtils.isNotBlank(countryCode)){
					List<CategoryDTO> catList = catService.getCategoryList(countryCode);
					 if(null!=catList && catList.size()>0){
						dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
						dataMap.put(MyFoodWebConstant.CATEGORIES,catList);
					}else{
						dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
						dataMap.put(MyFoodWebConstant.MESSAGE, SuccessCodesEnum.NO_CAT_SUCCESS.getSuccessMessage());
						dataMap.put(MyFoodWebConstant.CODE, SuccessCodesEnum.NO_CAT_SUCCESS.getSuccessCode());	
					}
				}else{
					dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
					dataMap.put(MyFoodWebConstant.MESSAGE, SuccessCodesEnum.NO_CAT_SUCCESS.getSuccessMessage());
					dataMap.put(MyFoodWebConstant.CODE, SuccessCodesEnum.NO_CAT_SUCCESS.getSuccessCode());	
				}
		
		} catch (CatServiceException e) {
			logger.error("CategoryController()"+e.getLocalizedMessage(),e);
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.CATEGORY_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.CATEGORY_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyFoodWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyFoodWebConstant.RESPONSE, jsonData);
		logger.debug("CategoryController.getCategories.dataMap="+dataMap);
		return modelAndView;
	}
	
	@RequestMapping(value = "/pub/getSubCats", method = RequestMethod.GET)
	public ModelAndView getSubCategories(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		ModelAndView modelAndView = new ModelAndView();
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		
		if(logger.isDebugEnabled()){
			logger.debug("CategoryController.getSubCategories="+httpServletRequest);
			Enumeration<Object> headerNames = httpServletRequest.getHeaderNames();
			Map<String,String> requestParamMap = new HashMap<String, String>();
			while (headerNames.hasMoreElements()) {
				String headerName = (String) headerNames.nextElement();
				requestParamMap.put(headerName, httpServletRequest.getHeader(headerName));	
			}
			logger.debug("CategoryController.getSubCategories"+requestParamMap);
		}
		try {
				
				Integer catId =(null!=httpServletRequest.getParameter(MyFoodWebConstant.CATEGORY_ID))?Integer.parseInt(httpServletRequest.getParameter(MyFoodWebConstant.CATEGORY_ID)):0;
				if(catId>0){
					List<SubCategoryDTO> subCatList = catService.getSubCategoryList(catId);
					 if(null!=subCatList && subCatList.size()>0){
						dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
						dataMap.put(MyFoodWebConstant.SUB_CATEGORIES,subCatList);
					}else{
						dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
						dataMap.put(MyFoodWebConstant.MESSAGE, SuccessCodesEnum.NO_SUB_CAT_SUCCESS.getSuccessMessage());
						dataMap.put(MyFoodWebConstant.CODE, SuccessCodesEnum.NO_SUB_CAT_SUCCESS.getSuccessCode());	
					}
				}else{
					dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_SUCCESS);
					dataMap.put(MyFoodWebConstant.MESSAGE, SuccessCodesEnum.NO_SUB_CAT_SUCCESS.getSuccessMessage());
					dataMap.put(MyFoodWebConstant.CODE, SuccessCodesEnum.NO_SUB_CAT_SUCCESS.getSuccessCode());	
				}
		
		} catch (CatServiceException e) {
			logger.error("CategoryController()"+e.getLocalizedMessage(),e);
			dataMap.put(MyFoodWebConstant.STATUS, MyFoodWebConstant.STATUS_ERROR);
			dataMap.put(MyFoodWebConstant.MESSAGE, ErrorCodesEnum.SUB_CATEGORY_SERVICE_FAILED_EXCEPTION.getErrorMessage());
			dataMap.put(MyFoodWebConstant.CODE, ErrorCodesEnum.SUB_CATEGORY_SERVICE_FAILED_EXCEPTION.getErrorCode());
			
		}
		Gson gson = new Gson();
		String jsonData = gson.toJson(dataMap);
		modelAndView.setViewName(MyFoodWebConstant.DEFAULT_VIEW_NAME);
		modelAndView.addObject(MyFoodWebConstant.RESPONSE, jsonData);
		logger.debug("CategoryController.getSubCategories.dataMap="+dataMap);
		return modelAndView;
	}
	
	

}
