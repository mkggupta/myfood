package com.myfood.service.user.service.v1_0;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myfood.common.constant.MyFoodConstant;
import com.myfood.common.user.util.UserUtils;
import com.myfood.dao.exception.DataAccessFailedException;
import com.myfood.dao.exception.DataUpdateFailedException;
import com.myfood.dao.modules.user.UserDAO;
import com.myfood.dto.RegistrationInfo;
import com.myfood.dto.UserAuth;
import com.myfood.dto.UserInfo;
import com.myfood.dto.UserThirdPartyAuth;
import com.myfood.framework.exception.util.ErrorCodesEnum;
import com.myfood.service.user.exception.UserServiceFailedException;
import com.myfood.service.user.exception.UserServiceValidationFailedException;

public class UserServiceImpl implements UserService {


	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	private UserDAO userDAO ;
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	@Override
	public Long regLoginUser(RegistrationInfo registrationInfo,Map<String, Object> clientMap) throws UserServiceFailedException,
			UserServiceValidationFailedException {
		//UserServiceValidator.validateRegistrationRequest(registrationInfo);
		Long userId = null;
		try {
			if(registrationInfo.getRegistrationMode()==0){
				return -1l;
			}
			UserAuth userAuth = UserUtils.transformRegistrationInfoToUserAuth(registrationInfo, true);
			logger.debug("userAuth--"+userAuth);
			if(null!=userAuth){
				try {
					if(registrationInfo.getRegistrationMode()==4 || registrationInfo.getRegistrationMode()==5){
						userId = userDAO.getUserIdByNamePassword(registrationInfo.getUserName(),registrationInfo.getPassword());
					}else{
						userId = userDAO.getUserExists(registrationInfo.getUserKey(), registrationInfo.getRegistrationMode());
					}
				} catch (DataAccessFailedException e) {
					throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
				}
				logger.debug("userId--"+userId);
				//user is exist and possibly trying to login 
				if(null!=userId && userId>0){
					return userId;
				}else{
					//user is trying to register
					// again check that user is not exist in our db
					try {
						if(registrationInfo.getRegistrationMode()==4 || registrationInfo.getRegistrationMode()==5){
							userId = userDAO.getUserIdByNameForApp(registrationInfo.getUserName());
						}
					} catch (DataAccessFailedException e) {
						throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
					}
					logger.debug("userId after app check--"+userId);
					//user is exist and possibly trying to login 
					if(null!=userId && userId>0){
						return -1l;
					}else{ 
					registrationInfo.setId(userDAO.saveUserAuth(userAuth));
					logger.debug("after saving userauth user id --"+registrationInfo.getId() +" userAuth ="+userAuth.getId());
					userId = registrationInfo.getId();
					UserInfo userInfo = UserUtils.transformRegistrationInfoToUserInfo(registrationInfo);
					logger.debug("userInfo--"+userInfo);
					if(null!=userInfo){
						userInfo.setCountry(MyFoodConstant.DEFAULT_COUNTRY);
						userInfo.setLanguage(MyFoodConstant.DEFAULT_LANGUAGE);
						userInfo.setPrimaryEmailAddress(registrationInfo.getUserName());
						if(StringUtils.isNotBlank(registrationInfo.getLastName())){
							userInfo.setContactName(registrationInfo.getFirstName() +" "+registrationInfo.getLastName());
						}else if(StringUtils.isNotBlank(registrationInfo.getFirstName())){
							userInfo.setContactName(registrationInfo.getFirstName());
						}else{
							userInfo.setContactName(registrationInfo.getUserName());
						}
						userDAO.saveUserInfo(userInfo);
						logger.debug("after saving userInfo  id ="+userAuth.getId());
					}
					if (registrationInfo.getRegistrationMode()>0 && registrationInfo.getRegistrationMode()<4) {
						UserThirdPartyAuth userThirdPartyAuth = new UserThirdPartyAuth(userAuth.getId(), registrationInfo.getRegistrationMode(),
								registrationInfo.getUserKey(), registrationInfo.getAppKey());
						userDAO.saveUserThirdPartyAuth(userThirdPartyAuth);
					}
				
				}
				}
			}
		} catch (DataUpdateFailedException e) {
			throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
		}
		return userId;
	}
	
	
	public UserInfo getUserProfile(long userId) throws UserServiceFailedException, UserServiceValidationFailedException{
		UserInfo userInfo = null;
		try {
			String userName = userDAO.getUserNameById(userId);
			if(StringUtils.isNotBlank(userName)){
				userInfo = userDAO.getUserProfile(userId);
				userInfo.setUserName(userName);
			}
		} catch (DataAccessFailedException e) {
			throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
		}
		
		return userInfo;
	}
	
	public UserInfo updateUser(UserInfo userInfo) throws UserServiceFailedException{
		try {
			UserInfo userInfoObj = null;
			try {
				userInfoObj = userDAO.getUserProfile(userInfo.getId());
				UserUtils.updateDBUserVO(userInfoObj, userInfo);
			} catch (DataAccessFailedException e) {
				if (null == userInfoObj || userInfoObj.getId() == 0) {
					logger.debug("User not found userId: " + userInfoObj);
					throw new UserServiceFailedException(ErrorCodesEnum.USER_NOT_FOUND_EXCEPTION);
				}
			}
			try {
				userDAO.updateUser(userInfoObj);
			} catch (DataUpdateFailedException e) {
				logger.error("Exception in updating user details in database for the user : " + userInfoObj + " error  : " + e.getMessage());
				throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
			}
			return  userInfoObj;
		} catch (Exception e) {
			throw new UserServiceFailedException(ErrorCodesEnum.USER_SERVICE_FAILED_EXCEPTION);
		}
	}

}
