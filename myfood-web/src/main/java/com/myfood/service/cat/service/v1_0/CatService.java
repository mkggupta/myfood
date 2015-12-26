package com.myfood.service.cat.service.v1_0;

import java.util.List;

import com.myfood.dto.CategoryDTO;
import com.myfood.dto.SubCategoryDTO;
import com.myfood.service.cat.exception.CatServiceException;

public interface CatService {
	
	
	public List<CategoryDTO> getCategoryList(String countryCode) throws CatServiceException;
	
	public  List<SubCategoryDTO> getSubCategoryList(Integer catId) throws CatServiceException;
	

	
}
