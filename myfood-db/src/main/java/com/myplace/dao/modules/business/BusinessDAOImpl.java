package com.myfood.dao.modules.business;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myfood.dao.constants.BusinessConstant;
import com.myfood.dao.exception.DataAccessFailedException;
import com.myfood.dao.exception.DataUpdateFailedException;
import com.myfood.dao.modules.base.AbstractDBManager;
import com.myfood.dto.BusinessFileInfo;
import com.myfood.dto.BusinessInfo;
import com.myfood.framework.exception.util.ErrorCodesEnum;






public class BusinessDAOImpl extends AbstractDBManager implements BusinessDAO {
	private static Logger logger = LoggerFactory.getLogger(BusinessDAOImpl.class);
	
	public Long saveBusinessInfo(BusinessInfo businessInfo) throws DataUpdateFailedException{
		try {
		logger.debug("BusinessDAOImpl=="+businessInfo);
		return (Long) sqlMapClient_.insert(BusinessConstant.INSERT_BUSINESS_INFO,businessInfo);
	
		}catch(SQLException e){
			logger.error("Exception in saveBusinessInfo : " + e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		
	}
	
	
	public void  saveBusinessFileInfo(BusinessFileInfo businessFileInfo)  throws DataUpdateFailedException{
		try {
		logger.debug("saveBusinessFileInfo=="+businessFileInfo);
		 sqlMapClient_.insert(BusinessConstant.INSERT_BUSINESS_FILE_INFO,businessFileInfo);
	
		}catch(SQLException e){
			logger.error("Exception in saveBusinessFileInfo : " + e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		
	}


	@Override
	public BusinessInfo getMyBusinessDetail(Long userId, Long businessId)throws DataAccessFailedException {
		try {
			logger.debug("getMyBusinessDetail=="+userId+"businessId="+businessId);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", userId);
			params.put("businessId", businessId);
			return (BusinessInfo) sqlMapClient_.queryForObject(BusinessConstant.GET_MY_BUSINESS_DETAILS,params);
		
			}catch(SQLException e){
				logger.error("Exception in saveBusinessInfo : " + e.getMessage());
				throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}
	}
	
	@Override
	public BusinessInfo getBusinessDetail (Long businessId) throws DataAccessFailedException {
		try {
			logger.debug("getMyBusinessDetail businessId="+businessId);
			return (BusinessInfo) sqlMapClient_.queryForObject(BusinessConstant.GET_BUSINESS_DETAILS,businessId);
		
			}catch(SQLException e){
				logger.error("Exception in saveBusinessInfo : " + e.getMessage());
				throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessInfo> getMyBusinessList(Long userId)throws DataAccessFailedException {
		try {
			logger.debug("getMyBusinessList=="+userId);
			return (List<BusinessInfo>) sqlMapClient_.queryForList(BusinessConstant.GET_MY_BUSINESS_LIST,userId);
		
			}catch(SQLException e){
				logger.error("Exception in saveBusinessInfo : " + e.getMessage());
				throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}
	}
	
	@Override
	public int updateBussStatus(Long userId, Long businessId, byte status)throws DataUpdateFailedException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", userId);
			params.put("businessId", businessId);
			params.put("status",status);
			return sqlMapClient_.update(BusinessConstant.UPDATE_BUSINESS_STATUS, params);
		} catch (SQLException e) {
			logger.error("Exception in updateBussStatus : ", e.getLocalizedMessage(), e);
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		
	}
	
	public void updateBusinessDetail (BusinessInfo businessInfo) throws DataUpdateFailedException{
		try {
			sqlMapClient_.update(BusinessConstant.UPDATE_BUSINESS_DETAILS, businessInfo);
		} catch (SQLException e) {
			logger.error("Exception in storing user details in database for the business : " + businessInfo + " error  : " + e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		
	}
	
	public void updateBusinessView (Long businessId) throws DataUpdateFailedException {
		try {
			logger.debug("updateBusinessView businessId="+businessId);
			 sqlMapClient_.update(BusinessConstant.UPDATE_BUSINESS_VIEW,businessId);
			}catch(SQLException e){
				logger.error("Exception in updateBusinessView : " + e.getMessage());
				throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}
	}
	
	
	
}
