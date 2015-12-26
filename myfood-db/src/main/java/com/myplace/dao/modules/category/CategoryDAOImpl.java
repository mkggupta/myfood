package com.myfood.dao.modules.category;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myfood.dao.constants.CategoryConstant;
import com.myfood.dao.exception.DataAccessFailedException;
import com.myfood.dao.modules.base.AbstractDBManager;
import com.myfood.dto.CategoryDTO;
import com.myfood.dto.SubCategoryDTO;
import com.myfood.framework.exception.util.ErrorCodesEnum;






public class CategoryDAOImpl extends AbstractDBManager implements CategoryDAO {
	private static Logger logger = LoggerFactory.getLogger(CategoryDAOImpl.class);
	
	
	@SuppressWarnings("unchecked")
	public List<CategoryDTO> getCategoryList(String countryCode) throws DataAccessFailedException{
		try {
			logger.debug("getCategoryList==");
			 return (List<CategoryDTO>) sqlMapClient_.queryForList(CategoryConstant.GET_CATEGORY_LIST,countryCode);
			}catch(SQLException e){
				logger.error("Exception in getAdvtTemplate : " + e.getMessage());
				throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubCategoryDTO> getSubCategoryList(Integer catId) throws DataAccessFailedException {
		try {
		return (List<SubCategoryDTO>) sqlMapClient_.queryForList(CategoryConstant.GET_SUB_CATEGORY_LIST,catId);
	} catch (SQLException e) {
		logger.error("Exception in getMyAdvtList : " + e.getMessage());
		throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
	}
	}

	
}
