package com.myfood.service.cat.service.v1_0;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myfood.dao.exception.DataAccessFailedException;
import com.myfood.dao.modules.category.CategoryDAO;
import com.myfood.dto.CategoryDTO;
import com.myfood.dto.SubCategoryDTO;
import com.myfood.framework.exception.util.ErrorCodesEnum;
import com.myfood.service.cat.exception.CatServiceException;


public class CatServiceImpl implements CatService {
	private static Logger logger = LoggerFactory.getLogger(CatServiceImpl.class);
	private CategoryDAO catDAO ;

	public void setCatDAO(CategoryDAO catDAO) {
		this.catDAO = catDAO;
	}
	@Override
	public List<CategoryDTO> getCategoryList(String countryCode) throws CatServiceException{
		
		try {
		  return catDAO.getCategoryList(countryCode);
		} catch (DataAccessFailedException e) {
			throw new CatServiceException(ErrorCodesEnum.CATEGORY_SERVICE_FAILED_EXCEPTION);
		}
	}
	@Override
	public  List<SubCategoryDTO> getSubCategoryList(Integer catId) throws CatServiceException{
		
		try {
		  return catDAO.getSubCategoryList(catId);
		} catch (DataAccessFailedException e) {
			throw new CatServiceException(ErrorCodesEnum.SUB_CATEGORY_SERVICE_FAILED_EXCEPTION);
		}
	}
	
}
