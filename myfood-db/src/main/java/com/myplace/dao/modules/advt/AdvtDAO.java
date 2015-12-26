package com.myfood.dao.modules.advt;



import java.util.List;
import java.util.Map;

import com.myfood.dao.exception.DataAccessFailedException;
import com.myfood.dao.exception.DataUpdateFailedException;
import com.myfood.dto.AdvertisementInfo;
import com.myfood.dto.AdvtStats;
import com.myfood.dto.AdvtTemplate;
import com.myfood.dto.FileInfo;
import com.myfood.dto.PaymentInfo;

public interface AdvtDAO {
	public Long saveAdvtInfo(AdvertisementInfo advertisementInfo) throws DataUpdateFailedException;
	public void saveAdvtDetail(AdvertisementInfo advertisementInfo) throws DataUpdateFailedException;
	public void saveAdvtFileInfo(FileInfo fileInfo) throws DataUpdateFailedException;
	public void savePaymentInfo(PaymentInfo paymentInfo) throws DataUpdateFailedException;
	public List<AdvtTemplate> getAdvtTemplate(Byte type) throws DataAccessFailedException;
	public List<AdvertisementInfo> getMyAdvtList(Long userId,int startLimit,int endLimit)throws DataAccessFailedException;
	public List<Long> getAdvtListByUserId(Long userId)throws DataAccessFailedException;
	public List<AdvtStats> getAdvtStatsList(Map<String, Object> parameterMap)throws DataAccessFailedException;
	public int updateAdvtStatus(Long userId,Long advtCode,byte status) throws DataUpdateFailedException;
}
