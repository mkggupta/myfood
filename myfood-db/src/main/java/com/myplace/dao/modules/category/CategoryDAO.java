package com.myfood.dao.modules.category;



import java.util.List;

import com.myfood.dao.exception.DataAccessFailedException;
import com.myfood.dto.CategoryDTO;
import com.myfood.dto.SubCategoryDTO;

public interface CategoryDAO {
	
	public List<CategoryDTO> getCategoryList(String countryCode) throws DataAccessFailedException;
	public List<SubCategoryDTO> getSubCategoryList(Integer catId)throws DataAccessFailedException;
	
}
