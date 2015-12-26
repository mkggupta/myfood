package com.myfood.dao.modules.search;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myfood.dao.constants.SearchConstant;
import com.myfood.dao.exception.DataAccessFailedException;
import com.myfood.dao.modules.base.AbstractDBManager;
import com.myfood.dto.BusinessSearchDTO;
import com.myfood.dto.BusinessSearchVO;
import com.myfood.framework.exception.util.ErrorCodesEnum;



public class SearchDAOImpl extends AbstractDBManager implements SearchDAO {
	private static Logger logger = LoggerFactory.getLogger(SearchDAOImpl.class);
	
	@SuppressWarnings("unchecked")
	public List<BusinessSearchDTO> getBusinessListByCatId(Integer catId,int startLimit,int endLimit ) throws DataAccessFailedException{
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("catId", catId);
			params.put("startLimit", startLimit);
			params.put("endLimit", endLimit);
			 return (List<BusinessSearchDTO>) sqlMapClient_.queryForList(SearchConstant.GET_BUSINESS_LIST_BY_CATEGORY_ID,params);
			}catch(SQLException e){
				logger.error("Exception in getBusinessList : " + e.getMessage());
				throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}
	}
	
	@SuppressWarnings("unchecked")
	public List<BusinessSearchDTO> getBusinessListCatIdNearMe(Integer catId,Float latitude,Float longitude,int startLimit,int endLimit) throws DataAccessFailedException{
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("catId", catId);
			params.put("latitude", latitude);
			params.put("longitude", longitude);
			params.put("startLimit", startLimit);
			params.put("endLimit", endLimit);
		    return (List<BusinessSearchDTO>) sqlMapClient_.queryForList(SearchConstant.GET_BUSINESS_LIST_BY_CATEGORY_ID_LONG_LAD,params);
		}catch(SQLException e){
			logger.error("Exception in getBusinessListCatIdNearMe : " + e.getMessage());
			throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}

	}
	
	@SuppressWarnings("unchecked")
	public List<BusinessSearchDTO> getBusinessListCatIdNearMe(BusinessSearchVO businessSearchVO,int startLimit,int endLimit) throws DataAccessFailedException{
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("catId", businessSearchVO.getCatId());
			params.put("latitude", businessSearchVO.getLatitude());
			params.put("longitude", businessSearchVO.getLongitude());
			//params.put("distance", businessSearchVO.getDistance());
			params.put("startLimit", startLimit);
			params.put("endLimit", endLimit);
			logger.debug("getBusinessListCatIdNearMe : " +businessSearchVO.getCatId()+","+ businessSearchVO.getLatitude()+", "+businessSearchVO.getLongitude()+","+businessSearchVO.getDistance());
		    return (List<BusinessSearchDTO>) sqlMapClient_.queryForList(SearchConstant.GET_BUSINESS_LIST_BY_CATEGORY_ID_LONG_LAD,params);
		}catch(SQLException e){
			logger.error("Exception in getBusinessListCatIdNearMe : " + e.getMessage());
			throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
	}
	@SuppressWarnings("unchecked")
	public List<BusinessSearchDTO> getBusinessListBySubCatId(Integer catId,String subCatId,int startLimit,int endLimit) throws DataAccessFailedException{
		try {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("catId", catId);
				params.put("subCatId", subCatId);
				params.put("startLimit", startLimit);
				params.put("endLimit", endLimit);
			     return (List<BusinessSearchDTO>) sqlMapClient_.queryForList(SearchConstant.GET_BUSINESS_LIST_BY_SUBCATEGORY_ID,params);
			}catch(SQLException e){
				logger.error("Exception in getBusinessList : " + e.getMessage());
				throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}
	}
	@SuppressWarnings("unchecked")
	public List<BusinessSearchDTO> getBusinessListBySubCatId(BusinessSearchVO businessSearchVO,String subCatId,int startLimit,int endLimit) throws DataAccessFailedException{
		try {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("catId", businessSearchVO.getCatId());
				params.put("latitude", businessSearchVO.getLatitude());
				params.put("longitude", businessSearchVO.getLongitude());
				//params.put("distance", businessSearchVO.getDistance());
				params.put("subCatId", subCatId);
				params.put("startLimit", startLimit);
				params.put("endLimit", endLimit);
			     return (List<BusinessSearchDTO>) sqlMapClient_.queryForList(SearchConstant.GET_BUSINESS_LIST_BY_SUBCATEGORY_ID_NEAR_ME,params);
			}catch(SQLException e){
				logger.error("Exception in getBusinessList : " + e.getMessage());
				throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}
	}
	@SuppressWarnings("unchecked")
	public List<BusinessSearchDTO> getBusinessListNearMe(BusinessSearchVO businessSearchVO,int startLimit,int endLimit) throws DataAccessFailedException{
		try {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("latitude", businessSearchVO.getLatitude());
				params.put("longitude", businessSearchVO.getLongitude());
				params.put("distance", businessSearchVO.getDistance());
				params.put("startLimit", startLimit);
				params.put("endLimit", endLimit);
			    return (List<BusinessSearchDTO>) sqlMapClient_.queryForList(SearchConstant.GET_BUSINESS_LIST_BY_NEAR_ME,params);
			}catch(SQLException e){
				logger.error("Exception in getBusinessList : " + e.getMessage());
				throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}
	}
	@SuppressWarnings("unchecked")
	public List<BusinessSearchDTO> getBusinessListByZip(String zipCode,int startLimit,int endLimit) throws DataAccessFailedException{
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("zipCode", zipCode);
			params.put("startLimit", startLimit);
			params.put("endLimit", endLimit);
			logger.debug(zipCode+""+startLimit+""+endLimit);
			 return (List<BusinessSearchDTO>) sqlMapClient_.queryForList(SearchConstant.GET_BUSINESS_LIST_BY_ZIP,params);
			}catch(SQLException e){
				e.printStackTrace();
				logger.error("Exception in getBusinessList : " + e.getMessage());
				throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}
	}
	
	@SuppressWarnings("unchecked")
	public List<BusinessSearchDTO> getBusinessListByText(String text,int startLimit,int endLimit) throws DataAccessFailedException{
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("text", text);
			params.put("startLimit", startLimit);
			params.put("endLimit", endLimit);
			 return (List<BusinessSearchDTO>) sqlMapClient_.queryForList(SearchConstant.GET_BUSINESS_LIST_BY_TEXT,params);
			}catch(SQLException e){
				logger.error("Exception in getBusinessList : " + e.getMessage());
				throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}
	}
	

	@SuppressWarnings("unchecked")
	public List<BusinessSearchDTO> getBusinessListByText(BusinessSearchVO businessSearchVO,String text,int startLimit,int endLimit) throws DataAccessFailedException{
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("latitude", businessSearchVO.getLatitude());
			params.put("longitude", businessSearchVO.getLongitude());
			params.put("text", text);
			params.put("startLimit", startLimit);
			params.put("endLimit", endLimit);
			 return (List<BusinessSearchDTO>) sqlMapClient_.queryForList(SearchConstant.GET_BUSINESS_LIST_BY_TEXT_NEAR_ME,params);
			}catch(SQLException e){
				logger.error("Exception in getBusinessList : " + e.getMessage());
				throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}
	}
	
	
}
