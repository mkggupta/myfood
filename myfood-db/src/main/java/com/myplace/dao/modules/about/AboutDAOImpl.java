package com.myfood.dao.modules.about;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myfood.dao.constants.AboutConstant;
import com.myfood.dao.exception.DataAccessFailedException;
import com.myfood.dao.modules.base.AbstractDBManager;
import com.myfood.framework.exception.util.ErrorCodesEnum;

public class AboutDAOImpl extends AbstractDBManager implements AboutDAO {
	private static Logger logger = LoggerFactory.getLogger(AboutDAOImpl.class);
	public String getAboutUs() throws DataAccessFailedException{
		try {
			return (String) sqlMapClient_.queryForObject(AboutConstant.GET_ABOUT_US);
			}catch(SQLException e){
				logger.error("Exception in getAboutUs : " + e.getMessage());
				throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}
	}
}
