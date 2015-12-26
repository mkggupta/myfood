package com.myfood.service.search.service.v1_0;



import java.util.HashMap;

import com.myfood.dto.BusinessSearchVO;
import com.myfood.service.search.exception.SearchServiceException;

public interface SearchService {
	
	public HashMap<String, Object> getBusinessSearch(BusinessSearchVO searchVO,int pageLimit,int endLimit) throws SearchServiceException;
	
	
	

	
}
