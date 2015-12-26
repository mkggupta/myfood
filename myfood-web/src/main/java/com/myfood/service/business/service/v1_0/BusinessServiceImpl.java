package com.myfood.service.business.service.v1_0;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myfood.common.business.util.BusinessUtil;
import com.myfood.dao.exception.DataAccessFailedException;
import com.myfood.dao.exception.DataUpdateFailedException;
import com.myfood.dao.modules.business.BusinessDAO;
import com.myfood.dto.BusinessFileInfo;
import com.myfood.dto.BusinessInfo;
import com.myfood.framework.exception.util.ErrorCodesEnum;
import com.myfood.service.business.exception.BusinessServiceException;



public class BusinessServiceImpl implements BusinessService {
	private static Logger logger = LoggerFactory.getLogger(BusinessServiceImpl.class);
	private BusinessDAO businessDAO ;
	
	public void setBusinessDAO(BusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}

	public Long createBusiness(BusinessInfo businessInfo) throws BusinessServiceException{
		
		logger.debug("BusinessServiceImpl----"+businessInfo.getBussName());
		Long businessId = null;
		try {
			
			businessId = businessDAO.saveBusinessInfo(businessInfo);
			logger.debug("businessId----"+businessId);
			if(null==businessId || businessId ==0){
				throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION);
			}
			
			 List<BusinessFileInfo> fileInfoList = businessInfo.getBusinessFileInfo();
			logger.debug("fileInfo----"+fileInfoList);
			if(null!=fileInfoList && fileInfoList.size()>0){
				for(BusinessFileInfo businessFileInfo :fileInfoList){
					businessFileInfo.setBussId(businessId);
					businessDAO.saveBusinessFileInfo(businessFileInfo);
				}
			}
		} catch (DataUpdateFailedException e) {
			logger.error("createBusiness---"+e.getLocalizedMessage());
			throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION);
		}
		return businessId;
	}
	
	public List<BusinessInfo> getMyBusinessList (Long userId) throws BusinessServiceException{
		
		try {
			return businessDAO.getMyBusinessList(userId);
		} catch (DataAccessFailedException e) {
			logger.error("getMyBusinessList---"+e.getLocalizedMessage());
			throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION);
		}
	}
	
	public BusinessInfo getMyBusinessDetail (Long userId,Long businessId) throws BusinessServiceException{

		try {
			return businessDAO.getMyBusinessDetail(userId,businessId);
		} catch (DataAccessFailedException e) {
			logger.error("getMyBusinessDetail---"+e.getLocalizedMessage());
			throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION);
		}
	}
	
	public BusinessInfo getBusinessDetail (Long businessId) throws BusinessServiceException{

		try {
			BusinessInfo  businessInfo = businessDAO.getBusinessDetail(businessId);
			businessDAO.updateBusinessView(businessId);
			logger.debug("changeBussStatus---businessId="+businessId);
			return businessInfo;
		} catch (DataAccessFailedException | DataUpdateFailedException e) {
			logger.error("getBusinessDetail---"+e.getLocalizedMessage());
			throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION);
		}
	}

	@Override
	public void changeBussStatus(long userId, long businessId, Byte status)throws BusinessServiceException {
		try {
			businessDAO.updateBussStatus(userId, businessId, status);
		} catch (DataUpdateFailedException e) {
			logger.error("changeBussStatus---"+e.getLocalizedMessage());
			throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION);
		}
		
	}
	
	public BusinessInfo updateBusinessInfo (BusinessInfo businessInfo) throws BusinessServiceException{
		
		BusinessInfo businessInfoObj = null ;
		
		try {
			businessInfoObj = businessDAO.getBusinessDetail(businessInfo.getBussId());
			BusinessUtil.updateBusinessInfo(businessInfoObj, businessInfo);
		} catch (DataAccessFailedException e) {
			throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_NOT_FOUND_EXCEPTION);
		}
		try {
			businessDAO.updateBusinessDetail(businessInfoObj);
		} catch (DataUpdateFailedException e) {
			logger.error("Exception in updating user details in database for the businessInfoObj : " + businessInfoObj + " error  : " + e.getMessage());
			throw new BusinessServiceException(ErrorCodesEnum.BUSINESS_SERVICE_FAILED_EXCEPTION);
		}
		return businessInfoObj;
	}
}
