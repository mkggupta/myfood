package com.myfood.service.search.service.v1_0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myfood.common.constant.MyFoodBusinessConstant;
import com.myfood.common.util.MyFoodProperties;
import com.myfood.common.util.MyFoodPropertyKeys;
import com.myfood.dao.exception.DataAccessFailedException;
import com.myfood.dao.modules.search.SearchDAO;
import com.myfood.dto.BusinessSearchDTO;
import com.myfood.dto.BusinessSearchVO;
import com.myfood.framework.exception.util.ErrorCodesEnum;
import com.myfood.service.search.exception.SearchServiceException;


public class SearchServiceImpl implements SearchService {
	private static Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);
	private SearchDAO searchDAO ;

	public void setSearchDAO(SearchDAO searchDAO) {
		this.searchDAO = searchDAO;
	}



	@Override
	public HashMap<String, Object> getBusinessSearch(BusinessSearchVO searchVO,int startLimit,int endLimit) throws SearchServiceException{
		 HashMap<String, Object>  resultMap = new HashMap<String, Object>();
		 List<BusinessSearchDTO> bussList = new ArrayList<BusinessSearchDTO>();
		try {
			logger.debug("getBusinessSearch--"+searchVO.getUserId()+"---"+searchVO.getType()+"--"+searchVO.getZipCode()+"--"+searchVO.getLatitude()+"--"+searchVO.getLongitude());
			if(MyFoodBusinessConstant.CAT_SEARCH_TYPE.equalsIgnoreCase(searchVO.getType())){
				if(null!=searchVO.getLatitude() && null!=searchVO.getLongitude() ){
					//bussList= searchDAO.getBusinessListCatIdNearMe(searchVO.getCatId(), searchVO.getLatitude(), searchVO.getLongitude(), startLimit, endLimit+1);
					bussList= searchDAO.getBusinessListCatIdNearMe(searchVO, startLimit, endLimit+1);
					if(null==bussList || bussList.size()==0){
						bussList =searchDAO.getBusinessListByCatId(searchVO.getCatId(), startLimit, endLimit+1);
					}
				}else{
					bussList =searchDAO.getBusinessListByCatId(searchVO.getCatId(), startLimit, endLimit+1);
				}
				
			}else if(MyFoodBusinessConstant.SUB_CAT_SEARCH_TYPE.equalsIgnoreCase(searchVO.getType())){
				if(null!=searchVO.getLatitude() && null!=searchVO.getLongitude() ){
					bussList = searchDAO.getBusinessListBySubCatId(searchVO, "%"+searchVO.getSubCatId()+ "%", startLimit, endLimit+1);
					if(null==bussList || bussList.size()==0){
						bussList = searchDAO.getBusinessListBySubCatId(searchVO.getCatId(), "%"+searchVO.getSubCatId()+ "%", startLimit, endLimit+1);
					}
				}else{
					bussList = searchDAO.getBusinessListBySubCatId(searchVO.getCatId(), "%"+searchVO.getSubCatId()+ "%", startLimit, endLimit+1);
				}
			
				
			}else if(MyFoodBusinessConstant.ZIP_SEARCH_TYPE.equalsIgnoreCase(searchVO.getType())){
				logger.debug("getBusinessSearch--"+startLimit+"---"+searchVO.getType()+"--"+searchVO.getZipCode()+"--"+endLimit);
				bussList = searchDAO.getBusinessListByZip(searchVO.getZipCode(), startLimit, endLimit+1);
			}else if(MyFoodBusinessConstant.NEAR_ME_SEARCH_TYPE.equalsIgnoreCase(searchVO.getType())){
				if(null!=searchVO.getLatitude() && null!=searchVO.getLongitude() ){
					bussList = searchDAO.getBusinessListNearMe(searchVO, startLimit, endLimit+1);	
				}
				
			}else if(MyFoodBusinessConstant.TEXT_SEARCH_TYPE.equalsIgnoreCase(searchVO.getType())){
				if(null!=searchVO.getLatitude() && null!=searchVO.getLongitude() ){
					bussList =searchDAO.getBusinessListByText(searchVO,"%"+searchVO.getText()+ "%", startLimit, endLimit+1);
					if(null==bussList || bussList.size()==0){
					 bussList =searchDAO.getBusinessListByText(searchVO,"%"+searchVO.getText()+ "%", startLimit, endLimit+1);
					}
				}else{
					bussList =searchDAO.getBusinessListByText("%"+searchVO.getText()+ "%", startLimit, endLimit+1);
				}
			} 
			logger.debug("getBusinessSearch- searchVO.getUserId()="+searchVO.getUserId());
			if(null!= searchVO.getUserId() && searchVO.getUserId()>0){
				
				for(BusinessSearchDTO businessSearchDTO:bussList){
					logger.debug("getBusinessSearch- searchVO.getUserId()="+ businessSearchDTO.getUserId());
					if(null!=businessSearchDTO.getUserId() && businessSearchDTO.getUserId().equals(searchVO.getUserId())){
						MyFoodProperties myfoodProperties = MyFoodProperties.getInstance();
						String baseUrl = myfoodProperties.getProperty(MyFoodPropertyKeys.BASE_URL);
						businessSearchDTO.setUpdateUrl(baseUrl+"business/pvt/updatebuss");
						businessSearchDTO.setButtonName("Update Business Info");
					}
				}
				
			}
			
			
		} catch (DataAccessFailedException e) {
			throw new SearchServiceException(ErrorCodesEnum.BUSINESS_SEARCH_SERVICE_FAILED_EXCEPTION);
		}
		logger.debug("getBusinessSearch--"+bussList);
		if(null!=bussList && bussList.size()>0){
			logger.debug("before getBusinessSearch--"+bussList.size());
			if(bussList.size()>endLimit){
				bussList.remove(endLimit);
				MyFoodProperties myfoodProperties = MyFoodProperties.getInstance();
				 String baseUrl = myfoodProperties.getProperty(MyFoodPropertyKeys.BASE_URL);
				resultMap.put(MyFoodBusinessConstant.NEXTURL, baseUrl+"search/pub/getBuss/more?sLimit="+(startLimit+endLimit));
			}
			logger.debug("after getBusinessSearch--"+bussList.size());
			resultMap.put("searchResult", bussList);
			//resultMap.put(MyFoodBusinessConstant.FEEDTYPE, MyFoodBusinessConstant.NORMALTYPE);

		}
		return resultMap;
	}
	
	
}
