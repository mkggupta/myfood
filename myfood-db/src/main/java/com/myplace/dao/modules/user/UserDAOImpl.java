package com.myfood.dao.modules.user;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myfood.dao.constants.UserConstants;
import com.myfood.dao.exception.DataAccessFailedException;
import com.myfood.dao.exception.DataUpdateFailedException;
import com.myfood.dao.modules.base.AbstractDBManager;
import com.myfood.dto.UserAuth;
import com.myfood.dto.UserInfo;
import com.myfood.dto.UserThirdPartyAuth;
import com.myfood.framework.exception.util.ErrorCodesEnum;
import com.ibatis.common.jdbc.exception.NestedSQLException;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;



public class UserDAOImpl extends AbstractDBManager implements UserDAO {

	Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	@Override
	public void saveUserInfo(UserInfo userInfo) throws DataUpdateFailedException {
		try {
			sqlMapClient_.insert(UserConstants.INSERT_USER_INFO, userInfo);
		} catch (SQLException e) {
			logger.error("Exception in storing user details in database for the user : " + userInfo + " error  : " + e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		
	}

	@Override
	public Long saveUserAuth(UserAuth userAuth)	throws DataUpdateFailedException {
		try {
			return (Long) sqlMapClient_.insert(UserConstants.INSERT_USER_AUTH, userAuth);
		} catch (NestedSQLException e) {
			Throwable t = e;
			while (t.getCause() != null) {
				t = t.getCause();
			}
			if (t instanceof MySQLIntegrityConstraintViolationException) {
				logger.error("User already present in database username : " + userAuth.getUserName() + " : registration mode : "
						+ userAuth.getRegistrationMode() + " " + e.getMessage());
				throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_UNIQUE_CONSTRAINT_VOILATION_EXCEPTION, e);
			} else {
				logger.error("Exception in storing user authentication details in database for the user : " + userAuth + " error  : " + e.getMessage());
				throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}

		} catch (SQLException e) {
			logger.error("Exception in storing user authentication details in database for the user : " + userAuth + " error  : " + e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
	}

	@Override
	public void saveUserThirdPartyAuth(UserThirdPartyAuth userThirdPartyAuth)throws DataUpdateFailedException {
		try {
			sqlMapClient_.insert(UserConstants.INSERT_USER_THIRD_PARTY_AUTH_DETAILS, userThirdPartyAuth);
		} catch (NestedSQLException e) {
			Throwable t = e;
			while (t.getCause() != null) {
				t = t.getCause();
			}
			if (t instanceof MySQLIntegrityConstraintViolationException) {
				logger.error("User already present in database username : " + userThirdPartyAuth.getId() + " : registration mode : "
						+ userThirdPartyAuth.getThirdPartyId() + " " + e.getMessage());
				throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_UNIQUE_CONSTRAINT_VOILATION_EXCEPTION, e);
			} else {
				logger.error("Exception in storing user authentication details in database for the user : " + userThirdPartyAuth.getId() + " error  : "
						+ e.getMessage());
				throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
			}

		} catch (SQLException e) {
			logger.error("Exception in storing user authentication details in database for the user : " + userThirdPartyAuth.getId() + " error  : "
					+ e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		
	}
	
	public Long getUserExists(String userKey,int thirdPartyId) throws DataAccessFailedException{
		Long userId=0l;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userKey", userKey);
			params.put("thirdPartyId", thirdPartyId);
			userId = (Long)sqlMapClient_.queryForObject(UserConstants.GET_USERID_BY_USERKEY_ID, params);
		} catch (Exception e) {
			logger.error("Exception in getUserExists : " + e.getMessage());
			throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		
		return userId;
	}
	
	public Long getUserIdByNamePassword(String userName,String password) throws DataAccessFailedException{
		Long userId=0l;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userName", userName);
			params.put("password", password);
			userId = (Long)sqlMapClient_.queryForObject(UserConstants.GET_USERID_BY_USERNAME_PASSWORD, params);
		} catch (Exception e) {
			logger.error("Exception in getUserExists : " + e.getMessage());
			throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		
		return userId;
		
	}
	
	public Long getUserIdByNameForApp(String userName) throws DataAccessFailedException{
		Long userId=0l;
		try {
			userId = (Long)sqlMapClient_.queryForObject(UserConstants.GET_USERID_BY_USERNAME_FOR_APP, userName);
		} catch (Exception e) {
			logger.error("Exception in getUserIdByName : " + e.getMessage());
			throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
		return userId;
		
	}
	
	public UserInfo getUserProfile(long userId) throws DataAccessFailedException {
		try {
			return (UserInfo) sqlMapClient_.queryForObject(UserConstants.GET_USER_DETAIL_BY_ID, userId);
		} catch (SQLException e) {
			logger.error("Exception in getUserProfile : " + e.getMessage());
			throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
	}
	public String getUserNameById(long userId) throws DataAccessFailedException {
		
		try {
			return  (String) sqlMapClient_.queryForObject(UserConstants.GET_USER_NAME_BY_ID, userId);
		} catch (SQLException e) {
			logger.error("Exception in getUserNameById : " + e.getMessage());
			throw new DataAccessFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
	}
	
	public void updateUser(UserInfo userInfo) throws DataUpdateFailedException {
		try {
			sqlMapClient_.update(UserConstants.QUERY_UPDATE_USER_INFO, userInfo);
		} catch (SQLException e) {
			logger.error("Exception in storing user details in database for the user : " + userInfo + " error  : " + e.getMessage());
			throw new DataUpdateFailedException(ErrorCodesEnum.DATABASE_LAYER_EXCEPTION, e);
		}
	}

}
