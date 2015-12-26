package com.myfood.service.business.service.v1_0;

import java.util.List;

import com.myfood.dto.BusinessInfo;
import com.myfood.service.business.exception.BusinessServiceException;

public interface BusinessService {
	public Long createBusiness(BusinessInfo businessInfo) throws BusinessServiceException;
	public List<BusinessInfo> getMyBusinessList (Long userId) throws BusinessServiceException;
	public BusinessInfo getMyBusinessDetail (Long userId,Long businessId) throws BusinessServiceException;
	public BusinessInfo getBusinessDetail (Long businessId) throws BusinessServiceException;
	public void changeBussStatus(long userId,long businessId,Byte status) throws BusinessServiceException;
	public BusinessInfo updateBusinessInfo (BusinessInfo businessInfo) throws BusinessServiceException;
	
	
}
