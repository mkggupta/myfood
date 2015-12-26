package com.myfood.dao.modules.advt;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myfood.dao.constants.AdvtConstant;
import com.myfood.dao.exception.DataAccessFailedException;
import com.myfood.dao.exception.DataUpdateFailedException;
import com.myfood.dao.modules.base.AbstractDBManager;
import com.myfood.dto.AdvertisementInfo;
import com.myfood.dto.AdvtStats;
import com.myfood.dto.AdvtTemplate;
import com.myfood.dto.AdvtBusinessInfo;
import com.myfood.dto.FileInfo;
import com.myfood.dto.PaymentInfo;
import com.myfood.framework.exception.util.ErrorCodesEnum;






public class AdvtDAOImpl extends AbstractDBManager implements AdvtDAO {
	private static Logger logger = LoggerFactory.getLogger(AdvtDAOImpl.class);
	
	public Long saveAdvtInfo(AdvertisementInfo advertisementInfo) throws DataUpdateFailedException{
		try {
		logger.debug("AdvtDAOImpl=="+advertisementInfo);
		return (Long) sqlMapClient_.insert(AdvtConstant.INSERT_ADVT_INFO,advertisementInfo);
	
		}catch(SQLException e){
			logger.error("Exception in saveAdvtInfo : " + e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		
	}
	
	public void saveAdvtDetail(AdvertisementInfo advertisementInfo) throws DataUpdateFailedException{
		try {
		logger.debug("saveAdvtDetail=="+advertisementInfo);
		 sqlMapClient_.insert(AdvtConstant.INSERT_ADVT_DETAIL,advertisementInfo);
	
		}catch(SQLException e){
			logger.error("Exception in saveAdvtDetail : " + e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		
	}
	public void saveAdvtFileInfo(FileInfo fileInfo) throws DataUpdateFailedException{
		try {
		logger.debug("saveAdvtFileInfo=="+fileInfo);
		 sqlMapClient_.insert(AdvtConstant.INSERT_ADVT_FILE_INFO,fileInfo);
	
		}catch(SQLException e){
			logger.error("Exception in saveAdvtFileInfo : " + e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		
	}
	
	public void savePaymentInfo(PaymentInfo paymentInfo) throws DataUpdateFailedException{
		try {
		logger.debug("savePaymentInfo=="+paymentInfo);
		 sqlMapClient_.insert(AdvtConstant.INSERT_PAYMENT_INFO,paymentInfo);
	
		}catch(SQLException e){
			logger.error("Exception in savePaymentInfo : " + e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		
	}
	
	public void saveBusinessInfo(AdvtBusinessInfo businessInfo) throws DataUpdateFailedException{
		try {
		logger.debug("saveAdvtDetail=="+businessInfo);
		 sqlMapClient_.insert(AdvtConstant.INSERT_ADVT_BUSINESS_INFO,businessInfo);
	
		}catch(SQLException e){
			logger.error("Exception in createStation : " + e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		
	}
	@SuppressWarnings("unchecked")
	public List<AdvtTemplate> getAdvtTemplate(Byte type) throws DataAccessFailedException{
		try {
			logger.debug("getAdvtTemplate==");
			 return (List<AdvtTemplate>) sqlMapClient_.queryForList(AdvtConstant.GET_ADVT_TEMPLATE,type);
			}catch(SQLException e){
				logger.error("Exception in getAdvtTemplate : " + e.getMessage());
				throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdvertisementInfo> getMyAdvtList(Long userId, int startLimit,int endLimit) throws DataAccessFailedException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("creatorId", userId);
			params.put("status", "DELETED");
			params.put("startLimit", startLimit);
			params.put("endLimit", endLimit);
		return (List<AdvertisementInfo>) sqlMapClient_.queryForList(AdvtConstant.GET_MY_ADVT_LIST,params);
	} catch (SQLException e) {
		logger.error("Exception in getMyAdvtList : " + e.getMessage());
		throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
	}
	}

	@Override
	public int updateAdvtStatus(Long userId, Long advtCode, byte status)throws DataUpdateFailedException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", userId);
			params.put("advtCode", advtCode);
			params.put("status",status);
			return sqlMapClient_.update(AdvtConstant.UPDATE_ADVT_STATUS, params);
		} catch (SQLException e) {
			logger.error("Exception in updateAdvtStatus : ", e.getLocalizedMessage(), e);
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getAdvtListByUserId(Long userId)throws DataAccessFailedException{
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("creatorId", userId);
			params.put("status", "DELETED");
			return sqlMapClient_.queryForList(AdvtConstant.GET_ADVT_LIST_BY_USERID,params );
		} catch (SQLException e) {
			logger.error("Exception in getAdvtListByUserId : " + e.getMessage());
			throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<AdvtStats> getAdvtStatsList(Map<String, Object> parameterMap)throws DataAccessFailedException{
		try {
			return sqlMapClient_.queryForList(AdvtConstant.GET_ADVT_STATS_LIST,parameterMap);
		} catch (SQLException e) {
			logger.error("Exception in getAdvtStatsList : " + e.getMessage());
			throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
	}
}
