package com.myfood.dao.modules.business;



import java.util.List;

import com.myfood.dao.exception.DataAccessFailedException;
import com.myfood.dao.exception.DataUpdateFailedException;
import com.myfood.dto.BusinessFileInfo;
import com.myfood.dto.BusinessInfo;

public interface BusinessDAO {
	public Long saveBusinessInfo(BusinessInfo businessInfo) throws DataUpdateFailedException;
	public void saveBusinessFileInfo(BusinessFileInfo businessFileInfo) throws DataUpdateFailedException;
	public List<BusinessInfo> getMyBusinessList (Long userId) throws DataAccessFailedException;
	public BusinessInfo getMyBusinessDetail (Long userId,Long businessId) throws DataAccessFailedException;
	public BusinessInfo getBusinessDetail (Long businessId) throws DataAccessFailedException;
	public int updateBussStatus(Long userId,Long businessId,byte status) throws DataUpdateFailedException;
	public void updateBusinessDetail (BusinessInfo businessInfo) throws DataUpdateFailedException;
	public void updateBusinessView (Long businessId) throws DataUpdateFailedException;
}
